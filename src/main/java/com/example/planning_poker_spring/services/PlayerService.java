package com.example.planning_poker_spring.services;

import com.example.planning_poker_spring.models.Player;
import java.util.Set;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PlayerService {

    private final String BASE_URL = "http://localhost:8080/v1/api/players/";
    private final String VOTE = "http://localhost:8080/v1/api/players/vote";
    private final String RESULT = "http://localhost:8080/v1/api/players/result";

    public void initDemo() {
        RestTemplate restTemplate = new RestTemplate();
        listPlayers().forEach(player -> restTemplate.postForEntity(BASE_URL, player, Void.class));
    }

    public Set<Player> listPlayers() {
        return Set.of(
            new Player("Gianluca", "PP"),
            new Player("Victor", "PP"),
            new Player("Lucas", "PP"),
            new Player("Ricardo", "P"),
            new Player("Bruno", "P"),
            new Player("Bruna", "P"),
            new Player("Renata", "M"),
            new Player("Geovanni", "M")
        );
    }


}
