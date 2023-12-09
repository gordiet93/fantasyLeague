package com.example.fantasyLeague.model.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum EventType {
    SUBSTITUTION,
    YELLOWCARD,
    YELLOWREDCARD,
    REDCARD,
    GOAL,
    PENALTY,
    OWNGOAL,
    MISSED_PENALTY,
    PENALTY_SHOOTOUT_MISS,
    VAR,
    VAR_CARD
}
