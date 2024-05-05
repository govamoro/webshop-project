package com.radnoti.webshop.controller;

import com.radnoti.webshop.ValamilyenException;
import com.radnoti.webshop.model.dto.ArtDto;
import com.radnoti.webshop.model.dto.ResponseDto;
import com.radnoti.webshop.service.BasketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/basket")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class BasketController {

    private final BasketService basketService;

    @PostMapping("/save")
    public ResponseDto saveBasket(@RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader, @RequestBody ArtDto artDto) {
        return basketService.saveBasket(authHeader, artDto);
        //return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/delete/{basketId}")
    public ResponseEntity<Void> deleteBasket(@RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader, @PathVariable Integer basketId) throws ValamilyenException {
        basketService.deleteWholeBasket(authHeader, basketId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/remove-art-from-basket")
    public ResponseEntity<Void> removeArtFromBasket(@RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader, @RequestBody ArtDto artDto) throws ValamilyenException {
        basketService.removeArtFromBasket(authHeader, artDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/get")
    public ResponseEntity<List<ArtDto>> get(@RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader) {
        return ResponseEntity.ok(basketService.getOwnedBasketItems(authHeader));
    }


}
