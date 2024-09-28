package kz.sabirov.pizzeria.services;

import kz.sabirov.pizzeria.dto.PizzaAddDTO;
import kz.sabirov.pizzeria.dto.PizzaRemoveDTO;
import kz.sabirov.pizzeria.entities.Pizza;
import org.springframework.http.ResponseEntity;

import java.util.Optional;


public interface PizzaService {
    ResponseEntity<String> addPizza(PizzaAddDTO pizzaAddDTO);
    ResponseEntity<String> removePizza(PizzaRemoveDTO pizzaRemoveDTO);
    Pizza findPizza(Long id);
    Pizza makePizza(PizzaAddDTO pizzaAddDTO);
}
