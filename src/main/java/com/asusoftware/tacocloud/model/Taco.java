package com.asusoftware.tacocloud.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class Taco {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Date createdAt;
    @NotNull
    // Il Size permette di specificare la dimensione minima o massima ed il messaggio da mostrare in caso non si rispetti questa condizione
    @Size(min = 5, message = "Name must be at least 5 characters long")
    private String name;
    @ManyToMany(targetEntity = Ingredient.class)
    @Size(min = 1, message = "You must choose at least 1 ingredient")
    private List<Ingredient> ingredients;

    // Genera automaticamente la data, senza che la settiamo noi manualmente
    @PrePersist
    void createdAt() {
        this.createdAt = new Date();
    }
}
