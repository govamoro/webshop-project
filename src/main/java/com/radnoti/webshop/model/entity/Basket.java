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

    //@JoinColumn(name = "user_id", referencedColumnName = "id")
    //TODO majd adatbázisban 1az1-hez kapcsolatot kell csinálni
    @OneToOne
    private User user;

    @JoinColumn(name = "art_id", referencedColumnName = "id")
    private Art art;
}
