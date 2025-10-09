package org.gamify.gym.app.dieta.model;

import java.util.List;

import org.gamify.gym.app.user.model.Player;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Dieta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @ManyToOne
    @JoinColumn(name = "player_id")
    @JsonBackReference
    private Player player;

    @OneToMany(mappedBy = "dieta", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Alimento> alimentos;

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

    public List<Alimento> getAlimentos() {
        return alimentos;
    }

    public void setAlimentos(List<Alimento> alimentos) {
        this.alimentos = alimentos;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
