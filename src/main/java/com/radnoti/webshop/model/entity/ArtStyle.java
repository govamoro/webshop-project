package com.radnoti.webshop.model.entity;

import javax.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "art_style")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class ArtStyle implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;

    @JoinColumn(name = "art", referencedColumnName = "id")
    //@ManyToMany
    private Art artId;

    @JoinColumn(name = "style", referencedColumnName = "id")
    //@ManyToMany
    private Style styleId;
}
