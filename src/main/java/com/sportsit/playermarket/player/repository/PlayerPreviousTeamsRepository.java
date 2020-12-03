package com.sportsit.playermarket.player.repository;

import com.sportsit.playermarket.player.model.PlayerPreviousTeams;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface PlayerPreviousTeamsRepository extends Repository<PlayerPreviousTeams, Long>, JpaSpecificationExecutor<PlayerPreviousTeams> {

    void save(PlayerPreviousTeams playerPreviousTeams);
    void deleteByPlayerId(Long playerId);
    void deleteByPreviousTeamId(Long previousTeamId);
    List<PlayerPreviousTeams> findByPlayerId(Long playerId);
}
