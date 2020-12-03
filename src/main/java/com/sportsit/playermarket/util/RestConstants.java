package com.sportsit.playermarket.util;

public interface RestConstants {

    String CONTENT_TYPE_APPLICATION_JSON = "application/json";

    String BASE_URL="/football";

    String PATH_ADD_PLAYER = BASE_URL + "/addPlayer";
    String PATH_UPDATE_PLAYER = BASE_URL + "/updatePlayer";
    String PATH_DELETE_PLAYER = BASE_URL + "/deletePlayer";

    String PATH_ADD_TEAM = BASE_URL + "/addTeam";
    String PATH_UPDATE_TEAM = BASE_URL + "/updateTeam";
    String PATH_DELETE_TEAM = BASE_URL + "/deleteTeam";


    String PATH_PLAYER_LIST = BASE_URL + "/getPlayersList";
    String PATH_PLAYER_TEAMS_DETAILS = BASE_URL + "/getPlayerTeamsDetails/{playerId}";
    String PATH_PLAYER_CHARGED =BASE_URL + "/getPlayerCharged/{playerId}";

}