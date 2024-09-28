package kz.sabirov.pizzeria.services.Implementations;

import jakarta.servlet.http.HttpServletRequest;
import kz.sabirov.pizzeria.entities.GeoLocation;
import kz.sabirov.pizzeria.repositories.GeoLocationRepository;
import kz.sabirov.pizzeria.services.GeolocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GeolocationServiceImpl {
    @Autowired
    private GeoLocationRepository geoLocationRepository;
    public String getClientIp(HttpServletRequest request) {
        String xForwardedForHeader = request.getHeader("X-Forwarded-For");
        if (xForwardedForHeader == null) {
            return request.getRemoteAddr();
        }
        return xForwardedForHeader.split(",")[0];
    }
    public void addGeoLocations(GeoLocation geoLocation){
        geoLocationRepository.save(geoLocation);
    }
}
