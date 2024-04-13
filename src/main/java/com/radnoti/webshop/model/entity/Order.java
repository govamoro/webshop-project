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
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    @NonNull()
    private Integer id;

    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne
    private User user_id;

    @JoinColumn(name = "material_id", referencedColumnName = "id")
    @ManyToOne
    private Material material_id;

    @Column(name = "size")
    private Integer size;

    @Column(name = "description")
    private String description;

    @Column(name = "upload")
    private String upload;
}
