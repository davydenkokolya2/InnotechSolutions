package by.innotechsolutions.practice.service;

import by.innotechsolutions.practice.entity.GeolocationDTO;
import by.innotechsolutions.practice.repository.GeolocationRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public List<GeolocationDTO> getGeolocation() {
        return new ArrayList<>();
    }
}
