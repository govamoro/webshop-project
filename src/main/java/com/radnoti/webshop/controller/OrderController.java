package com.radnoti.webshop.controller;

import com.radnoti.webshop.enums.RoleEnum;
import com.radnoti.webshop.model.dto.OrderDto;
import com.radnoti.webshop.model.dto.ResponseDto;
import com.radnoti.webshop.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;

@RestController
@RequestMapping(path = "/order")
// @RequiredArgsConstructor ez csak akkor kell ha "private final OrderService orderService;" , @autowired annotáció nélkül
@CrossOrigin(origins = "*")

public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/add")
    @RolesAllowed({RoleEnum.Types.ADMIN, RoleEnum.Types.USER})
    public ResponseDto addProduct(@RequestBody OrderDto orderDto) {
        return orderService.saveProduct(orderDto);
    }
}
