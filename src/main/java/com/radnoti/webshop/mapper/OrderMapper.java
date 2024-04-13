package com.radnoti.webshop.mapper;

import com.radnoti.webshop.model.dto.OrderDto;
import com.radnoti.webshop.model.entity.Order;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderDto fromEntityToDto(Order order);
    List<OrderDto> fromEntityToDto(List<Order> orderList);
    Order fromDtoToEntity(OrderDto orderDto);
}
