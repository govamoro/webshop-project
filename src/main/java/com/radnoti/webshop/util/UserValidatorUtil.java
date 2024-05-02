package com.radnoti.webshop.util;

import org.springframework.security.core.parameters.P;

public class UserValidatorUtil {

    public static boolean validateUser(Integer id1, Integer id2){
        return !id1.equals(id2);
    }

}
