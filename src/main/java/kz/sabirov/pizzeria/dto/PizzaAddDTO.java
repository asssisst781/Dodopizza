package kz.sabirov.pizzeria.dto;

import kz.sabirov.pizzeria.entities.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PizzaAddDTO {
    private String title;
    private List<Long> ids;
}
