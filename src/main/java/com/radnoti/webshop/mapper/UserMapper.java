package com.radnoti.webshop.mapper;

import com.radnoti.webshop.model.dto.UserDto;
import com.radnoti.webshop.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "role.type", target = "roleName")
    UserDto fromEntityToDto(User user);

    @Mapping(source = "roleName", target = "role.type")
    User fromDtoToEntity(UserDto userDto);

}
