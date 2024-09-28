package kz.sabirov.pizzeria.dto;

import kz.sabirov.pizzeria.entities.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderDTO {
    private PizzaIdDTO pizzaIdDTO;
    private PaymentMethod paymentMethod;
}