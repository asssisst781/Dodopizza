package kz.sabirov.pizzeria.mappers;

import kz.sabirov.pizzeria.dto.PizzaAddDTO;
import kz.sabirov.pizzeria.entities.Pizza;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PizzaMapper {
    Pizza PizzaAddDTOtoPizza(PizzaAddDTO pizzaAddDTO);
}
