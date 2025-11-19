package org.gamify.gym.app.dieta.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class Alimento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private Double calories;

    @Column
    private Double fats;

    @Column
    private Double carbs;

    @Column
    private Double fibers;

    @Column
    private Double sodium;

    @Column
    private Double proteins;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "acucars_id")
    private Acucars acucars;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "gorduras_id")
    private Gorduras gorduras;

    @ManyToOne
    @JoinColumn(name = "dietaId")
    @JsonBackReference
    private Dieta dieta;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getCalories() {
        return calories;
    }

    public void setCalories(Double calories) {
        this.calories = calories;
    }

    public Double getFats() {
        return fats;
    }

    public void setFats(Double fats) {
        this.fats = fats;
    }

    public Double getCarbs() {
        return carbs;
    }

    public void setCarbs(Double carbs) {
        this.carbs = carbs;
    }

    public Double getFibers() {
        return fibers;
    }

    public void setFibers(Double fibers) {
        this.fibers = fibers;
    }

    public Double getSodium() {
        return sodium;
    }

    public void setSodium(Double sodium) {
        this.sodium = sodium;
    }

    public Double getProteins() {
        return proteins;
    }

    public void setProteins(Double proteins) {
        this.proteins = proteins;
    }

    public Dieta getDieta() {
        return dieta;
    }

    public void setDieta(Dieta dieta) {
        this.dieta = dieta;
    }

    public Acucars getAcucars() {
        return acucars;
    }

    public void setAcucars(Acucars acucars) {
        this.acucars = acucars;
    }

    public Gorduras getGorduras() {
        return gorduras;
    }

    public void setGorduras(Gorduras gorduras) {
        this.gorduras = gorduras;
    }

}
