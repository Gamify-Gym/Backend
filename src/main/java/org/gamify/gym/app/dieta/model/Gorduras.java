package org.gamify.gym.app.dieta.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Gorduras extends Alimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Double gorduraTrans;

    @Column
    private Double gordurasMonosaturadas;

    @Column
    private Double gordurasPoliinsaturadas;

    @Column
    private Double gordurasSaturadas;
}
