package com.betbull.playermarket.dao;

import com.betbull.playermarket.dto.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlayerRepository extends JpaRepository<Player, Long> {
    List<Player> findByPlayerName(String playerName);

}
