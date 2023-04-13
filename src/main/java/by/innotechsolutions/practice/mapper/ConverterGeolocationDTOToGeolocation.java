package by.innotechsolutions.practice.mapper;

import by.innotechsolutions.practice.DTO.GeolocationDTO;
import by.innotechsolutions.practice.entity.Geolocation;
import org.springframework.stereotype.Service;

@Service
public class ConverterGeolocationDTOToGeolocation {

    public Geolocation toEntity(GeolocationDTO dto) {
        Geolocation geolocation = new Geolocation();
        geolocation.setLongitude(dto.getLongitude());
        geolocation.setLatitude(dto.getLatitude());
        geolocation.setSos(dto.isSos());
        geolocation.setTime(dto.getTime());
        geolocation.setUserId(dto.getUserId());
        geolocation.setDate(dto.getDate());
        return geolocation;
    }

    public GeolocationDTO toEntity(Geolocation dto) {
        GeolocationDTO geolocation = new GeolocationDTO();
        geolocation.setLongitude(dto.getLongitude());
        geolocation.setLatitude(dto.getLatitude());
        geolocation.setSos(dto.isSos());
        geolocation.setTime(dto.getTime());
        geolocation.setUserId(dto.getUserId());
        geolocation.setDate(dto.getDate());
        return geolocation;
    }
}
