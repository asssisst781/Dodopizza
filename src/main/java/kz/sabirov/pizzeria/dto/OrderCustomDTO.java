package kz.sabirov.pizzeria.dto;

import kz.sabirov.pizzeria.entities.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderCustomDTO {
    private PizzaAddDTO pizzaAddDTO;
    private PaymentMethod paymentMethod;
}