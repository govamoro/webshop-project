package com.radnoti.webshop.mapper;

import com.radnoti.webshop.model.dto.BasketDto;
import com.radnoti.webshop.model.entity.Basket;
import org.mapstruct.Mapper;


import java.util.List;

@Mapper(componentModel = "spring")
public interface BasketMapper {
    BasketDto fromEntityToDto(Basket basket);
    List<BasketDto> fromEntityToDto(List<Basket> basketList);
    Basket fromDtoToEntity(BasketDto basketDto);
}
