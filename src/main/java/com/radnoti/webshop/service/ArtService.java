package com.radnoti.webshop.service;

import com.radnoti.webshop.mapper.ArtMapper;
import com.radnoti.webshop.model.dto.ArtDto;
import com.radnoti.webshop.model.dto.ResponseDto;
import com.radnoti.webshop.model.entity.Art;
import com.radnoti.webshop.repository.ArtRepository;
import com.radnoti.webshop.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class ArtService {


    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private ArtRepository artRepository;
    @Autowired
    private ArtMapper artMapper;

    public ResponseDto saveProduct(ArtDto artDto) {
        Art art = artMapper.fromDtoToEntity(artDto);
        Art save = artRepository.save(art);
        return new ResponseDto(save.getId());
    }

    public Art getProductById(Integer id) {
        return artRepository.findById(id).orElse(null);
    }

    public void delete(String authHeader, Integer productId) {
        //n+1 problema
        Integer userId = jwtUtil.getIdFromAuthHeader(authHeader);
        Optional<Art> byId = artRepository.findById(productId);

        if (byId.isEmpty()){
            throw new RuntimeException("nincs ilyen art");
        }
        Art art = byId.get();

        if (art.getUser().getId() != userId){
            throw new RuntimeException("nem a tied te fadz");
        }

        artRepository.delete(art);
    }

    public List<Art> getOwnedProducts(String authHeader) {
        Integer userId = jwtUtil.getIdFromAuthHeader(authHeader);
         return artRepository.findProductsByUserId(userId);
    }
}
