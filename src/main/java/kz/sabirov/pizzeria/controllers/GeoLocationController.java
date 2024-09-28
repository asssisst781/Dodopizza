package kz.sabirov.pizzeria.controllers;

import jakarta.servlet.http.HttpServletRequest;
import kz.sabirov.pizzeria.entities.GeoLocation;
import kz.sabirov.pizzeria.entities.User;
import kz.sabirov.pizzeria.services.Implementations.GeolocationServiceImpl;
import kz.sabirov.pizzeria.services.Implementations.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class GeoLocationController {
    String ipAddress = "2.73.53.135";
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private GeolocationServiceImpl geolocationService;
    @GetMapping(value = "/geo/userLocation")
    public ResponseEntity<GeoLocation> getUserLocation(HttpServletRequest request) {
        /*
        Тут используется фиксированный реальный адрес (2.73.53.135), потому что гет запрос выполняется
         на локальном сервере и метод request.getRemoteAddr() в классе geoLocationService
         возвращает адрес 127.0.0.1, поэтому метод getClientIp() временно закомментирован
        */
        //String ipAddress = geolocationService.getClientIp(request);
        String url = "http://ip-api.com/json/" + ipAddress;

        GeoLocation response = restTemplate.getForObject(url, GeoLocation.class);
        geolocationService.addGeoLocations(response);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        String email = ((User) principal).getEmail();

        User foundUser = userService.findUser(email);
        foundUser.setGeoLocations(response);
        userService.updateUser(foundUser);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
