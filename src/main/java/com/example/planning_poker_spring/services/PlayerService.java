package com.example.planning_poker_spring.services;

import com.example.planning_poker_spring.models.Player;
import java.util.Set;
import org.springframework.stereotype.Service;

@Service
public class PlayerService {

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
