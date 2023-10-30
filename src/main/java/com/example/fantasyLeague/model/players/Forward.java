package com.example.fantasyLeague.model.players;

import org.springframework.stereotype.Component;

@Component
public class Forward extends Player {
    private static final int CLEAN_SHEET_POINTS = 0;
    private static final int GOAL_POINTS = 3;

    Forward(String name) {
        super(Position.FORWARD, name);
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
