package com.example.planning_poker_spring.models;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GameResult {

    private List<Vote> votes;

}
