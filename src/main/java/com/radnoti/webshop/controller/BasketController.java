package com.radnoti.webshop.controller;

import com.radnoti.webshop.model.dto.ArtDto;
import com.radnoti.webshop.model.dto.BasketDto;
import com.radnoti.webshop.model.entity.Basket;
import com.radnoti.webshop.service.BasketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/basket")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class BasketController {

    private final BasketService basketService;

    @PostMapping("/save")
    public ResponseEntity<Void> saveBasket(@RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader, @RequestBody ArtDto artDto) {
        basketService.saveBasket(authHeader, artDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

   @DeleteMapping("/delete/{basketId}")
    public ResponseEntity<Void> deleteBasket(@RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader,  @PathVariable Integer basketId) {
        basketService.delete(authHeader, basketId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/get")
    public ResponseEntity<List<ArtDto>> get(@RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader){
        return ResponseEntity.ok(basketService.getOwnedBasketItems(authHeader));
    }

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
