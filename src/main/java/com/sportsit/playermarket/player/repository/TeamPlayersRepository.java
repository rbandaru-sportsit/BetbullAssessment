package com.sportsit.playermarket.player.repository;

import com.sportsit.playermarket.player.model.TeamPlayers;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface TeamPlayersRepository extends Repository<TeamPlayers, Long>, JpaSpecificationExecutor<TeamPlayers> {

    void save(TeamPlayers teamPlayers);

    List<TeamPlayers> findAll();

    void deleteByTeamId(Long teamId);

    TeamPlayers findByPlayerId(Long playerId);

    void deleteByPlayerId(Long playerId);
}
