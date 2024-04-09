package com.radnoti.webshop.mapper;

import com.radnoti.webshop.model.dto.StyleDto;
import com.radnoti.webshop.model.entity.Style;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StyleMapper {
    StyleDto fromEntityToDto(Style style);
    List<StyleDto> fromEntityToDto(List<Style> styleList);
    Style fromDtoToEntity(StyleDto styleDtoDto);
}
