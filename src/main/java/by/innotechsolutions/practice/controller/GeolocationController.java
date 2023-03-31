package by.innotechsolutions.practice.controller;

import by.innotechsolutions.practice.DTO.GeolocationDTO;
import by.innotechsolutions.practice.service.GeolocationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@AllArgsConstructor
@RestController
public class GeolocationController {

    GeolocationService geolocationService;


    @RequestMapping(value = "/geolocation", method = POST)
    @ResponseBody
    //@PostMapping("/geolocation")
    public boolean getLocation(@RequestBody GeolocationDTO geolocationDTO) {
        geolocationService.saveGeolocation(geolocationDTO);
        return geolocationService.checkSOS(geolocationDTO);
    }
}
