package by.innotechsolutions.practice.mapper;

import by.innotechsolutions.practice.DTO.GeolocationDTO;
import by.innotechsolutions.practice.entity.Geolocation;

public class ConverterGeolocationDTOToGeolocation {
    public Geolocation toEntity(GeolocationDTO dto) {
        Geolocation geolocation = new Geolocation();
        geolocation.setLongitude(dto.getLongitude());
        geolocation.setLatitude(dto.getLatitude());
        geolocation.setSos(dto.getSos());
        geolocation.setTime(dto.getTime());
        geolocation.setUserId(dto.getUserId());
        return geolocation;
    }
}
