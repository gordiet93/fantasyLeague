package com.example.fantasyLeague.model;

import com.example.fantasyLeague.deserializer.FixtureDeserializer;
import com.example.fantasyLeague.model.enums.FixtureStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@JsonDeserialize(using = FixtureDeserializer.class)
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
public class Fixture {

    @Id
    private Long id;

    @ManyToOne
    private Gameweek gameweek;

    @ManyToOne
    private Team homeTeam;

    @ManyToOne
    private Team awayTeam;

    private Integer homeTeamScore;

    private Integer awayTeamScore;

    @OneToMany(mappedBy = "fixture", cascade = CascadeType.ALL)
    private List<Event> events;

    @OneToMany(mappedBy = "fixture", cascade = CascadeType.MERGE)
    private List<PlayerGameweek> playerGameweeks;

    @Enumerated(EnumType.STRING)
    private FixtureStatus status;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy hh:mm:ss")
    private Date startDateTime;

    public Fixture() {}

    public Fixture(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Gameweek getGameweek() {
        return gameweek;
    }

    public void setGameweek(Gameweek gameweek) {
        this.gameweek = gameweek;
    }

    public Team getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(Team homeTeam) {
        this.homeTeam = homeTeam;
    }

    public Team getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(Team awayTeam) {
        this.awayTeam = awayTeam;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public List<PlayerGameweek> getPlayerGameweeks() {
        return playerGameweeks;
    }

    public void setPlayerGameweeks(List<PlayerGameweek> playerGameweeks) {
        this.playerGameweeks = playerGameweeks;
    }

    public FixtureStatus getStatus() {
        return status;
    }

    public void setStatus(FixtureStatus status) {
        this.status = status;
    }

    public Date getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(Date startDateTime) {
        this.startDateTime = startDateTime;
    }

    public Integer getHomeTeamScore() {
        return homeTeamScore;
    }

    public void setHomeTeamScore(Integer homeTeamScore) {
        this.homeTeamScore = homeTeamScore;
    }

    public Integer getAwayTeamScore() {
        return awayTeamScore;
    }

    public void setAwayTeamScore(Integer awayTeamScore) {
        this.awayTeamScore = awayTeamScore;
    }
}
