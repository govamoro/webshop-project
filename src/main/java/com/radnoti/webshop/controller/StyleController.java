package com.radnoti.webshop.controller;

import com.radnoti.webshop.model.dto.StyleDto;
import com.radnoti.webshop.service.StyleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/style")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")

public class StyleController {
    private final StyleService styleService;

    @GetMapping("/get-all")
    public ResponseEntity<List<StyleDto>> getAllStyles() {
        List<StyleDto> styleDtos = styleService.getAllStyles();
        return ResponseEntity.ok(styleDtos);
    }
}
