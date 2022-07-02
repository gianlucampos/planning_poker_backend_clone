package com.example.planning_poker_spring.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = {"name"})
@AllArgsConstructor
public class Player {

    private String name;
    private String vote;

}
