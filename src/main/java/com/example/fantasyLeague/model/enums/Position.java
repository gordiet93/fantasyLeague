package com.example.fantasyLeague.model.enums;

public enum Position {
    GOALKEEPER (24, 7, 4),
    DEFENDER (25, 6, 4),
    MIDFIELDER (26, 5, 1),
    ATTACKER (27, 4, 0);

    private final int apiCode;
    private final int goalPoints;
    private final int cleanSheetPoints;

    Position(int apiCode, int goalPoints, int cleanSheetPoints) {
        this.apiCode = apiCode;
        this.goalPoints = goalPoints;
        this.cleanSheetPoints = cleanSheetPoints;
    }

    public int getApiCode() {
        return apiCode;
    }

    public int getGoalPoints() {
        return goalPoints;
    }

    public int getCleanSheetPoints() {
        return cleanSheetPoints;
    }

    public static Position fromApiCode(int code) {
        for (Position position : Position.values()) {
            if (position.getApiCode() == code) {
                return position;
            }
        }
        throw new IllegalArgumentException("No matching position for code: " + code);
    }
}
