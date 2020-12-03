package com.sportsit.playermarket.player.model;

import com.sun.istack.NotNull;
import lombok.Data;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@Table(schema = "public",name = "player")
public class FootballPlayer {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "player_id_seq", sequenceName = "player_id_seq", allocationSize = 1)
    @Column(name = "id", insertable = true, updatable = false, nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "player_id_seq")
    private Long playerId;

    @NotNull
    @Column(name = "full_name", unique = true)
    private String name;

    @NotNull
    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    @Column(name = "started_playing_from")
    @NotNull
    private Date playingStartedDate;

    @Column(name = "current_team_id")
    private Long currentTeamId;

    @Column(name = "transfer_fee")
    private BigDecimal playerTransferFee;

}
