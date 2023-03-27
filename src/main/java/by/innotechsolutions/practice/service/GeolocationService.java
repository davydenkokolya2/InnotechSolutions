package by.innotechsolutions.practice.service;

import by.innotechsolutions.practice.entity.GeolocationDTO;
import by.innotechsolutions.practice.repository.GeolocationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GeolocationService {
    private final GeolocationRepository geolocationRepository;

    public GeolocationService(GeolocationRepository geolocationRepository) {
        this.geolocationRepository = geolocationRepository;
    }

    public boolean saveGeolocation(GeolocationDTO geolocationDTO) {
        try {
            //System.out.println(geolocationDTO.getLatitude());
            geolocationRepository.save(geolocationDTO);
            return true;
        } catch (Exception ex) {
            System.out.println(ex.toString());
            return false;
        }
    }

    public double getDistanceBetweenCoordinates(GeolocationDTO firstGeo, GeolocationDTO secondGeo) {

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


    public List<GeolocationDTO> getGeolocation() {
        return geolocationRepository.findAll();
    }
}
