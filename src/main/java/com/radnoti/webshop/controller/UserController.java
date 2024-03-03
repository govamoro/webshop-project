package com.radnoti.webshop.controller;

import com.radnoti.webshop.util.JwtUtil;
import com.radnoti.webshop.enums.RoleEnum;
import com.radnoti.webshop.model.dto.ResponseDto;
import com.radnoti.webshop.model.dto.UserDto;
import com.radnoti.webshop.model.entity.Role;
import com.radnoti.webshop.model.entity.User;
import com.radnoti.webshop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping(path = "/user")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class UserController {
    private final UserService userService;
    private final JwtUtil jwtUtil;
    /*@PostMapping(path = "/test")
    public String adduser() {
        return "HELLOOOOO";

    }*/


    @RolesAllowed({RoleEnum.Types.ADMIN})
    @GetMapping(path = "/jwttest")
    public void test(){
        User user = new User();
        Role role = new Role();
        role.setType(RoleEnum.USER.getType());

        user.setId(50);
        user.setRole(role);
        user.setEmail("valami@valami");
        user.setUserName("valami50");
        user.setFirstName("BoB");
        user.setLastName("BAB");
        String jwt = jwtUtil.generateJwt(user);
        System.out.println(jwt);
        System.out.println(jwtUtil.validateJwt(jwt));
    }

}
