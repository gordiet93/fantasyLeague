package com.example.fantasyLeague.deserializer;

import com.example.fantasyLeague.model.Fixture;
import com.example.fantasyLeague.model.Gameweek;
import com.example.fantasyLeague.model.Team;
import com.example.fantasyLeague.model.enums.FixtureStatus;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FixtureDeserializer extends JsonDeserializer<Fixture> {

    @Override
    public Fixture deserialize (JsonParser jsonParser,
                                DeserializationContext deserializationContext) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode fixtureNode = mapper.readTree(jsonParser);

        Fixture fixture = new Fixture();
        fixture.setId(fixtureNode.get("id").asLong());

        DateFormat df = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");

        try {
            Date date = df.parse(fixtureNode.get("starting_at").asText());
            fixture.setStartDateTime(date);
        } catch (ParseException e) {
           System.err.println(e.getLocalizedMessage());
        }

        String statusText = fixtureNode.get("state").get("state").asText();
        try {
            FixtureStatus fixtureStatus = FixtureStatus.valueOf(statusText);
            fixture.setStatus(fixtureStatus);
        } catch (IllegalArgumentException e) {
            System.err.println("Invalid FixtureStatus: " + statusText);
        }

        Gameweek gameweek = new Gameweek();
        gameweek.setId(fixtureNode.get("round_id").asLong());

        JsonNode participantsNode = fixtureNode.get("participants");

        Long homeParticipantId = null;
        Long awayParticipantId = null;

        for (JsonNode participantNode : participantsNode) {
            Long participantId = participantNode.get("id").asLong();

            JsonNode metaNode = participantNode.get("meta");

            String location = metaNode.get("location").asText();

            if ("home".equals(location)) {
                homeParticipantId = participantId;
            } else if ("away".equals(location)) {
                awayParticipantId = participantId;
            }
        }

        JsonNode scoresNode = fixtureNode.get("scores");

        int homeTeamScore = 0;
        int awayTeamScore = 0;

        for (JsonNode scoreNode : scoresNode) {
            String current = scoreNode.get("description").asText();

            if (current.equals("CURRENT")) {
                int score = scoreNode.get("score").get("goals").asInt();

                if (scoreNode.get("score").get("participant").asText().equals("home")) {
                    homeTeamScore = score;
                } else
                    awayTeamScore = score;
            }
        }

        Team homeTeam = new Team();
        homeTeam.setId(homeParticipantId);

        Team awayTeam = new Team();
        awayTeam.setId(awayParticipantId);

        fixture.setHomeTeamScore(homeTeamScore);
        fixture.setAwayTeamScore(awayTeamScore);
        fixture.setGameweek(gameweek);
        fixture.setHomeTeam(homeTeam);
        fixture.setAwayTeam(awayTeam);

    return fixture;
    }
}
