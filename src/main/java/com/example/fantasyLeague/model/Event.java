package com.example.fantasyLeague.model;

import com.example.fantasyLeague.deserializer.EventDeserializer;
import com.example.fantasyLeague.deserializer.TeamDeserializer;
import com.example.fantasyLeague.model.enums.EventType;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.*;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(using = EventDeserializer.class)
@Entity
public class Event extends AuditModel {

    @Id
    private Long id;

    @JsonProperty(value = "player_id")
    @NotFound(action = NotFoundAction.IGNORE)
    @ManyToOne(fetch = FetchType.LAZY)
    private Player player;

    @JsonProperty(value = "related_player_id")
    @NotFound(action = NotFoundAction.IGNORE)
    @ManyToOne(fetch = FetchType.LAZY)
    private Player relatedPlayer;

    @JsonProperty(value = "fixture_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Fixture fixture;

    @JsonProperty(value = "participant_id")
    @NotFound(action = NotFoundAction.IGNORE)
    private Team team;

    private Integer minute;

    @Enumerated(EnumType.STRING)
    @JsonFormat(with = JsonFormat.Feature.ACCEPT_CASE_INSENSITIVE_PROPERTIES)
    private EventType type;

    public Event() {}

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

    public Player getRelatedPlayer() {
        return relatedPlayer;
    }

    public void setRelatedPlayer(Player relatedPlayer) {
        this.relatedPlayer = relatedPlayer;
    }

    public Fixture getFixture() {
        return fixture;
    }

    public void setFixture(Fixture fixture) {
        this.fixture = fixture;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Integer getMinute() {
        return minute;
    }

    public void setMinute(Integer minute) {
        this.minute = minute;
    }

    public EventType getType() {
        return type;
    }

    public void setType(EventType type) {
        this.type = type;
    }
}
