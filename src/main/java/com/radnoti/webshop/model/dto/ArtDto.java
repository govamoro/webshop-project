package com.radnoti.webshop.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ArtDto {

    private Integer id;
    private String title;
    private String artist;
    private Integer price;
    private String description;
    private String imgUrl;
    private Integer createdYear;
    private float xCm;
    private float yCm;
    private List<MaterialDto> material;
    private List<StyleDto> style;

}
