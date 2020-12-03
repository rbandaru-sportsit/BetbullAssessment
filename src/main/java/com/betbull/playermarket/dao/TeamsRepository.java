package com.betbull.playermarket.dao;

import com.betbull.playermarket.dto.Teams;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeamsRepository extends JpaRepository<Teams,Long> {

    List<Teams> findByTeamName(String teamName);


}
