package com.example.fantasyLeague.model.players;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.List;

@Component
public class PlayerFactory {

    @Autowired
    private Goalkeeper goalkeeper;

    @Autowired
    private Defender defender;

    @Autowired
    private Midfielder midfielder;

    @Autowired
    private Forward forward;

    public Player createPlayer(Position position) {
        switch (position) {
            case GOALKEEPER -> {
                return this.goalkeeper;
            }
            case DEFENDER -> {
                return this.defender;
            }
            case MIDFIELDER -> {
                return this.midfielder;
            }
            case FORWARD -> {
                return this.forward;
            }
            default -> throw new UnsupportedOperationException();
        }
    }
}
