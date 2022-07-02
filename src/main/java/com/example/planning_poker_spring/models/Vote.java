package com.example.planning_poker_spring.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Vote {

    public enum VoteEnum {
        PP, P, M, G
    }

    private VoteEnum choicedVote;
    private long quantity;

}
