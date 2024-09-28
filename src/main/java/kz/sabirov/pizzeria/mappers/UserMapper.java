package kz.sabirov.pizzeria.mappers;

import kz.sabirov.pizzeria.dto.UserPasswordAndEmailDTO;
import kz.sabirov.pizzeria.entities.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserPasswordAndEmailDTO userToUserRegistrationDTO(User user);
    User userRegistrationDTOToUser(UserPasswordAndEmailDTO userDTO);
}
