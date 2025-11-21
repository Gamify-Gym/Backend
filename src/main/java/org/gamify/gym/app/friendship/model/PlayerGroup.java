package org.gamify.gym.app.friendship.model;

import org.gamify.gym.app.user.model.Player;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class PlayerGroup {

    @Id @GeneratedValue
    private Long id_playerGroup;

    @ManyToOne
    @JoinColumn(name = "player_id")
    private Player player;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;

    public Long getId_playerGroup() {
        return id_playerGroup;
    }

    public void setId_playerGroup(Long id_playerGroup) {
        this.id_playerGroup = id_playerGroup;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    
}
