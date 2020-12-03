package com.sportsit.playermarket.player.repository;

import com.sportsit.playermarket.player.model.FootballPlayer;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface FootballPlayerRepository extends Repository<FootballPlayer, Long>, JpaSpecificationExecutor<FootballPlayer> {

    FootballPlayer save(FootballPlayer player);
    List<FootballPlayer> findAll();
    FootballPlayer findByPlayerId(Long playerId);
    List<FootballPlayer> findByCurrentTeamId(Long currentTeamId);
    void deleteByCurrentTeamId(Long currentTeamId);
    void deleteByPlayerId(Long playerId);
}
