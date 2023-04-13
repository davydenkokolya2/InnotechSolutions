package by.innotechsolutions.practice.controller;

import by.innotechsolutions.practice.DTO.GeolocationDTO;
import by.innotechsolutions.practice.entity.Geolocation;
import by.innotechsolutions.practice.service.GeolocationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@AllArgsConstructor
@RestController

public class GeolocationController {

    GeolocationService geolocationService;

    @RequestMapping(value = "/geolocation", method = POST)
    @ResponseBody
    public boolean getLocation(@RequestBody GeolocationDTO geolocationDTO) {
        //System.out.println(geolocationDTO.getTime());
        geolocationService.saveGeolocation(geolocationDTO);
        return geolocationService.checkSOS(geolocationDTO);
    }

    @RequestMapping(value = "/geolocation", method = GET)
    @ResponseBody
    public List<Geolocation> sendLocation() {
        return geolocationService.getGeolocation();
    }
}
