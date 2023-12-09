package com.example.fantasyLeague.model;

import com.example.fantasyLeague.deserializer.FixtureDeserializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.Date;
import java.util.List;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Gameweek extends AuditModel {

    @Id
    private Long id;

    @JsonProperty(value = "starting_at")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date startDate;

    @JsonProperty(value = "ending_at")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date endDate;

    @JsonProperty("fixtures")
    @JsonDeserialize(using = FixtureDeserializer.class)
    @OneToMany(mappedBy = "gameweek", cascade = CascadeType.MERGE)
    private List<Fixture> fixtures;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Fixture> getFixtures() {
        return fixtures;
    }

    public void setFixtures(List<Fixture> fixtures) {
        this.fixtures = fixtures;
    }
}
