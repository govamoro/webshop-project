package com.radnoti.webshop.mapper;

import com.radnoti.webshop.model.dto.ArtDto;
import com.radnoti.webshop.model.entity.Art;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ArtMapper {
    ArtDto fromEntityToDto(Art art);
    Art fromDtoToEntity(ArtDto artDto);
}
