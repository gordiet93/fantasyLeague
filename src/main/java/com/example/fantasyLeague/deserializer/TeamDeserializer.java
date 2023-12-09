package com.example.fantasyLeague.deserializer;

import com.example.fantasyLeague.model.Player;
import com.example.fantasyLeague.model.Team;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TeamDeserializer extends JsonDeserializer<Team> {

    @Override
    public Team deserialize (JsonParser jsonParser,
                             DeserializationContext deserializationContext) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(jsonParser);

        Team team = new Team();

        team.setId(node.get("id").asLong());
        team.setName(node.get("name").toString());

        JsonNode playersNode = node.get("players");
        List<Player> players = new ArrayList<>();
        for (JsonNode playerNode : playersNode) {

            Player player = mapper.readValue(playerNode.get("player").traverse(), Player.class);
            player.setTeam(team);
            players.add(player);
        }

        team.setPlayers(players);

        return team;
    }
}
