package bg.softuni.pizza.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import bg.softuni.pizza.model.dto.user.UserRegisterDTO;
import bg.softuni.pizza.model.entity.UserEntity;

@Mapper(componentModel = "spring")
public interface UserMapper {

  @Mapping(target = "active", constant = "true")
  UserEntity userDtoToUserEntity(UserRegisterDTO registerDTO);
}
