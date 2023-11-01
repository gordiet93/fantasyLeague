package com.example.fantasyLeague.model;
import com.example.fantasyLeague.model.enums.Position;
import jakarta.persistence.*;

@Entity
public class Player {

    @Id
    private Long id;

    @Enumerated(EnumType.STRING)
    private Position position;

    private String name;

    private String nationality;

    @ManyToOne
    private Team team;

    public Player(){}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return id.equals(player.id);
    }
}
