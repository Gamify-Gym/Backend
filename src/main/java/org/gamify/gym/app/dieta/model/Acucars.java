package org.gamify.gym.app.dieta.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Acucars {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Double acucarTotal;

    @Column
    private Double acucarAdicionado;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getAcucarTotal() {
        return acucarTotal;
    }

    public void setAcucarTotal(Double acucarTotal) {
        this.acucarTotal = acucarTotal;
    }

    public Double getAcucarAdicionado() {
        return acucarAdicionado;
    }

    public void setAcucarAdicionado(Double acucarAdicionado) {
        this.acucarAdicionado = acucarAdicionado;
    }
}
