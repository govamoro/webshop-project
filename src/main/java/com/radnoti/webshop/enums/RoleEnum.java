package com.radnoti.webshop.enums;

public enum RoleEnum {
    ADMIN(1, Types.ADMIN),
    USER(2, Types.USER);



    private final Integer id;
    private final String type;

    RoleEnum(Integer id, String type) {
        this.id = id;
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public String getType() {
        return type;
    }


    public class Types {
        public static final String ADMIN = "ADMIN";
        public static final String USER = "USER";
    }
}
