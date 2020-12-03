package com.sportsit.playermarket.player.service;

import com.sportsit.playermarket.player.dto.*;
import com.sportsit.playermarket.player.exception.PlayersTeamException;
import com.sportsit.playermarket.player.model.FootballPlayer;
import com.sportsit.playermarket.player.model.PlayerPreviousTeams;
import com.sportsit.playermarket.player.model.Team;
import com.sportsit.playermarket.player.model.TeamPlayers;
import com.sportsit.playermarket.player.repository.FootballPlayerRepository;
import com.sportsit.playermarket.player.repository.PlayerPreviousTeamsRepository;
import com.sportsit.playermarket.player.repository.TeamPlayersRepository;
import com.sportsit.playermarket.player.repository.TeamRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import static org.apache.commons.lang3.RandomStringUtils.randomNumeric;

/**
 * Component providing functionality related to PlayersTeamGeneration.
 */
@Slf4j
@Service
@Transactional
public class PlayersTeamGenerationServiceImpl implements PlayersTeamGenerationService {


	@Autowired
	private FootballPlayerRepository footballPlayerRepository;

	@Autowired
	private PlayerPreviousTeamsRepository playerPreviousTeamsRepository;

	@Autowired
	private TeamRepository teamRepository;

	@Autowired
	private TeamPlayersRepository teamPlayersRepository;

	@Value("${commission.percent}")
	private BigDecimal commissionPercentage;
	

	@Transactional
	@Override
	public UserRequestOutput createTeam(CreateTeamInputRest createTeamInput) {
		try {
			if (createTeamInput != null) {
				if(createTeamInput.getCurrencyCode().length() ==3) {
					Team team = new Team();
					Team existedteam = teamRepository.findByName(createTeamInput.getName());
					if (existedteam == null) {
						team.setName(createTeamInput.getName());
						team.setCurrencyCode(createTeamInput.getCurrencyCode().toUpperCase());
						teamRepository.save(team);
						return new UserRequestOutput()
								.setRequestCreated(true)
								.setMessage("Team: " + team + " created successfully")
								.setResponseStatus("OK")
								.setResponseCode(HttpStatus.OK.value());
					} else {
						log.error("Add Team: response info: team name" + createTeamInput.getName() + " already exists.  Please try with another team name");
						throw new PlayersTeamException("Team with name: " + createTeamInput.getName() + " already exists. Please try with another team name");
					}
				}else {
					log.error("Add Team: response info: currency code: " + createTeamInput.getCurrencyCode() + " should be 3 characters.");
					throw new PlayersTeamException("Team with name: currency code :" + createTeamInput.getCurrencyCode() + " should be 3 characters.");
				}
			}else {
				log.error("Add Team: response info:  "+createTeamInput + " must not be empty");
				throw new PlayersTeamException("Team details: "+createTeamInput + " must not be empty");
			}
		} catch (PlayersTeamException ex) {
			log.error("Add Team: response info:"+ex.getTranslatedMessage());
			return new UserRequestOutput().setResponseCode(HttpStatus.BAD_REQUEST.value())
					.setRequestCreated(false)
					.setResponseErrorMessage(ex.getTranslatedMessage())
					.setResponseStatus("BAD_REQUEST");
		}
	}

	@Transactional
	@Override
	public UserRequestOutput updateTeam(UpdateTeamInputRest updateTeamInput) {
		try {
			if(updateTeamInput != null) {

				if(updateTeamInput.getCurrencyCode().length() ==3) {
					Team team = teamRepository.findByTeamId(updateTeamInput.getTeamId());
					if (team != null) {
						Team existedteam = teamRepository.findByName(updateTeamInput.getName());
						if (existedteam != null && !updateTeamInput.getTeamId().equals( existedteam.getTeamId())) {
							log.error("Update team: response info: Team name: " + updateTeamInput.getName() + " already exists. Please try with another team name");
							throw new PlayersTeamException("Team with name: " + updateTeamInput.getName() + " already exists. Please try with another team name");
						} else {
							team.setName(updateTeamInput.getName());
							team.setCurrencyCode(updateTeamInput.getCurrencyCode());
							teamRepository.save(team);
							return new UserRequestOutput()
									.setRequestCreated(true)
									.setMessage("Team details: " + team + " is Updated successfully")
									.setResponseStatus("OK")
									.setResponseCode(HttpStatus.OK.value());
						}
					} else {
						log.error("Update team: response info: Team id: " + updateTeamInput.getTeamId() + " is not found in DB,  Please try with another team id");
						throw new PlayersTeamException("Team id: " + updateTeamInput.getTeamId() + " is not found");
					}
				}else {
					log.error("Add Team: response info: currency code: " + updateTeamInput.getCurrencyCode() + " should be 3 characters.");
					throw new PlayersTeamException("Team with name: currency code :" + updateTeamInput.getCurrencyCode() + " should be 3 characters.");
				}
			}else {
				log.error("Update team: response info: "+updateTeamInput + " must not be empty");
				throw new PlayersTeamException("Team details: "+updateTeamInput + " must not be empty");
            }
		} catch (PlayersTeamException ex) {
			log.error("Update team: response info:"+ex.getTranslatedMessage());
			return new UserRequestOutput().setResponseCode(HttpStatus.BAD_REQUEST.value())
					.setRequestCreated(false)
					.setResponseErrorMessage(ex.getTranslatedMessage())
					.setResponseStatus("BAD_REQUEST");
		}
	}

	@Transactional
	@Override
	public UserRequestOutput deleteTeam(Long teamId) {
		try {
			if(teamId != null) {
				Team team = teamRepository.findByTeamId(teamId);
				if (team != null) {
					deleteParticularTeam(teamId);
					return new UserRequestOutput()
							.setRequestCreated(true)
							.setMessage(team.getName() +" Team details deleted successfully")
							.setResponseStatus("OK")
							.setResponseCode(HttpStatus.OK.value());
				}else {
					log.error("deleteTeam: response info: Team id: "+ teamId + " is not found in DB,  Please try with another team id");
					throw new PlayersTeamException("deleteTeam id:"+teamId +" is not found in DB");
				}
			}else{
				log.error("deleteTeam: response info: teamId:" + teamId + " must not be empty");
				throw new PlayersTeamException(" deleteTeam details are must not be empty ");
			}
		} catch (PlayersTeamException ex) {
			log.error("deleteTeam: response info: "+ex.getTranslatedMessage());
			return new UserRequestOutput().setResponseCode(HttpStatus.BAD_REQUEST.value())
					.setRequestCreated(false)
					.setResponseErrorMessage(ex.getTranslatedMessage())
					.setResponseStatus("BAD_REQUEST");
		}
	}

	@Transactional
	@Override
	public UserRequestOutput addPlayer(CreatePlayerInputRest createPlayerInput){
		try {
			if(createPlayerInput != null) {
				if(createPlayerInput.getCurrentTeamId() != null){
					FootballPlayer player = createPlayer(createPlayerInput);
					return new UserRequestOutput()
							.setRequestCreated(true)
							.setMessage("Player details: " + player + " created successfully")
							.setResponseStatus("OK")
							.setResponseCode(HttpStatus.OK.value());

				} else {
					log.error("Add player: response info: current team  id " + createPlayerInput.getCurrentTeamId() + " not found in DB");
					throw new PlayersTeamException("Add player: response info: current team  id " + createPlayerInput.getCurrentTeamId() + " not found in DB");
				}

			} else {
				log.error("Add player: response info:" + createPlayerInput + " must not be empty");
				throw new PlayersTeamException("Player details: "+ createPlayerInput + " must not be empty");
			}
		} catch (PlayersTeamException ex) {
			log.error("Add player: response info::"+ex.getTranslatedMessage());
			return new UserRequestOutput().setResponseCode(HttpStatus.BAD_REQUEST.value())
				.setRequestCreated(false)
				.setResponseErrorMessage(ex.getTranslatedMessage())
				.setResponseStatus("BAD_REQUEST");
		}
	}

	@Transactional
	@Override
	public UserRequestOutput updatePlayer(UpdatePlayerInputRest updatePlayerInput) {
		try {
			if (updatePlayerInput != null) {
				FootballPlayer player = footballPlayerRepository.findByPlayerId(updatePlayerInput.getPlayerId());
				if (player != null) {
					if(updatePlayerInput.getCurrentTeamId()!=null){
						updatePlayerDetails(player, updatePlayerInput);
						return new UserRequestOutput()
							.setRequestCreated(true)
							.setMessage("Player id: " + updatePlayerInput + " updated successfully")
							.setResponseStatus("OK")
							.setResponseCode(HttpStatus.OK.value());
					} else {
						log.error("Update player: response info: current team  id " + updatePlayerInput.getCurrentTeamId() + " not found in DB");
						throw new PlayersTeamException("Update player: response info: current team  id " + updatePlayerInput.getCurrentTeamId() + " not found in DB");
					}
				} else {
					log.error("Update player: response info: playerId:" + updatePlayerInput.getPlayerId() + " is not found in DB");
					throw new PlayersTeamException("Player: " + updatePlayerInput.getPlayerId() + " is not found in DB");
				}
			} else {
				log.error("Update player: response info:" + updatePlayerInput + " must not be empty");
				throw new PlayersTeamException("Player details: " + updatePlayerInput + " must not be empty");
			}
		} catch (PlayersTeamException ex) {
			log.error("Update player: response info:"+ex.getTranslatedMessage());
			return new UserRequestOutput().setResponseCode(HttpStatus.BAD_REQUEST.value())
					.setRequestCreated(false)
					.setResponseErrorMessage(ex.getTranslatedMessage())
					.setResponseStatus("BAD_REQUEST");
		}
	}

	@Transactional
	@Override
	public  UserRequestOutput deletePlayer(Long playerId) {
		try {
			if(playerId != null) {
				FootballPlayer footballPlayer = footballPlayerRepository.findByPlayerId(playerId);
				if (footballPlayer != null) {
					playerPreviousTeamsRepository.deleteByPlayerId(playerId);
					teamPlayersRepository.deleteByPlayerId(playerId);
					footballPlayerRepository.deleteByPlayerId(playerId);

					BigDecimal teamTransferFee = BigDecimal.ZERO;
					List<FootballPlayer> footballPlayerList = footballPlayerRepository.findByCurrentTeamId(footballPlayer.getCurrentTeamId());
					for(FootballPlayer player: footballPlayerList){
						teamTransferFee = teamTransferFee.add(player.getPlayerTransferFee());
					}
					BigDecimal commission = teamTransferFee.multiply(commissionPercentage);
					Team currentTeam = teamRepository.findByTeamId(footballPlayer.getCurrentTeamId());
					currentTeam.setTeamTransferFee(teamTransferFee);
					currentTeam.setTeamCommission(commission);
					currentTeam.setContractFee(teamTransferFee.add(commission));
					teamRepository.save(currentTeam);

					return new UserRequestOutput()
							.setRequestCreated(true)
							.setMessage("Player id: " + playerId + " deleted successfully")
							.setResponseStatus("OK")
							.setResponseCode(HttpStatus.OK.value());
				} else {
					log.error("deletePlayer: response info: playerId:" + playerId + "not found in DB");
					throw new PlayersTeamException(" deletePlayer: response info: playerId:" + playerId + "not found in DB");
				}
			}else {
				log.error("deletePlayer: response info: playerId:" + playerId + "must not be empty");
				throw new PlayersTeamException(" deletePlayer: response info: playerId:" + playerId + "must not be empty");
			}
		} catch (PlayersTeamException ex) {
			log.error("deletePlayer: response info: " + ex.getTranslatedMessage());
			return new UserRequestOutput().setResponseCode(HttpStatus.BAD_REQUEST.value())
					.setRequestCreated(false)
					.setResponseErrorMessage(ex.getTranslatedMessage())
					.setResponseStatus("BAD_REQUEST");
		}
	}

	@Transactional
	@Override
	public List<PlayerListOutputRest> playersList(){
		List<FootballPlayer> playersList = footballPlayerRepository.findAll();
		List<PlayerListOutputRest>  playerListOutput = new ArrayList<>();
		for (FootballPlayer player : playersList) {
			PlayerListOutputRest  playerDetails = new PlayerListOutputRest();
			Team team = teamRepository.findByTeamId(player.getCurrentTeamId());
			playerDetails.setPlayerId(player.getPlayerId());
			playerDetails.setFullName(player.getName());
			playerDetails.setCurrentTeamId(player.getCurrentTeamId());
			playerDetails.setDateOfBirth(player.getDateOfBirth());
			playerDetails.setPlayingStartedDate(player.getPlayingStartedDate());
			playerDetails.setCurrencyCode(team.getCurrencyCode());
			playerListOutput.add(playerDetails);
		}
		List<PlayerListOutputRest> playerList = playerListOutput.stream()
				.sorted(Comparator.comparing(PlayerListOutputRest::getPlayerId).reversed())
				.collect(Collectors.toList());
		log.info("Get player List: response info: " + playerListOutput);
		return playerListOutput;
	}

	@Transactional
	@Override
	public TeamOutputRest getPlayerTeamDetails(Long playerId) {
		Team team = findPlayerTeam(playerId);
		if (team == null) {
			log.error("Get player team details: response info: Player id:" + playerId + " is not found in DB");
			throw new PlayersTeamException("Player id: " + playerId + " is not found in DB");
		}
		TeamOutputRest teamOutputRest = new TeamOutputRest().setTeamId(team.getTeamId())
				.setName(team.getName())
				.setTeamCommission(team.getTeamCommission())
				.setTeamTransferFee(team.getTeamTransferFee())
				.setContractFee(team.getContractFee())
				.setCurrencyCode(team.getCurrencyCode());

		log.info("Get player team details: response info: " + teamOutputRest);
		return teamOutputRest;
	}

	@Transactional
	@Override
	public PlayerOutputRest getPlayerChargedDetails(Long playerId){
		FootballPlayer player = footballPlayerRepository.findByPlayerId(playerId);
		if (player == null) {
			log.error("Get player charged details: response info: Player id:" + playerId + " is not found in DB");
			throw new PlayersTeamException("Player id:" + playerId + " is not found in DB");
		}
		List<PlayerPreviousTeams> playerPreviousTeams = playerPreviousTeamsRepository.findByPlayerId(playerId);
		List<PlayerInputRest> previousTeams = new ArrayList<>();
		for (PlayerPreviousTeams playerPreviousTeam : playerPreviousTeams){
			PlayerInputRest previousTeam = new PlayerInputRest();
			previousTeam.setId(playerPreviousTeam.getPreviousTeamId());
		}
		CalculateDateDto calculateDateDto = calculateAge(player.getDateOfBirth(),player.getPlayingStartedDate());
		PlayerOutputRest playerOutputRest = new PlayerOutputRest().setPlayerId(player.getPlayerId())
				.setFullName(player.getName())
				.setCurrentTeamId(player.getCurrentTeamId())
				.setAge(calculateDateDto.getAge())
				.setMonthsOfExperience(calculateDateDto.getMonthsOfExperience())
				.setTransferFee(player.getPlayerTransferFee())
				.setPreviousTeams(previousTeams);
		log.info("Get player charged details: response info: " + playerOutputRest);
		return playerOutputRest;
	}

	private Team findPlayerTeam(Long playerId) {
		TeamPlayers teamPlayers = null;
		Team team = null;
		if (playerId != null) {
			teamPlayers = teamPlayersRepository.findByPlayerId(playerId);
			if(teamPlayers !=null) {
				team = teamRepository.findByTeamId(teamPlayers.getTeamId());
				if (team == null) {
					log.error("Get player team details: Player id:" + playerId + " is not found in DB");
					throw new PlayersTeamException("Player id:" + playerId + " is not found in DB");
				}
			}
		}
		return team;
	}

	private void deleteParticularTeam(Long teamId) {
		List<FootballPlayer> playerList = footballPlayerRepository.findByCurrentTeamId(teamId);
		for (FootballPlayer player : playerList) {
			playerPreviousTeamsRepository.deleteByPlayerId(player.getPlayerId());
		}
		playerPreviousTeamsRepository.deleteByPreviousTeamId(teamId);
		teamPlayersRepository.deleteByTeamId(teamId);
		teamRepository.deleteByTeamId(teamId);
		footballPlayerRepository.deleteByCurrentTeamId(teamId);
	}
	private CalculateDateDto calculateAge(Date playerDateOfBirth, Date startedPlayingDate) {
		Calendar calendar = Calendar.getInstance();
		Calendar calendarnow = Calendar.getInstance();
		calendarnow.getTimeZone();
		calendar.setTime(playerDateOfBirth);
		int getDobMonth = calendar.get(calendar.MONTH);
		int getDobYear = calendar.get(calendar.YEAR);
		int currentmonth = calendarnow.get(calendarnow.MONTH);
		int currentyear = calendarnow.get(calendarnow.YEAR);

		calendar.setTime(startedPlayingDate);
		int getMonth = calendar.get(calendar.MONTH);
		int getYear = calendar.get(calendar.YEAR);

		Long age = Long.valueOf(((currentyear * 12 + currentmonth) - (getDobYear * 12 + getDobMonth))) / 12;
		Long monthsOfExp = Long.valueOf(((currentyear * 12 + currentmonth) - (getYear * 12 + getMonth)));

		return new CalculateDateDto().setAge(age).setMonthsOfExperience(monthsOfExp);
	}

	private  FootballPlayer  createPlayer(CreatePlayerInputRest createPlayerInput){
		Boolean isUpdated = false;
		FootballPlayer player = new FootballPlayer();
		player.setName(createPlayerInput.getFullName());
		player.setDateOfBirth(createPlayerInput.getDateOfBirth());
		player.setPlayingStartedDate(createPlayerInput.getPlayingStartedDate());
		player.setCurrentTeamId(createPlayerInput.getCurrentTeamId());

		Team  previousTeam= new Team();
		Team currentTeam = teamRepository.findByTeamId(createPlayerInput.getCurrentTeamId());
		BigDecimal playerChargedAmount  = updateCharges(player, currentTeam, previousTeam, isUpdated);
		player.setPlayerTransferFee(playerChargedAmount);
		player = footballPlayerRepository.save(player);

		TeamPlayers teamPlayers = new TeamPlayers();
		teamPlayers.setPlayerId(player.getPlayerId());
		teamPlayers.setTeamId(createPlayerInput.getCurrentTeamId());
		teamPlayersRepository.save(teamPlayers);

		if (createPlayerInput.getPreviousTeams() != null && !createPlayerInput.getPreviousTeams().isEmpty()) {
			for (PlayerInputRest previousTeamInput : createPlayerInput.getPreviousTeams()) {
				if( teamRepository.findByTeamId(previousTeamInput.getId())!= null) {
					PlayerPreviousTeams playerPreviousTeams = new PlayerPreviousTeams();
					playerPreviousTeams.setPlayerId(player.getPlayerId());
					playerPreviousTeams.setPreviousTeamId(previousTeamInput.getId());
					playerPreviousTeamsRepository.save(playerPreviousTeams);
				}else{
					log.error("Add player team details: Previous team id:" + previousTeamInput.getId() + " is not found in DB");
					throw new PlayersTeamException("Previous team id :" + previousTeamInput.getId() + " is not found in DB");
				}

			}
		}
		return player;
	}

	private void updatePlayerDetails(FootballPlayer player, UpdatePlayerInputRest updatePlayerInput){
		Boolean isUpdated = true;
		TeamPlayers teamPlayers = teamPlayersRepository.findByPlayerId(player.getPlayerId());
		teamPlayersRepository.deleteByPlayerId(player.getPlayerId());
		if(updatePlayerInput.getCurrentTeamId() !=null) {
			teamPlayersRepository.deleteByPlayerId(player.getPlayerId());
			TeamPlayers newTeamPlayer = new TeamPlayers();
			newTeamPlayer.setPlayerId(player.getPlayerId());
			newTeamPlayer.setTeamId(updatePlayerInput.getCurrentTeamId());
			teamPlayersRepository.save(newTeamPlayer);
		}

		Team previousTeam = teamRepository.findByTeamId(teamPlayers.getTeamId());
		player.setName(updatePlayerInput.getFullName());
		player.setPlayingStartedDate(updatePlayerInput.getPlayingStartedDate() != null ? updatePlayerInput.getPlayingStartedDate() : player.getPlayingStartedDate());
		player.setDateOfBirth(updatePlayerInput.getDateOfBirth() != null ? updatePlayerInput.getDateOfBirth() : player.getDateOfBirth());
		player.setCurrentTeamId(updatePlayerInput.getCurrentTeamId());
		if(updatePlayerInput.getCurrentTeamId() != null) {
			Team currentTeam = teamRepository.findByTeamId(updatePlayerInput.getCurrentTeamId());
			BigDecimal playerChargedAmount=updateCharges(player, currentTeam, previousTeam, isUpdated);
			player.setPlayerTransferFee(playerChargedAmount);
		}
		footballPlayerRepository.save(player);


		if (updatePlayerInput.getPreviousTeams() != null && !updatePlayerInput.getPreviousTeams().isEmpty()) {
			for (PlayerInputRest previousTeamInput : updatePlayerInput.getPreviousTeams()) {
				if( teamRepository.findByTeamId(previousTeamInput.getId())!= null) {
					playerPreviousTeamsRepository.deleteByPlayerId(player.getPlayerId());
					PlayerPreviousTeams playerPreviousTeams = new PlayerPreviousTeams();
					playerPreviousTeams.setPlayerId(player.getPlayerId());
					playerPreviousTeams.setPreviousTeamId(previousTeamInput.getId());
					playerPreviousTeamsRepository.save(playerPreviousTeams);
				}else{
					log.error("Add player team details: Previous team id:" + previousTeamInput.getId() + " is not found in DB");
					throw new PlayersTeamException("Previous team id :" + previousTeamInput.getId() + " is not found in DB");
				}
			}
		} else {
			playerPreviousTeamsRepository.deleteByPlayerId(player.getPlayerId());
		}
	}

	private BigDecimal updateCharges(FootballPlayer player, Team currentTeam, Team previousTeam, Boolean isUpdated)  {
		CalculateDateDto calculateDateDto = calculateAge(player.getDateOfBirth(), player.getPlayingStartedDate());
		if(calculateDateDto.getAge()<16) {
			calculateDateDto.getAge();
			throw new PlayersTeamException("player age must be 16 years old.");
		}
		BigDecimal playerTransferFee = BigDecimal.valueOf(calculateDateDto.getMonthsOfExperience() * 100000 / calculateDateDto.getAge());
		BigDecimal teamTransferFee = BigDecimal.ZERO;
		List<FootballPlayer> footballPlayerList = footballPlayerRepository.findByCurrentTeamId(currentTeam.getTeamId());
		for(FootballPlayer footballPlayer: footballPlayerList){
			if(!footballPlayer.getPlayerId().equals(player.getPlayerId())) {
				teamTransferFee = teamTransferFee.add(footballPlayer.getPlayerTransferFee());
			}
		}
		teamTransferFee = teamTransferFee.add(playerTransferFee);
		BigDecimal commission = teamTransferFee.multiply(commissionPercentage);
		currentTeam.setTeamTransferFee(teamTransferFee);
		currentTeam.setTeamCommission(commission);
		currentTeam.setContractFee(teamTransferFee.add(commission));
		teamRepository.save(currentTeam);
		return playerTransferFee;
	}

}
