package kz.sabirov.pizzeria.services;

import kz.sabirov.pizzeria.dto.UserEmailFieldDTO;
import kz.sabirov.pizzeria.dto.UserPasswordAndEmailDTO;
import kz.sabirov.pizzeria.entities.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


public interface UserService {
    void addUser(UserPasswordAndEmailDTO userDTO);
    void updateUser(User user);
    User createUser(User user);
    ResponseEntity<String> banUser(UserEmailFieldDTO userDTO);
    ResponseEntity<String> unbanUser(UserEmailFieldDTO userDTO);
}
