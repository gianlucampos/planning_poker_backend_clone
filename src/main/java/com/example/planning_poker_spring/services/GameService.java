package com.example.planning_poker_spring.services;

import com.example.planning_poker_spring.models.GameStatus;
import org.springframework.stereotype.Service;

@Service
public class GameService {

    private GameStatus gameStatus = GameStatus.VOTING;

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public void changeGameStatus(GameStatus newGameStatus) {
        gameStatus = newGameStatus;
    }

}
