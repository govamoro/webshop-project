package com.radnoti.webshop.service;

import com.radnoti.webshop.model.dto.BasketDto;
import com.radnoti.webshop.repository.BasketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor

public class BasketService {

    private final BasketRepository basketRepository;
    public void saveBasket(BasketDto basketDto) {
    }

    @Transactional
    public void deleteBasketById(Integer basketId) {
        basketRepository.deleteById(basketId);
    }
}
