package org.gamify.gym.app.user.model;

import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Entity;

@Entity
@Table(name = "nutritionists")
public class Nutritionist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_nutritionist;

    @Column(nullable = false, unique = true, length = 7)
    private long crn;

    @Column(nullable = false, unique = true, length = 11)
    private long cpf;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public long getId_nutritionist() {
        return id_nutritionist;
    }

    public long getCrn() {
        return crn;
    }

    public void setCrn(long crn) {
        this.crn = crn;
    }

    public long getCpf() {
        return cpf;
    }

    public void setCpf(long cpf) {
        this.cpf = cpf;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
