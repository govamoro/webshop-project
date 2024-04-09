package com.radnoti.webshop.controller;

import com.radnoti.webshop.model.dto.BasketDto;
import com.radnoti.webshop.service.BasketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @DeleteMapping("/delete/{basketId}")
    public ResponseEntity<Void> deleteBasket(@PathVariable Integer basketId) {
        basketService.deleteBasketById(basketId);
        return ResponseEntity.noContent().build();
    }
}
