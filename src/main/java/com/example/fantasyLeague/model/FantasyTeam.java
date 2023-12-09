package com.example.fantasyLeague.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

import java.util.HashSet;
import java.util.List;

@Entity
public class FantasyTeam {

    @Id
    private Long id;

    @OneToOne
    private User user;

    private String name;

    private Integer totalPoints;

    @OneToMany
    private List<FantasyTeamGameweek> fantasyTeamGameweeks;

}
