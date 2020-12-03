package com.betbull.playermarket.dto;

import javax.persistence.*;

@Entity
@Table(name = "player_teams")
public class PlayerTeams {

    @Id
    public long getPlayerId() {
        return playerId;
    }

    @Override
    public String toString() {
        return "PlayerTeams{" +
                "playerId=" + playerId +
                ", teamId='" + teamId + '\'' +
                ", teamName='" + teamName + '\'' +
                '}';
    }

    public void setPlayerId(long playerId) {
        this.playerId = playerId;
    }

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    @Column(name="player_id")
    private long playerId;

    @Column(name="team_id")
    private String teamId;

    @Column(name="team_name")
    private String teamName;
}
