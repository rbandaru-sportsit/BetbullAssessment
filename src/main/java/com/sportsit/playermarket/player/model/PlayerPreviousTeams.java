package com.sportsit.playermarket.player.model;

import com.sun.istack.NotNull;
import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(schema = "public",name = "player_previous_teams")
public class PlayerPreviousTeams {

    @Id
    @SequenceGenerator(name = "player_previous_teams_id_seq", sequenceName = "player_previous_teams_id_seq", allocationSize = 1)
    @Column(name = "id", insertable = true, updatable = false, nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "player_previous_teams_id_seq")
    private Long id;

    @Column(name = "player_id")
    private Long playerId;

    @Column(name = "previous_team_id")
    private Long previousTeamId;

}
