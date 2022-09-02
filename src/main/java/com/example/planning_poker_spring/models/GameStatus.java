package com.example.planning_poker_spring.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum GameStatus {

    VOTING("Pick your cards"),
    REVEAL_CARDS("Reveal cards"),
    NEW_GAME("Start new voting");

    private final String value;

}