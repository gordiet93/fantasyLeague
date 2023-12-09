package com.example.fantasyLeague.model;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
public class Selection extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @JsonIdentityReference(alwaysAsId = true)
    @ManyToOne
    @JsonProperty(value = "player_id")
    private Player player;

    private boolean captained;

    public Selection() {
    }

    public Selection(Player player, boolean captained) {
        this.player = player;
        this.captained = captained;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public boolean isCaptained() {
        return captained;
    }

    public void setCaptained(boolean captained) {
        this.captained = captained;
    }
}
