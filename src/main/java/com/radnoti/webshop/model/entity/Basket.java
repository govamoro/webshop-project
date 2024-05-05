package com.radnoti.webshop.model.entity;

import lombok.*;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

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

    @OneToOne
    private User user;

}
