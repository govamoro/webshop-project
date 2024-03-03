package com.radnoti.webshop.service;

import com.radnoti.webshop.enums.RoleEnum;
import com.radnoti.webshop.mapper.UserMapper;
import com.radnoti.webshop.model.dto.ResponseDto;
import com.radnoti.webshop.model.dto.UserDto;
import com.radnoti.webshop.model.entity.Role;
import com.radnoti.webshop.model.entity.User;
import com.radnoti.webshop.repository.UserRepository;
import com.radnoti.webshop.util.HashUtil;
import com.radnoti.webshop.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.time.ZonedDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    //@Autowired - injektálás!!!4!
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final HashUtil hashUtil;
    private final JwtUtil jwtUtil;

    public ResponseDto registration(UserDto userDto) throws NoSuchAlgorithmException {
        if (userDto.getUserName() == null || userDto.getUserName().isBlank()
            || userDto.getEmail() == null || userDto.getEmail().isBlank()
            || userDto.getPassword() == null || userDto.getPassword().isBlank()
            /*|| userDto.getFirstName() == null || userDto.getFirstName().isBlank()
            || userDto.getLastName() == null || userDto.getLastName().isBlank()*/){
            throw new RuntimeException("sasdasdas");
        }
        if (userRepository.findByUsername(userDto.getUserName()).isPresent()){
            throw new RuntimeException("van már ilyen username");
        }
        if (userRepository.findByEmail(userDto.getEmail()).isPresent()) {
            throw new RuntimeException("van már ilyen email");
        }

        ZonedDateTime now = ZonedDateTime.now();
        User user = userMapper.fromDtoToEntity(userDto);
        user.setPassword(hashUtil.getSHA256Hash(userDto.getPassword()));
        user.setRegisteredAt(now);
        user.setIsDeleted(false);
        user.setRole(new Role(RoleEnum.USER.getId()));
        userRepository.save(user);


        /*User user = new User();
        user.setEmail(userDto.getEmail());
        userRepository.save(user);*/

        return new ResponseDto(user.getId());
    }

    public ResponseDto login(UserDto userDto) throws NoSuchAlgorithmException {
        if (userDto.getUserName() == null || userDto.getUserName().isBlank()
                || userDto.getPassword() == null || userDto.getPassword().isBlank()){
            throw new RuntimeException("hibások az adatok");
        }
        Optional<User> byUsername = userRepository.findByUsername(userDto.getUserName());
        if (byUsername.isEmpty()){
            throw new RuntimeException("nincs ilyen user");
        }
        if (!byUsername.get().getPassword().equals(hashUtil.getSHA256Hash(userDto.getPassword()))){
            throw new RuntimeException("rossz jelszó");
        }

        return new ResponseDto(jwtUtil.generateJwt(byUsername.get()));
    }
}

/* Írjak egy mappert, ami a userből csinál userDto-t és fordítva

* */