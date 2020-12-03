package com.sportsit.playermarket.player.repository;

import com.sportsit.playermarket.player.model.Team;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.Repository;

import javax.transaction.Transactional;

@Transactional
public interface TeamRepository extends Repository<Team, Long>, JpaSpecificationExecutor<Team> {

    Team save(Team team);

    Team findByTeamId(Long teamId);

    void deleteByTeamId(Long teamId);

    Team findByName(String name);
}
