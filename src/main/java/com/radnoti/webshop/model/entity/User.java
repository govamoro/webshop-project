package com.radnoti.webshop.model.entity;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;

@Entity
@Table(name = "user")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false) //ez lehet nem kell
    @Column(name = "id")
    private Integer id;

    //@Size(max = 255)
    @JoinColumn(name = "role", referencedColumnName = "id")
    @ManyToOne
    private Role role;

    @Column(name = "username")
    private String userName;

    @Column(name = "email")
    private String email;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "password")
    private String password;

    @Column(name = "registered_at")
    private ZonedDateTime registeredAt;

    @Column(name = "deleted")
    private Boolean isDeleted;

    @Column(name = "deleted_at")
    private ZonedDateTime deletedAt;

}
