package kz.sabirov.pizzeria.controllers;

import kz.sabirov.pizzeria.PDFGenerator.DocumentCompImpl;
import kz.sabirov.pizzeria.dto.OrderDTO;
import kz.sabirov.pizzeria.dto.OrderCustomDTO;
import kz.sabirov.pizzeria.dto.PizzaIdDTO;
import kz.sabirov.pizzeria.entities.PaymentMethod;
import kz.sabirov.pizzeria.entities.Pizza;
import kz.sabirov.pizzeria.entities.User;
import kz.sabirov.pizzeria.services.Implementations.PaymentServiceImpl;
import kz.sabirov.pizzeria.services.Implementations.PizzaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/order")
public class OrderController {

    @Autowired
    private PaymentServiceImpl paymentService;
    @Autowired
    private PizzaServiceImpl pizzaService;
    @Autowired
    private DocumentCompImpl documentComp;
    @PostMapping(value = "/makeOrder")
    public ResponseEntity<?> makeOrder(@RequestBody OrderDTO order){
        String msg = "";
        PaymentMethod pm = order.getPaymentMethod();
        PizzaIdDTO pizza = order.getPizzaIdDTO();
        msg = paymentService.checkPaymentMethod(pm);
        Pizza pizzaToPurchase = pizzaService.findPizza(pizza.getId());
        if(msg != null){
            return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
        }
        String paymentMethod = paymentService.determinePaymentMethod(pm);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        String email = ((User) principal).getEmail();



        if(paymentMethod.equals("VISA")){
            Long price = pizzaToPurchase.getPrice();
            Long newPrice = (long) (price - price*0.15);
            msg = String.format("As you use VISA you have given discount of 15%%. Initial price - %d, new price - %d", price, newPrice);

            documentComp.createPDF(email, pm, pizzaToPurchase, newPrice);
            return new ResponseEntity<>(msg, HttpStatus.OK);
        }
        else {
            Long price = pizzaToPurchase.getPrice();
            Long newPrice = (long) (price - price * 0.20);
            msg = String.format("As you use MasterCard you have given discount of 20%%. Initial price - %d, new price - %d", price, newPrice);

            documentComp.createPDF(email, pm, pizzaToPurchase, newPrice);
            return new ResponseEntity<>(msg, HttpStatus.OK);
        }
    }
    @PostMapping(value = "/makeOrderCustom")
    public ResponseEntity<?> makeOrderCustom(@RequestBody OrderCustomDTO order){
        String msg = "";
        PaymentMethod pm = order.getPaymentMethod();
        msg = paymentService.checkPaymentMethod(pm);
        Pizza pizzaToPurchase = pizzaService.makePizza(order.getPizzaAddDTO());
        if(pizzaToPurchase == null){
            msg = "Bad pizza configuration";
            return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
        }
        String paymentMethod = paymentService.determinePaymentMethod(pm);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        String email = ((User) principal).getEmail();



        if(paymentMethod.equals("VISA")){
            Long price = pizzaToPurchase.getPrice();
            Long newPrice = (long) (price - price*0.15);
            msg = String.format("As you use VISA you have given discount of 15%%. Initial price - %d, new price - %d", price, newPrice);

            documentComp.createPDF(email, pm, pizzaToPurchase, newPrice);
            return new ResponseEntity<>(msg, HttpStatus.OK);
        }
        else {
            Long price = pizzaToPurchase.getPrice();
            Long newPrice = (long) (price - price * 0.20);
            msg = String.format("As you use MasterCard you have given discount of 20%%. Initial price - %d, new price - %d", price, newPrice);

            documentComp.createPDF(email, pm, pizzaToPurchase, newPrice);
            return new ResponseEntity<>(msg, HttpStatus.OK);
        }
    }
}
