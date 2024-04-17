package com.radnoti.webshop.service;

import com.radnoti.webshop.mapper.BasketMapper;
import com.radnoti.webshop.model.dto.BasketDto;
import com.radnoti.webshop.model.dto.ResponseDto;
import com.radnoti.webshop.model.entity.Basket;
import com.radnoti.webshop.repository.BasketRepository;
import com.radnoti.webshop.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BasketService{

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
    }
    public void delete(String authHeader, Integer productId) {
            Integer userId = jwtUtil.getIdFromAuthHeader(authHeader);
            Optional<Basket> byId = basketRepository.findById(productId);

            if (byId.isEmpty()){
                throw new RuntimeException("nincs ilyen art");
            }
            Basket basket = byId.get();
            if (basket.getUser().getId() != userId){
                throw new RuntimeException("nem a tied te fadz");
            }

            basketRepository.delete(basket);
        }
    }

