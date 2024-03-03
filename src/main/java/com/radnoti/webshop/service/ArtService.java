package com.radnoti.webshop.service;

import com.radnoti.webshop.mapper.ArtMapper;
import com.radnoti.webshop.model.dto.ArtDto;
import com.radnoti.webshop.model.entity.Art;
import com.radnoti.webshop.repository.ArtRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ArtService {

    @Autowired
    private ArtRepository artRepository;
    @Autowired
    private ArtMapper artMapper;

    public Art saveProduct(ArtDto artDto) {
        Art art = artMapper.fromDtoToEntity(artDto);
        return artRepository.save(art);
    }

    public Art getProductById(Integer id) {
        return artRepository.findById(id).orElse(null);
    }

}
