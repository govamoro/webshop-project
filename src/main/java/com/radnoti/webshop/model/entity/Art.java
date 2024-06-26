package com.radnoti.webshop.model.entity;

import lombok.*;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

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
    //@NonNull()
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
    private Integer createdYear;

    @Column(name = "x_cm")
    private float xCm;

    @Column(name = "y_cm")
    private float yCm;

    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne
    private User user;

    @ToString.Exclude
    @ManyToMany
    List<Material> material;

    @ToString.Exclude
    @ManyToMany
    List<Style> style;

    @JoinColumn(name = "basket_id", referencedColumnName = "id")
    @ManyToOne
    private Basket basket;

}
