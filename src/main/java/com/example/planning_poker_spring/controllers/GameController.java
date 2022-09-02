package com.example.planning_poker_spring.controllers;

import com.example.planning_poker_spring.models.GameStatus;
import com.example.planning_poker_spring.services.GameService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api/game")
@CrossOrigin(origins = "*")
public class GameController {

    private final GameService gameService;
    private final SimpMessagingTemplate simpMessagingTemplate;

    public GameController(GameService gameService, SimpMessagingTemplate simpMessagingTemplate) {
        this.gameService = gameService;
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @GetMapping("/status")
    public ResponseEntity<GameStatus> getGameStatus() {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(gameService.getGameStatus());
    }

    @PutMapping("/status")
    public ResponseEntity<?> changeGameStatus(@RequestBody GameStatus newGameStatus) {
        gameService.changeGameStatus(newGameStatus);
        simpMessagingTemplate.convertAndSend("/topic/status", gameService.getGameStatus());
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }


}
