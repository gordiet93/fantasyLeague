package com.example.fantasyLeague.model;
import com.example.fantasyLeague.model.enums.Position;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import jakarta.persistence.*;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
public class Player extends AuditModel {

    @Id
    @JsonProperty("player_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private Position position;

    @JsonProperty("display_name")
    private String name;

    @JsonProperty("nationality")
    private String nationality;

    @ManyToOne
    private Team team;

    public Player(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    @JsonProperty("position")
    private void setDetails(JsonNode node) {
        position = Position.valueOf(node.get("data").get("name").asText().toUpperCase());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return id.equals(player.id);
    }
}
