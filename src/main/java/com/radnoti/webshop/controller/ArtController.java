package com.radnoti.webshop.controller;

import com.radnoti.webshop.enums.RoleEnum;
import com.radnoti.webshop.model.dto.ArtDto;
import com.radnoti.webshop.model.dto.MaterialDto;
import com.radnoti.webshop.model.dto.ResponseDto;
import com.radnoti.webshop.model.entity.Art;
import com.radnoti.webshop.service.ArtService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping(path = "/product")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ArtController {

    @Autowired
    private ArtService artService;

    @PostMapping("/add")
    @RolesAllowed({RoleEnum.Types.ADMIN, RoleEnum.Types.USER})
    public ResponseDto addProduct(@RequestBody ArtDto artDto) {
        return artService.saveProduct(artDto);
    }

    @GetMapping("/get/{productId}")
    public Art getProduct(@PathVariable Integer productId) {
        return artService.getProductById(productId);
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<ArtDto>> getAllArts() {
        List<ArtDto> artDtoList = artService.getAllArts();
        return ResponseEntity.ok(artDtoList);
    }
    
    @RolesAllowed({RoleEnum.Types.ADMIN, RoleEnum.Types.USER})
    @DeleteMapping("/delete/{productId}")
    public void deleteProduct(@RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader, @PathVariable Integer productId) {
        artService.delete(authHeader, productId);
    }

    @RolesAllowed({RoleEnum.Types.ADMIN, RoleEnum.Types.USER})
    @GetMapping("/get-owned-products")
    public ResponseEntity<List<ArtDto>> getOwnedProducts(@RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader) {
        return ResponseEntity.ok(artService.getOwnedProducts(authHeader));
    }
}
