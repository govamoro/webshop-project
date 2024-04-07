package com.radnoti.webshop.controller;

import com.radnoti.webshop.model.dto.MaterialDto;
import com.radnoti.webshop.service.MaterialService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/material")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")

public class MaterialController {
    private final MaterialService materialService;

    @GetMapping("/get-all")
    public ResponseEntity<List<MaterialDto>> getAllMaterials() {
        List<MaterialDto> materialDtos = materialService.getAllMaterials();
        return ResponseEntity.ok(materialDtos);
    }

}
