package com.radnoti.webshop.controller;

import com.radnoti.webshop.model.dto.StyleDto;
import com.radnoti.webshop.model.entity.Style;
import com.radnoti.webshop.service.StyleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/style")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")

public class StyleController {
    private final StyleService styleService;

    @PostMapping("/add")
    public ResponseEntity<StyleDto> addStyle(@RequestBody StyleDto styleDto) {
        StyleDto savedStyleDto = styleService.saveStyle(styleDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedStyleDto);
    }

    @GetMapping("/get/{styleId}")
    public ResponseEntity<StyleDto> getStyle(@PathVariable Integer styleId) {
        StyleDto styleDto = styleService.getStyleById(styleId);
        return ResponseEntity.ok(styleDto);
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<StyleDto>> getAllStyles() {
        List<StyleDto> styleDtos = styleService.getAllStyles();
        return ResponseEntity.ok(styleDtos);
    }

    @DeleteMapping("/delete/{styleId}")
    public ResponseEntity<Void> deleteStyle(@PathVariable Integer styleId) {
        styleService.deleteStyle(styleId);
        return ResponseEntity.noContent().build();
    }
}
