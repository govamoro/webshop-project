package com.radnoti.webshop.model.entity;

import javax.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "style")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Style implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

}
