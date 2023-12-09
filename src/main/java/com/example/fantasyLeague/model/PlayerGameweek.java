package com.example.fantasyLeague.model;

import com.example.fantasyLeague.model.enums.LineUpType;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
public class PlayerGameweek extends AuditModel {

    @EmbeddedId
    private PlayerGameweekId playerGameweekId;

    @ManyToOne
    @JoinColumn(name = "player_id", insertable = false, updatable = false)
    private Player player;

    @JsonProperty("team_id")
    @ManyToOne
    private Team team;

    @ManyToOne
    @JoinColumn(name = "fixture_id", insertable = false, updatable = false)
    private Fixture fixture;

    @JsonProperty("type")
    @Enumerated(EnumType.STRING)
    private LineUpType lineUpType;

    @JsonIgnore
    private Gameweek gameweek;

    private Integer goalsScored;
    private Integer assists;
    private Integer ownGoals;
    private Integer yellowCards;
    private Integer redCards;
    private Integer yellowRedCards;
    private Integer missedPens;
    private Integer pensScored;
    private Long subbedOnEventId;
    private Long subbedOffEventId;
    private Boolean cleanSheet;
    private Integer goalsConceded;
    private Double rating;
    private Integer bonus;
    private Integer minutesPlayed;
    private Integer points;

    @JsonCreator
    public PlayerGameweek(@JsonProperty("player_id") final Player player,
                          @JsonProperty("fixture_id") final Fixture fixture) {
        this.player = player;
        this.fixture = fixture;
        this.playerGameweekId = new PlayerGameweekId(player.getId(), fixture.getId());
    }

    public PlayerGameweek(Player player, Fixture fixture, Gameweek gameweek) {
        this.player = player;
        this.fixture = fixture;
        this.playerGameweekId = new PlayerGameweekId(player.getId(), fixture.getId());
        this.gameweek = gameweek;
        this.goalsConceded = 0;
        this.goalsScored = 0;
        this.pensScored = 0;
        this.missedPens = 0;
        this.ownGoals = 0;
        this.redCards = 0;
        this.yellowRedCards = 0;
        this.yellowCards = 0;
        this.minutesPlayed = 0;
        this.assists = 0;
        this.bonus = 0;
        this.cleanSheet = false;
        this.points = 0;
        this.rating = 0.0;
    }



}
