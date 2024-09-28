package kz.sabirov.pizzeria.controllers;

import kz.sabirov.pizzeria.dto.PizzaAddDTO;
import kz.sabirov.pizzeria.dto.PizzaRemoveDTO;
import kz.sabirov.pizzeria.dto.UserEmailFieldDTO;
import kz.sabirov.pizzeria.entities.Pizza;
import kz.sabirov.pizzeria.mappers.PizzaMapper;
import kz.sabirov.pizzeria.services.Implementations.UserServiceImpl;
import kz.sabirov.pizzeria.services.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private PizzaService pizzaService;
    @Autowired
    private PizzaMapper pizzaMapper;
    @Qualifier("userService")
    @Autowired
    private UserServiceImpl userService;

    @PostMapping(value = "/addPizza")
    public ResponseEntity<?> addNewPizzaToShowcase(@RequestBody PizzaAddDTO pizzaAddDTO){
        try{
            return pizzaService.addPizza(pizzaAddDTO);
        }
        catch (Exception e){
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping(value = "/removePizza")
    public ResponseEntity<?> removePizzaFromShowcase(@RequestBody PizzaRemoveDTO pizzaRemoveDTO){
        try{
            return pizzaService.removePizza(pizzaRemoveDTO);
        }
        catch (Exception e){
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping(value = "/banUser")
    public ResponseEntity<?> banUser(@RequestBody UserEmailFieldDTO userDTO){
        try{
            return userService.banUser(userDTO);
        }
        catch (Exception e){
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping(value = "/unbanUser")
    public ResponseEntity<?> unbanUser(@RequestBody UserEmailFieldDTO userDTO){
        try{
            return userService.unbanUser(userDTO);
        }
        catch (Exception e){
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
    }
}
