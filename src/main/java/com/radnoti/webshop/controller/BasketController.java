package com.radnoti.webshop.controller;

import com.radnoti.webshop.model.dto.BasketDto;
import com.radnoti.webshop.model.entity.Art;
import com.radnoti.webshop.model.entity.Basket;
import com.radnoti.webshop.service.BasketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/basket")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class BasketController {

    private final BasketService basketService;

    @PostMapping("/save")
    public ResponseEntity<Void> saveBasket(@RequestBody BasketDto basketDto) {
        basketService.saveBasket(basketDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

   /* @DeleteMapping("/delete/{basketId}")
    public ResponseEntity<Void> deleteBasket(@PathVariable Integer basketId) {
        basketService.deleteBasketById(basketId);
        return ResponseEntity.noContent().build();
    } */

    /* public void delete(String authHeader, Integer productId) {
        //n+1 problema
        Integer userId = jwtUtil.getIdFromAuthHeader(authHeader);
        Optional<Basket> byId = basketRepository.findById(productId);

        if (byId.isEmpty()){
            throw new RuntimeException("Üres a kosár");
        }
        Basket basket = byId.get();

        if (basket.getUser().getId() != userId){
            throw new RuntimeException("");
        }

        basketRepository.delete(basket);
    } */
}
