package com.example.fantasyLeague.model.players;

import org.springframework.stereotype.Component;

@Component
public class Defender extends Player {
    private static final int CLEAN_SHEET_POINTS = 4;
    private static final int GOAL_POINTS = 5;

    Defender(String name) {
        super(Position.DEFENDER, name);
    }

    @Override
    public int getCleanSheetPoints() {
        return CLEAN_SHEET_POINTS;
    }
    @Override
    public int getGoalPoints() {
        return GOAL_POINTS;
    }
}
