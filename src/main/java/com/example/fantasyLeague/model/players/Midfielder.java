package com.example.fantasyLeague.model.players;

import org.springframework.stereotype.Component;

@Component
public class Midfielder extends Player {
    private static final int CLEAN_SHEET_POINTS = 1;
    private static final int GOAL_POINTS = 4;

    Midfielder(String name) {
        super(Position.MIDFIELDER, name);
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
