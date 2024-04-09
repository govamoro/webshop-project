package com.radnoti.webshop.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor

public class BasketDto {
    private Integer id;
    private Integer user_id;
    private Integer art_id;
}
