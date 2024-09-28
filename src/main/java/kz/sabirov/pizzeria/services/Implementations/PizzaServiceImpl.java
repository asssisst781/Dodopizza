package kz.sabirov.pizzeria.services.Implementations;

import kz.sabirov.pizzeria.dto.PizzaAddDTO;
import kz.sabirov.pizzeria.dto.PizzaRemoveDTO;
import kz.sabirov.pizzeria.entities.Pizza;
import kz.sabirov.pizzeria.entities.Product;
import kz.sabirov.pizzeria.repositories.PizzaRepository;
import kz.sabirov.pizzeria.services.PizzaService;
import kz.sabirov.pizzeria.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PizzaServiceImpl implements PizzaService {
    @Autowired
    private ProductService productService;
    @Autowired
    private PizzaRepository pizzaRepository;
    @Override
    public ResponseEntity<String> addPizza(PizzaAddDTO pizza) {
        List<Long> productIds = pizza.getIds();
        String title = pizza.getTitle();
        String msg;
        List<Product> products = productService.findProducts(productIds);
        if (products.size() != productIds.size()) {
            msg = "Some of typed products do not exist";
            return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
        }
        if (title == null) {
            msg = "Pizza should be named";
            return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
        }
        List<Product> productsList = new ArrayList<>(products);
        Long totalPrice = products.stream()
                .mapToLong(Product::getPrice)
                .sum();
        Long finalPrice = (long) (totalPrice * 1.1);
        Pizza newPizza = new Pizza();
        newPizza.setTitle(title);
        newPizza.setPrice(finalPrice);
        newPizza.setProducts(productsList);

        pizzaRepository.save(newPizza);
        msg = "Pizza had been successfully added";
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> removePizza(PizzaRemoveDTO pizzaRemoveDTO) {
        List<Long> idsForRemoval = pizzaRemoveDTO.getIds();
        String msg;
        for(Long id : idsForRemoval){
            Pizza foundPizza = pizzaRepository.findAllById(id);
            if(foundPizza == null){
                msg = String.format("Pizza by id %d has not been found", id);
                return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
            }
        }
        msg = "Pizza/Pizzas successfully removed";
        pizzaRepository.deleteAllById(idsForRemoval);
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }

    @Override
    public Pizza findPizza(Long id) {
        Pizza pizza = pizzaRepository.findAllById(id);
        return pizza;
    }

    @Override
    public Pizza makePizza(PizzaAddDTO pizzaAddDTO) {
        if(checkPizza(pizzaAddDTO)){
            return null;
        }
        List<Long> productIds = pizzaAddDTO.getIds();
        String title = pizzaAddDTO.getTitle();
        List<Product> products = productService.findProducts(productIds);
        List<Product> productsList = new ArrayList<>(products);
        Long totalPrice = products.stream()
                .mapToLong(Product::getPrice)
                .sum();
        Long finalPrice = (long) (totalPrice * 1.1);
        Pizza newPizza = new Pizza();
        newPizza.setTitle(title);
        newPizza.setPrice(finalPrice);
        newPizza.setProducts(productsList);

        return newPizza;
    }
    public boolean checkPizza(PizzaAddDTO pizzaAddDTO){
        List<Long> productIds = pizzaAddDTO.getIds();
        String title = pizzaAddDTO.getTitle();
        String msg;
        List<Product> products = productService.findProducts(productIds);
        if (products.size() != productIds.size()) {
            return true;
        }
        if (title == null) {
            return true;
        }
        return false;
    }
}
