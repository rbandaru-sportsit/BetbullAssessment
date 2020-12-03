package com.sportsit.betbulldemo.dto;

import org.springframework.stereotype.Component;

@Component
public class RequestTO {

    private String playerName;
    private String teamName;

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }
}
