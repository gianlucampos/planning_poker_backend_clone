package com.example.planning_poker_spring;

import com.example.planning_poker_spring.services.PlayerService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PlanningPokerSpringApplication implements CommandLineRunner {

    private final PlayerService playerService;

    public PlanningPokerSpringApplication(PlayerService playerService) {
        this.playerService = playerService;
    }

    public static void main(String[] args) {
        SpringApplication.run(PlanningPokerSpringApplication.class, args);
    }

    @Override
    public void run(String... args) {
        playerService.initDemo();
    }
}
