package org.gamify.gym.app.friendship.model;

import java.time.LocalDate;
import java.util.UUID;

import org.gamify.gym.app.user.model.Player;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Friendship {
    public enum Status {
        PENDING, OK, BLOCKED, DECLINED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long friendshipId;

    @ManyToOne
    @JoinColumn(name = "playerId")
    private Player player;

    @ManyToOne
    @JoinColumn(name = "friendId")
    private Player friend;

    @Column
    private LocalDate sinceWhen;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Column
    private LocalDate createdOn;

    @Column(unique = true)
    private UUID friendCode;

    public Long getFriendshipId() {
        return friendshipId;
    }

    public void setFriendshipId(Long friendshipId) {
        this.friendshipId = friendshipId;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getFriend() {
        return friend;
    }

    public void setFriend(Player friend) {
        this.friend = friend;
    }

    public LocalDate getSinceWhen() {
        return sinceWhen;
    }

    public void setSinceWhen(LocalDate sinceWhen) {
        this.sinceWhen = sinceWhen;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDate getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDate createdOn) {
        this.createdOn = createdOn;
    }

    public UUID getFriendCode() {
        return friendCode;
    }

    public void setFriendCode(UUID friendCode) {
        this.friendCode = friendCode;
    }

}
