package by.innotechsolutions.practice.service;

import by.innotechsolutions.practice.DTO.GeolocationDTO;
import by.innotechsolutions.practice.entity.Geolocation;
import by.innotechsolutions.practice.mapper.ConverterGeolocationDTOToGeolocation;
import by.innotechsolutions.practice.repository.GeolocationRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class GeolocationService {
    private final GeolocationRepository geolocationRepository;

    private final ConverterGeolocationDTOToGeolocation converterGeolocationDTOToGeolocation = new ConverterGeolocationDTOToGeolocation();

    GeolocationDTO geolocation = new GeolocationDTO();

    public boolean saveGeolocation(final GeolocationDTO geolocationDTO) {
        try {
            //System.out.println(geolocationDTO.getLatitude());
            geolocationRepository.save(converterGeolocationDTOToGeolocation.toEntity(geolocationDTO));
            return true;
        } catch (Exception ex) {
            //System.out.println(ex.toString());
            return false;
        }
    }

    public double getDistanceBetweenCoordinates(final GeolocationDTO firstGeo,
                                                final GeolocationDTO secondGeo) {
        int radius = 6371000;
        double dLat = deg2rad(firstGeo.getLatitude() - secondGeo.getLatitude());
        double dLon = deg2rad(firstGeo.getLongitude() - secondGeo.getLongitude());
        double d = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(deg2rad(firstGeo.getLatitude())) * Math.cos(deg2rad(secondGeo.getLatitude())) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);
        //System.out.println(d);
        double c = 2 * Math.atan2(Math.sqrt(d), Math.sqrt(1 - d));
        return radius * c;
    }

    private double deg2rad(double deg) {
        return deg * (Math.PI / 180);
    }

    ArrayList<ArrayList<GeolocationDTO>> listOfUsers = new ArrayList<>();

    public boolean checkSOS(GeolocationDTO geolocationDTO) {
        boolean flag = false, flag2 = false;

        if (geolocationDTO.isSos()) {
            for (int i = 0; i < listOfUsers.size(); i++) {
                for (int j = 0; j < listOfUsers.get(i).size(); j++)
                    if (Math.abs(geolocationDTO.getTime().getSecond() - listOfUsers.get(i).get(j).getTime().getSecond()) <= 10
                            && getDistanceBetweenCoordinates(geolocationDTO, listOfUsers.get(i).get(j)) <= 10
                            && !Objects.equals(listOfUsers.get(i).get(j).getUserId(), geolocationDTO.getUserId())) {
                        flag = true;
                    } else if (Objects.equals(listOfUsers.get(i).get(j).getUserId(), geolocationDTO.getUserId()))
                        flag = false;
                if (flag) {
                    listOfUsers.get(i).add(geolocationDTO);
                    flag2 = true;
                }
                if (listOfUsers.get(i).size() == 3) {
                    sendSOSNotification(listOfUsers.get(i));
                }
            }
        }
        if (!flag2) {
            listOfUsers.add(new ArrayList<>(1));
            listOfUsers.get(listOfUsers.size() - 1).add(geolocationDTO);
        }
        for (ArrayList<GeolocationDTO> listOfUser : listOfUsers) {
            for (GeolocationDTO dto : listOfUser)
                System.out.print(dto.getUserId() + " ");
            System.out.println();
        }

        geolocation.setLatitude(geolocationDTO.getLatitude());
        geolocation.setLongitude(geolocationDTO.getLongitude());
        geolocation.setTime(geolocationDTO.getTime());
        return false;
    }

    private void sendSOSNotification(ArrayList<GeolocationDTO> listGeolocations) {
        geolocationRepository.findByTime();
    }

    public List<Geolocation> getGeolocation() {
        return geolocationRepository.findAll();
    }
}
