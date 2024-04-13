package com.radnoti.webshop.service;

import com.radnoti.webshop.mapper.OrderMapper;
import com.radnoti.webshop.model.dto.OrderDto;
import com.radnoti.webshop.model.dto.ResponseDto;
import com.radnoti.webshop.model.entity.Order;
import com.radnoti.webshop.repository.OrderRepository;
import com.radnoti.webshop.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderMapper orderMapper;

    public ResponseDto saveProduct(OrderDto orderDto) {
        Order order = orderMapper.fromDtoToEntity(orderDto);
        Order save = orderRepository.save(order);
        return new ResponseDto(save.getId());
    }
}
