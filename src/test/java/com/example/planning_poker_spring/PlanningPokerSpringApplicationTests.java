package com.example.planning_poker_spring;

import com.example.planning_poker_spring.models.GameStatus;
import com.example.planning_poker_spring.models.Player;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class PlanningPokerSpringApplicationTests {

    Player player, player2, newPlayer;
    List<Player> players;

    @BeforeEach
    void setUp() {
        player = new Player();
        player.setName("Gianluca");
        player.setVote("PP");

        player2 = new Player();
        player2.setName("Ricardo");
        players = List.of(player, player2);

        newPlayer = new Player();
        newPlayer.setName("Gianluca");
    }

    @Test
    public void testContainsPlayer() {
        System.out.println(players.contains(newPlayer));
        System.out.println(player.equals(newPlayer));
    }

    @Test
    public void testChangeVote() {
        System.out.println(players);
        var playa = players.stream().filter(p -> p.getName().equals("Gianluca")).findFirst().orElseThrow();
        playa.setVote("P");
        System.out.println(players);
    }

    @Test
    public void changeStatus() {
        String status = "VOTING";
        GameStatus gameStatus = GameStatus.valueOf(status);

        assert gameStatus.equals(GameStatus.VOTING);
    }
}
