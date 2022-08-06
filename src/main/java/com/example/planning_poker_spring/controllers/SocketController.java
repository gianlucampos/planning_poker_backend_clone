package com.example.planning_poker_spring.controllers;

import com.example.planning_poker_spring.models.Player;
import com.example.planning_poker_spring.services.PlayerService;
import java.util.HashSet;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

@Controller
@Slf4j
public class SocketController {

    private final SimpMessagingTemplate simpMessagingTemplate;
    private final Set<Player> connectedPlayers = new HashSet<>();
    private final PlayerService service;

    public SocketController(SimpMessagingTemplate simpMessagingTemplate, PlayerService service) {
        this.simpMessagingTemplate = simpMessagingTemplate;
        this.service = service;
        connectedPlayers.addAll(service.listPlayers());
    }

    //Add o jogador
    @MessageMapping("/register")
    @SendToUser("/queue/newMember")
    public Set<Player> registerUser(String name) {
        var player = new Player(name, "PP");
        connectedPlayers.add(player);
        simpMessagingTemplate.convertAndSend("/topic/users", connectedPlayers);
        return connectedPlayers;
    }

    //Liste os jogadores
    @MessageMapping("/list")
    public void listUsers() {
        simpMessagingTemplate.convertAndSend("/topic/users", connectedPlayers);
    }

    //Remove os jogadores
    @MessageMapping("/unregister")
    public void unregisterUser(String name) {
        log.info("Removing player {}", name);
        connectedPlayers.removeIf(player -> player.getName().equals(name));
        simpMessagingTemplate.convertAndSend("/topic/users", connectedPlayers);
    }

    //Reseta lista de jogadores
    @MessageMapping("/resetList")
    public void reset() {
        connectedPlayers.addAll(service.listPlayers());
        simpMessagingTemplate.convertAndSend("/topic/users", connectedPlayers);
    }

}
