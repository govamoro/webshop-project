package com.radnoti.webshop.controller;

import com.radnoti.webshop.model.dto.ArtDto;
import com.radnoti.webshop.model.entity.Art;
import com.radnoti.webshop.service.ArtService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/product")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ArtController {

    @Autowired
    private ArtService artService;

    @PostMapping("/add")
    public Art addProduct(@RequestBody ArtDto artDto){
        return artService.saveProduct(artDto);
    }

    @GetMapping("/{productId}")
    public Art getProduct(@PathVariable Integer productId) {
        return artService.getProductById(productId);
    }
}
