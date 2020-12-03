package com.sportsit.playermarket.player.model;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(schema = "public",name = "team_players")
public class TeamPlayers {
    @Id
    @SequenceGenerator(name = "team_players_id_seq", sequenceName = "team_players_id_seq", allocationSize = 1)
    @Column(name = "id", insertable = true, updatable = false, nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "team_players_id_seq")
    private Long id;

    @Column(name = "team_id")
    private Long teamId;

    @Column(name = "player_id")
    private Long playerId;

}
