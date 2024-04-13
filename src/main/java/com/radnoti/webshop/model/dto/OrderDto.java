package com.radnoti.webshop.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class OrderDto {
    private Integer id;
    private Integer user_id;
    private Integer material_id;
    private Integer size;
    private String description;
    private String upload;
}
