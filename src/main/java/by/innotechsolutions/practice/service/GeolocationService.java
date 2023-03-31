package by.innotechsolutions.practice.service;

import by.innotechsolutions.practice.DTO.GeolocationDTO;
import by.innotechsolutions.practice.entity.Geolocation;
import by.innotechsolutions.practice.mapper.ConverterGeolocationDTOToGeolocation;
import by.innotechsolutions.practice.repository.GeolocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class GeolocationService {
    private final GeolocationRepository geolocationRepository;

    private final ConverterGeolocationDTOToGeolocation converterGeolocationDTOToGeolocation = new ConverterGeolocationDTOToGeolocation();

    GeolocationDTO geolocation = new GeolocationDTO();
    int counter = 0;

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

    public boolean checkSOS(GeolocationDTO geolocationDTO) {

        if (geolocationDTO.isSos()) {
            if (counter > 0 && counter < 3)
                if (Math.abs(geolocationDTO.getTime().getSecond() - geolocation.getTime().getSecond()) <= 10 && getDistanceBetweenCoordinates(geolocationDTO, geolocation) <= 10)
                    counter++;

            if (counter > 2) {
                counter = 0;
                return true;
            } else {
                geolocation.setLatitude(geolocationDTO.getLatitude());
                geolocation.setLongitude(geolocationDTO.getLongitude());
                geolocation.setTime(geolocationDTO.getTime());
                counter++;
                return false;
            }
        }
        return false;
    }

    public List<Geolocation> getGeolocation() {
        return geolocationRepository.findAll();
    }
}
