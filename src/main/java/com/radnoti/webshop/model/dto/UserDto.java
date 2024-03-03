package com.radnoti.webshop.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.ZonedDateTime;
@Getter
@Setter
@ToString
@NoArgsConstructor
public class UserDto {

    private Integer id;
    private String roleName;
    private String userName;
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private ZonedDateTime registeredAt;
    private Boolean isDeleted;
    private ZonedDateTime deletedAt;

}
