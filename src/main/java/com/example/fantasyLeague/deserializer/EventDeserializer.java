package com.example.fantasyLeague.deserializer;

import com.example.fantasyLeague.model.Event;
import com.example.fantasyLeague.model.Fixture;
import com.example.fantasyLeague.model.Player;
import com.example.fantasyLeague.model.Team;
import com.example.fantasyLeague.model.enums.EventType;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class EventDeserializer extends JsonDeserializer<Event> {

    @Override
    public Event deserialize(JsonParser jsonParser,
                                   DeserializationContext deserializationContext) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(jsonParser);

        Event event = new Event();
        event.setId(node.get("id").asLong());
        event.setMinute(node.get("minute").asInt());

        Team team = new Team(node.get("participant_id").asLong());
        event.setTeam(team);

        Player player = new Player(node.get("player_id").asLong());
        event.setPlayer(player);

        Player relatedPlayer = new Player(node.get("related_player_id").asLong());
        event.setRelatedPlayer(relatedPlayer);

        Fixture fixture = new Fixture(node.get("fixture_id").asLong());
        event.setFixture(fixture);

        EventType eventType = EventType.valueOf(node.get("type").get("developer_name").asText());
        event.setType(eventType);

        return event;
    }
}
