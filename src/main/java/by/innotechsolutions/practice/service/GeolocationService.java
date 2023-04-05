package by.innotechsolutions.practice.service;

import by.innotechsolutions.practice.DTO.GeolocationDTO;
import by.innotechsolutions.practice.DTO.SendMessageRequestDTO;
import by.innotechsolutions.practice.controller.SseRestController;
import by.innotechsolutions.practice.entity.Geolocation;
import by.innotechsolutions.practice.mapper.ConverterGeolocationDTOToGeolocation;
import by.innotechsolutions.practice.repository.GeolocationRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.*;

import static by.innotechsolutions.practice.utils.Constants.*;

@RequiredArgsConstructor
@Service
public class GeolocationService {
    private final GeolocationRepository geolocationRepository;
    private final SseRestController sseRestController;

    private final ConverterGeolocationDTOToGeolocation converterGeolocationDTOToGeolocation = new ConverterGeolocationDTOToGeolocation();


    public void saveGeolocation(final GeolocationDTO geolocationDTO) {
        try {
            //System.out.println(geolocationDTO.getLatitude());
            geolocationRepository.save(converterGeolocationDTOToGeolocation.toEntity(geolocationDTO));
        } catch (Exception ex) {
            //System.out.println(ex.toString());
        }
    }

    public double getDistanceBetweenCoordinates(final GeolocationDTO firstGeo,
                                                final GeolocationDTO secondGeo) {
        double dLat = deg2rad(firstGeo.getLatitude() - secondGeo.getLatitude());
        double dLon = deg2rad(firstGeo.getLongitude() - secondGeo.getLongitude());
        double d = Math.sin(dLat / TWO) * Math.sin(dLat / TWO) +
                Math.cos(deg2rad(firstGeo.getLatitude())) * Math.cos(deg2rad(secondGeo.getLatitude())) *
                        Math.sin(dLon / TWO) * Math.sin(dLon / TWO);
        //System.out.println(d);
        return EARTH_RADIUS * TWO * Math.atan2(Math.sqrt(d), Math.sqrt(ONE - d));
    }

    private double deg2rad(double deg) {
        return deg * (Math.PI / HALF_CIRCLE);
    }

    ArrayList<ArrayList<GeolocationDTO>> listOfUsers = new ArrayList<>();

    public boolean checkSOS(GeolocationDTO geolocationDTO) {
        boolean flag = false, flag2 = false;

        if (geolocationDTO.isSos()) {
            System.out.println(geolocationDTO.getTime().toString());
            for (int i = 0; i < listOfUsers.size(); i++) {
                for (int j = 0; j < listOfUsers.get(i).size(); j++)
                    if (Math.abs(geolocationDTO.getTime().getHour() - listOfUsers.get(i).get(j).getTime().getHour()) < ONE
                            && Math.abs(geolocationDTO.getTime().getMinute() - listOfUsers.get(i).get(j).getTime().getMinute()) < ONE
                            && Math.abs(geolocationDTO.getTime().getSecond() - listOfUsers.get(i).get(j).getTime().getSecond()) <= CRITICAL_TIME
                            && getDistanceBetweenCoordinates(geolocationDTO, listOfUsers.get(i).get(j)) <= CRITICAL_RADIUS
                            && !Objects.equals(listOfUsers.get(i).get(j).getUserId(), geolocationDTO.getUserId())) {
                        flag = true;
                    } else if (Objects.equals(listOfUsers.get(i).get(j).getUserId(), geolocationDTO.getUserId()))
                        flag = false;
                if (flag) {
                    listOfUsers.get(i).add(geolocationDTO);
                    flag2 = true;
                }
                if (listOfUsers.get(i).size() == CRITICAL_NUMBER_CARS) {
                    sendSOSNotification(listOfUsers.get(i));
                    listOfUsers.get(i).add(geolocationDTO);
                }
            }
            if (!flag2) {
                listOfUsers.add(new ArrayList<>(1));
                listOfUsers.get(listOfUsers.size() - 1).add(geolocationDTO);
            }
        } else {
            SendMessageRequestDTO sendMessageRequestDTO = new SendMessageRequestDTO();
            sendMessageRequestDTO.setMessage("get geolocation");
            sseRestController.sendMessageByName(geolocationDTO.getUserId().toString(), sendMessageRequestDTO);
        }

        for (ArrayList<GeolocationDTO> listOfUser : listOfUsers) {
            for (GeolocationDTO dto : listOfUser)
                System.out.print(dto.getUserId() + " ");
            System.out.println();
        }

        return false;
    }

    private void sendSOSNotification(ArrayList<GeolocationDTO> listGeolocations) {
        HashSet<GeolocationDTO> listNotificationUsers = new HashSet<>();
        List<Geolocation> result = geolocationRepository.findByTimeBetween(
                listGeolocations.get(0).getTime().minusMinutes(TIME_BEFORE_ACCIDENT_FOR_NOTIFICATION),
                listGeolocations.get(0).getTime());
        for (Geolocation item : result)
            if (getDistanceBetweenCoordinates(
                    converterGeolocationDTOToGeolocation.toEntity(item),
                    listGeolocations.get(0)) <= NOTIFICATION_RADIUS && !item.isSos())
                listNotificationUsers.add(converterGeolocationDTOToGeolocation.toEntity(item));

        SendMessageRequestDTO sendMessageRequestDTO = new SendMessageRequestDTO();
        sendMessageRequestDTO.setMessage("sos");

        //sseRestController.sendMessageForAll(sendMessageRequestDTO);
        for (GeolocationDTO item : listNotificationUsers)
            sseRestController.sendMessageByName(item.getUserId().toString(), sendMessageRequestDTO);
    }

    public List<Geolocation> getGeolocation() {
        return geolocationRepository.findAll();
    }
}
