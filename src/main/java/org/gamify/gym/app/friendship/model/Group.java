package org.gamify.gym.app.friendship.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_group;

    @Column
    private String nameGroup;

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL)
    private Set<PlayerGroup> players = new HashSet<>();

        public Long getId_group() {
        return id_group;
    }

    public void setId_group(Long id_group) {
        this.id_group = id_group;
    }

    public String getNameGroup() {
        return nameGroup;
    }

    public void setNameGroup(String nameGroup) {
        this.nameGroup = nameGroup;
    }

    public Set<PlayerGroup> getPlayers() {
        return players;
    }

    public void setPlayers(Set<PlayerGroup> players) {
        this.players = players;
    }
}
