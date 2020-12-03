package com.betbull.playermarket.dto;

import javax.persistence.*;

@Entity
@Table(name = "teams")
@SequenceGenerator(name = "team_id_seq", schema = "public", sequenceName = "team_seq_id", allocationSize = 1)
public class Teams {

    @Id

    @Column(name="team_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "team_id_seq")
    private long teamId;

    public long getTeamId() {
        return teamId;
    }


    public void setTeamId(long teamId) {
        this.teamId = teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getTeamColour() {
        return teamColour;
    }

    public void setTeamColour(String teamColour) {
        this.teamColour = teamColour;
    }

    @Column(name="team_name")
    private String teamName;

    @Column(name="team_colour")
    private String teamColour;

}
