package com.sportsit.betbulldemo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Team {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer teamId;
    private String teamName;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer currencyId;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String currencyName;

    public String getCurrencyName() {
        return currencyName;
    }

    public Team() {
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public Integer getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(Integer currencyId) {
        this.currencyId = currencyId;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<Player> playersList;

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public List<Player> getPlayersList() {
        return playersList;
    }

    public void setPlayersList(List<Player> playersList) {
        this.playersList = playersList;
    }
}
