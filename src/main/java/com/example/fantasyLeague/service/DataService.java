package com.example.fantasyLeague.service;

import com.example.fantasyLeague.model.Team;
import com.example.fantasyLeague.repository.TeamRepository;
import com.example.fantasyLeague.utilities.Helper;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
public class DataService {

    private final TeamRepository teamRepository;

    @Autowired
    public DataService(final TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public void getAndSaveBulkData(){
        getTeamsAndPlayers();
    }

    private void getTeamsAndPlayers() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            String s = Helper.callURL("teams/season/17141?include=squad,squad.player,squad.player.position&");
            JsonNode node = mapper.readTree(s);
            List<Team> teams = Arrays.asList(mapper.readValue(node.get("data").traverse(), Team[].class));
            teamRepository.saveAll(teams);
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
