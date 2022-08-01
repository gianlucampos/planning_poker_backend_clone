package com.example.planning_poker_spring.controllers;

import com.example.planning_poker_spring.models.Player;
import com.example.planning_poker_spring.services.PlayerService;
import java.util.HashSet;
import java.util.Set;
import lombok.AllArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor
public class SocketController {

    private final SimpMessagingTemplate simpMessagingTemplate;
    private final Set<Player> connectedPlayers = new HashSet<>();
    private final PlayerService service;

    //Add o jogador
    @MessageMapping("/register")
    @SendToUser("/queue/newMember")
    public Set<Player> registerUser(String name) {
        var player = new Player(name, "PP");
        connectedPlayers.add(player);
        simpMessagingTemplate.convertAndSend("/topic/newMember", player);
        return connectedPlayers;
    }

    //Liste os jogadores
    @MessageMapping("/list")
    public void listUsers() {
        if (connectedPlayers.isEmpty()) {
            connectedPlayers.addAll(service.listPlayers());
        }
        simpMessagingTemplate.convertAndSend("/topic/users", connectedPlayers);
    }

    //Remove os jogadores
    @MessageMapping("/unregister")
    public void unregisterUser(String name) {
        connectedPlayers.removeIf(player -> player.getName().equals(name));
    }

}
