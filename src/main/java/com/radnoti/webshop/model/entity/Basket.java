package com.radnoti.webshop.model.entity;

import lombok.*;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "basket")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString

public class Basket implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;

    @Column(name = "user_id")
    private String user_id;

    @Column(name = "art_id")
    private String art_id;
}
