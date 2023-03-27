package by.innotechsolutions.practice.controller;

import by.innotechsolutions.practice.entity.GeolocationDTO;
import by.innotechsolutions.practice.service.GeolocationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GeolocationController {

    GeolocationService geolocationService;

    public GeolocationController(GeolocationService geolocationService){
        this.geolocationService = geolocationService;
    }

    @PostMapping("/geolocation")
    public boolean getLocation(@RequestBody GeolocationDTO geolocationDTO){
        return geolocationService.saveGeolocation(geolocationDTO);
    }
}
