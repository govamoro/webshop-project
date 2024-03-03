package com.radnoti.webshop.model.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "art")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Art implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;

    @Column(name = "title")
    private String title;

    @Column(name = "artist")
    private String artist;

    @Column(name = "price")
    private Integer price;

    @Column(name = "description")
    private String description;

    @Column(name = "img_url")
    private String imgUrl;

    @Column(name = "created_year")
    private Integer CreatedYear;

    @Column(name = "x_cm")
    private float xCm;

    @Column(name = "y_cm")
    private float yCm;

    @Column(name = "material")
    private Integer material;

}
