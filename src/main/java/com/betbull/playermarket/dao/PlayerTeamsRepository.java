package com.betbull.playermarket.dao;

import com.betbull.playermarket.dto.PlayerTeams;
import com.betbull.playermarket.dto.Teams;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlayerTeamsRepository extends JpaRepository<PlayerTeams, Long> {

    List<PlayerTeams> findByPlayerId(long playerId);

}
