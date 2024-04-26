package com.radnoti.webshop.service;

import com.radnoti.webshop.mapper.ArtMapper;
import com.radnoti.webshop.mapper.BasketMapper;
import com.radnoti.webshop.model.dto.ArtDto;
import com.radnoti.webshop.model.dto.BasketDto;
import com.radnoti.webshop.model.dto.ResponseDto;
import com.radnoti.webshop.model.entity.Art;
import com.radnoti.webshop.model.entity.Basket;
import com.radnoti.webshop.model.entity.User;
import com.radnoti.webshop.repository.ArtRepository;
import com.radnoti.webshop.repository.BasketRepository;
import com.radnoti.webshop.repository.UserRepository;
import com.radnoti.webshop.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BasketService{

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private BasketRepository basketRepository;
    @Autowired
    private BasketMapper basketMapper;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ArtRepository artRepository;
    @Autowired
    private ArtMapper artMapper;



    public ResponseDto saveBasket(String authHeader, ArtDto artDto) {
        Integer artId = artDto.getId();
        Optional<Art> optionalArt = artRepository.findById(artId);
        if (optionalArt.isEmpty()){
            throw new RuntimeException("nincs ilyen art");
        }
        Art art = optionalArt.get();
        Basket basket = new Basket();
        Integer userId = jwtUtil.getIdFromAuthHeader(authHeader);
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty()){
            throw new RuntimeException("nincs ilyen user");
        }

        basket.setUser(optionalUser.get());
        Basket savedBasket = basketRepository.save(basket);
        art.setBasket(savedBasket);
        artRepository.save(art);
        return new ResponseDto(savedBasket.getId());
    }



    public void delete(String authHeader, Integer productId) {
            Integer userId = jwtUtil.getIdFromAuthHeader(authHeader);
            Optional<Basket> byId = basketRepository.findById(productId);

            if (byId.isEmpty()){
                throw new RuntimeException("nincs ilyen art");
            }
            Basket basket = byId.get();
            if (basket.getUser().getId() != userId){
                throw new RuntimeException("nem a tied te fadz");
            }

            basketRepository.delete(basket);
    }


    public List<ArtDto> getOwnedBasketItems(String authHeader){
        Integer userId = jwtUtil.getIdFromAuthHeader(authHeader);
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty()){
            throw new RuntimeException("nincs ilyen user");
        }
        List<Integer> basketIdList = new ArrayList<>();
        List<Basket> ownedBaskets = basketRepository.getOwnedBaskets(userId);
        for (Basket ownedBasket : ownedBaskets) {
            basketIdList.add(ownedBasket.getId());
        }

        List<Art> artList = artRepository.findByBasketList(basketIdList);
        List<ArtDto> artDtoList = artMapper.fromEntityToDto(artList);
        return artDtoList;
    }

}



