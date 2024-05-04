package com.radnoti.webshop.service;

import com.radnoti.webshop.ValamilyenException;
import com.radnoti.webshop.mapper.ArtMapper;
import com.radnoti.webshop.mapper.BasketMapper;
import com.radnoti.webshop.model.dto.ArtDto;
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

import javax.transaction.Transactional;
import java.beans.Transient;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.radnoti.webshop.util.UserValidatorUtil.validateUser;

@Service
@RequiredArgsConstructor
public class BasketService {

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
        if (optionalArt.isEmpty()) {
            throw new RuntimeException("nincs ilyen art");
        }
        Art art = optionalArt.get();
        Basket basket = new Basket();
        Integer userId = jwtUtil.getIdFromAuthHeader(authHeader);
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty()) {
            throw new RuntimeException("nincs ilyen user");
        }

        /*if(optionalArt.isPresent()){
            throw new RuntimeException("Már a kosárban van a termék");
        }*/

        basket.setUser(optionalUser.get());
        Basket savedBasket = basketRepository.save(basket);
        art.setBasket(savedBasket);
        artRepository.save(art);
        return new ResponseDto(savedBasket.getId());
    }

    /*public ResponseDto saveBasket(String authHeader, ArtDto artDto) {
        Integer artId = artDto.getId();
        Optional<Art> optionalArt = artRepository.findById(artId);
        if (optionalArt.isEmpty()) {
            throw new RuntimeException("Nincs ilyen art");
        }
        Art art = optionalArt.get();

        Integer userId = jwtUtil.getIdFromAuthHeader(authHeader);
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty()) {
            throw new RuntimeException("Nincs ilyen user");
        }
        User user = optionalUser.get();

        // Ellenőrizzük, hogy az adott felhasználó kosarában van-e már az adott termék
        Optional<Basket> optionalBasket = basketRepository.findByUserAndArt(user, art);
        if (optionalBasket.isPresent()) {
            throw new RuntimeException("Már a kosárban van a termék");
        }

        Basket basket = new Basket();
        basket.setUser(user);
        Basket savedBasket = basketRepository.save(basket);
        art.setBasket(savedBasket);
        artRepository.save(art);
        return new ResponseDto(savedBasket.getId());
    }*/


    @Transactional
    public void removeArtFromBasket(String authHeader, ArtDto artDto) throws ValamilyenException {
        Integer userId = jwtUtil.getIdFromAuthHeader(authHeader);
        Optional<Art> optionalArt = artRepository.findById(artDto.getId());
        Optional<Basket> optionalBasket = basketRepository.findById(artDto.getBasket().getId());

        if (optionalBasket.isEmpty()) {
            throw new ValamilyenException("nincs ilyen kosar");
        }

        if (validateUser(optionalBasket.get().getUser().getId(), userId)) {
            throw new ValamilyenException("invalid user");
        }

        if (optionalArt.isEmpty()) {
            throw new ValamilyenException("nincs ilyen art");
        }

        Art art = optionalArt.get();
        Basket basket = optionalBasket.get();

        artRepository.removeArtFromBasket(art.getId());
        basketRepository.delete(basket);
    }

    @Transactional
    public void deleteWholeBasket(String authHeader, Integer basketId) throws ValamilyenException {

        Integer userId = jwtUtil.getIdFromAuthHeader(authHeader);
        Optional<Basket> optionalBasket = basketRepository.findById(basketId);

        if (optionalBasket.isEmpty()) {
            throw new ValamilyenException("nincs ilyen kosar");
        }
        Basket basket = optionalBasket.get();
        if (basket.getUser().getId() != userId) {
            throw new RuntimeException("nem a tied");
        }

        artRepository.deleteWholeBasket(basketId);
        basketRepository.delete(basket);
    }


    public List<ArtDto> getOwnedBasketItems(String authHeader) {
        Integer userId = jwtUtil.getIdFromAuthHeader(authHeader);
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty()) {
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



