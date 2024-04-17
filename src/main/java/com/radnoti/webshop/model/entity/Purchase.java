package com.radnoti.webshop.model.entity;

import javax.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "purchase")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Purchase implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;

    //@Size(max = 255)
    @Column(name = "type")
    private String type;

}
