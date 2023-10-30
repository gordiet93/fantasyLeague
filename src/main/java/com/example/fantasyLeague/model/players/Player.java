package com.example.fantasyLeague.model.players;

public abstract class Player {
    Position position;
    String name;

    protected Player(Position position, String name) {
        this.position = position;
        this.name = name;
    }
    abstract int getGoalPoints();
    abstract int getCleanSheetPoints();

    public Position getPosition() {
        return position;
    }
}
