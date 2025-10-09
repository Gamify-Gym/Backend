package org.gamify.gym.app.dieta.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Gorduras {

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getGorduraTrans() {
        return gorduraTrans;
    }

    public void setGorduraTrans(Double gorduraTrans) {
        this.gorduraTrans = gorduraTrans;
    }

    public Double getGordurasMonosaturadas() {
        return gordurasMonosaturadas;
    }

    public void setGordurasMonosaturadas(Double gordurasMonosaturadas) {
        this.gordurasMonosaturadas = gordurasMonosaturadas;
    }

    public Double getGordurasPoliinsaturadas() {
        return gordurasPoliinsaturadas;
    }

    public void setGordurasPoliinsaturadas(Double gordurasPoliinsaturadas) {
        this.gordurasPoliinsaturadas = gordurasPoliinsaturadas;
    }

    public Double getGordurasSaturadas() {
        return gordurasSaturadas;
    }

    public void setGordurasSaturadas(Double gordurasSaturadas) {
        this.gordurasSaturadas = gordurasSaturadas;
    }
}
