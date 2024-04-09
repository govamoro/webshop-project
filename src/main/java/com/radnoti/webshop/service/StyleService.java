package com.radnoti.webshop.service;

import com.radnoti.webshop.repository.StyleRepository;
import com.radnoti.webshop.mapper.StyleMapper;
import com.radnoti.webshop.model.dto.StyleDto;
import com.radnoti.webshop.model.entity.Style;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StyleService {
    private StyleRepository styleRepository;
    private StyleMapper styleMapper;

    public StyleService(StyleRepository styleRepository, StyleMapper styleMapper) {
        this.styleRepository = styleRepository;
        this.styleMapper = styleMapper;
    }

    public List<StyleDto> getAllStyles() {
        Iterable<Style> stylesIterable = styleRepository.findAll();
        List<Style> styles = new ArrayList<>();
        stylesIterable.forEach(styles::add);

        return styles.stream()
                .map(styleMapper::fromEntityToDto)
                .collect(Collectors.toList());
    }
}
