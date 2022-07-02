package com.example.planning_poker_spring.controllers;

import com.example.planning_poker_spring.models.GameResult;
import com.example.planning_poker_spring.models.Player;
import com.example.planning_poker_spring.models.Vote;
import com.example.planning_poker_spring.models.Vote.VoteEnum;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api/players")
@CrossOrigin(origins = "*")
public class PlayerController {

    private final List<Player> players = new ArrayList<>();

    @GetMapping
    public ResponseEntity<List<Player>> listPlayers() {
        return ResponseEntity.ok(players);
    }

    @PostMapping
    public ResponseEntity<?> addPlayer(@RequestBody Player player) {
        player.setName(player.getName().trim());
        if (players.contains(player)) {
            return ResponseEntity.badRequest().body("Nome já inserido, por favor informe outro!");
        }
        players.add(player);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/vote")
    public ResponseEntity<?> vote(@RequestBody Player player) {
        players.stream()
            .filter(p -> p.getName().equalsIgnoreCase(player.getName()))
            .findAny()
            .orElseThrow()
            .setVote(player.getVote());
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @GetMapping("/result")
    public ResponseEntity<GameResult> result() {
        long pp = players.stream().filter(player -> player.getVote().equals("PP")).count();
        long p = players.stream().filter(player -> player.getVote().equals("P")).count();
        long m = players.stream().filter(player -> player.getVote().equals("M")).count();
        long g = players.stream().filter(player -> player.getVote().equals("G")).count();

        Vote votePP = new Vote(VoteEnum.PP, pp);
        Vote voteP = new Vote(VoteEnum.P, p);
        Vote voteM = new Vote(VoteEnum.M, m);
        Vote voteG = new Vote(VoteEnum.G, g);

        return ResponseEntity.ok(new GameResult(List.of(votePP, voteP, voteM, voteG)));

    }


}
