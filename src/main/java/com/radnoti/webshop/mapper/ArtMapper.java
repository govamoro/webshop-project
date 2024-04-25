package com.radnoti.webshop.mapper;

import com.radnoti.webshop.model.dto.ArtDto;
import com.radnoti.webshop.model.entity.Art;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ArtMapper {
    ArtDto fromEntityToDto(Art art);
    List<ArtDto> fromEntityToDto(List<Art> artList);
    Art fromDtoToEntity(ArtDto artDto);
    List<Art> fromDtoToEntity(List<ArtDto> artList);

}
