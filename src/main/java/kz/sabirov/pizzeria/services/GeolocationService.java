package kz.sabirov.pizzeria.services;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;


public interface GeolocationService {
    String getClientIp(HttpServletRequest req);
}
