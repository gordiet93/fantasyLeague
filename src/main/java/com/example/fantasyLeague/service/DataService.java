package com.example.fantasyLeague.service;

import com.example.fantasyLeague.model.Event;
import com.example.fantasyLeague.model.Fixture;
import com.example.fantasyLeague.model.Gameweek;
import com.example.fantasyLeague.model.Team;
import com.example.fantasyLeague.repository.FixtureRepository;
import com.example.fantasyLeague.repository.GameweekRepository;
import com.example.fantasyLeague.repository.TeamRepository;
import com.example.fantasyLeague.utilities.Helper;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
public class DataService {

    private final TeamRepository teamRepository;
    private final GameweekRepository gameweekRepository;
    private final FixtureRepository fixtureRepository;

    @Autowired
    public DataService(TeamRepository teamRepository, GameweekRepository gameweekRepository,
                       FixtureRepository fixtureRepository) {
        this.teamRepository = teamRepository;
        this.gameweekRepository = gameweekRepository;
        this.fixtureRepository = fixtureRepository;
    }

    @PostConstruct
    public void getAndSaveBulkData() {
        getTeamsAndPlayers();
        loadGameweeks();
        loadFixtures();
        loadEvents();
    }

    private void getTeamsAndPlayers() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            String s = Helper.callURL("seasons/21787?includes=teams;teams.players.player&");
            JsonNode node = mapper.readTree(s);
            List<Team> teams = Arrays.asList(mapper.readValue(node.get("data").get("teams").traverse(), Team[].class));
            teamRepository.saveAll(teams);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    private void loadGameweeks() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            String s = Helper.callURL("rounds/seasons/21787?");
            JsonNode node = mapper.readTree(s);
            List<Gameweek> gameweeks = Arrays.asList(mapper.readValue(node.get("data").traverse(), Gameweek[].class));
            gameweekRepository.saveAll(gameweeks);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    private void loadFixtures() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            List<Gameweek> gameweeks = gameweekRepository.findAll();
            for (Gameweek gameweek : gameweeks) {
                //String s = Helper.callURL("rounds/" + gameweek.getId() + "?include=fixtures.state;fixtures.participants;fixtures.scores;fixtures.events.type;fixtures.lineups.details.type&");
                String s = Helper.callURL("rounds/" + gameweek.getId() + "?include=fixtures.state;fixtures.participants;fixtures.scores&");
                JsonNode node = mapper.readTree(s);

                List<Fixture> fixtures = Arrays.asList(mapper.readValue(node.get("data").get("fixtures").traverse(), Fixture[].class));
                gameweek.setFixtures(fixtures);
            }
            gameweekRepository.saveAll(gameweeks);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    private void loadEvents() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            List<Fixture> fixtures = fixtureRepository.findAll();
            for (Fixture fixture : fixtures) {
                String s = Helper.callURL("fixtures/" + fixture.getId() + "?include=events.type&");
                JsonNode node = mapper.readTree(s);

                List<Event> events = Arrays.asList(mapper.readValue(node.get("data").get("events").traverse(), Event[].class));
                fixture.setEvents(events);
            }
            fixtureRepository.saveAll(fixtures);
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
