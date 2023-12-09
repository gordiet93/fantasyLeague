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
    private Long id;

//    @JsonProperty("position_id")
//    @Enumerated(EnumType.STRING)
//    private Position position;

    @JsonProperty("display_name")
    private String name;

    @ManyToOne
    private Team team;

    public Player(){}

    public Player(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

//    public Position getPosition() {
//        return position;
//    }
//
//    public void setPosition(Position position) {
//        this.position = position;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return id.equals(player.id);
    }
}
