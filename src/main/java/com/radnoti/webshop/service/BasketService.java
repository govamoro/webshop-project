package com.radnoti.webshop.service;

import com.radnoti.webshop.mapper.BasketMapper;
import com.radnoti.webshop.mapper.OrderMapper;
import com.radnoti.webshop.model.dto.BasketDto;
import com.radnoti.webshop.model.dto.OrderDto;
import com.radnoti.webshop.model.dto.ResponseDto;
import com.radnoti.webshop.model.entity.Basket;
import com.radnoti.webshop.model.entity.Order;
import com.radnoti.webshop.repository.BasketRepository;
import com.radnoti.webshop.repository.OrderRepository;
import com.radnoti.webshop.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor

public class BasketService {

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private BasketRepository basketRepository;
    @Autowired
    private BasketMapper basketMapper;

    public ResponseDto saveBasket(BasketDto basketDto) {
        Basket basket = basketMapper.fromDtoToEntity(basketDto);
        Basket save = basketRepository.save(basket);
        return new ResponseDto(save.getId());

    /* @Transactional
    public void deleteBasketById(Integer basketId) {
        basketRepository.deleteById(basketId);
    } */
    }
}
