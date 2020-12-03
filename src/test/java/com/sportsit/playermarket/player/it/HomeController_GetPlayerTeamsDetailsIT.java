package com.sportsit.playermarket.player.it;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.sportsit.playermarket.player.AbstractRestOperationIT;
import com.sportsit.playermarket.player.dto.TeamOutputRest;
import com.sportsit.playermarket.util.RestConstants;
import org.apache.http.HttpStatus;
import org.junit.Test;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static com.jayway.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_OK;
import static org.assertj.core.api.Assertions.assertThat;

@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class,
		TransactionalTestExecutionListener.class, DbUnitTestExecutionListener.class })
@DirtiesContext
public class HomeController_GetPlayerTeamsDetailsIT extends AbstractRestOperationIT {

	private static final Long TEAM_ID = 957L;
	private static final String NAME = "Germeny";
	private static final String CURRENCY_CODE = "GBP";
	private static final BigDecimal TEAM_TRANSFER_FEE = BigDecimal.valueOf(6223082).setScale(2, RoundingMode.HALF_UP);
	private static final BigDecimal TEAM_COMMISSION = BigDecimal.valueOf(622308.20).setScale(2, RoundingMode.HALF_UP);
	private static final BigDecimal CONTRACT_FEE = BigDecimal.valueOf(6845390.20).setScale(2, RoundingMode.HALF_UP);

	@Override
	protected String getRestOperationPath() {
		return RestConstants.PATH_PLAYER_TEAMS_DETAILS;
	}

	@Test
	public void getPlayerTeamsDetails_givenCorrectInput_shouldReturnExistingTeam() {
		// WHEN
		// @formatter:off
		TeamOutputRest result = given()
			.contentType(RestConstants.CONTENT_TYPE_APPLICATION_JSON)
		.when()
			.get(getRestOperationPath().replace("{playerId}", "955"))
		.then()
			.statusCode(SC_OK)
			.extract()
			.as(TeamOutputRest.class);
		// @formatter:on

		// THEN
		assertThat(result.getTeamId()).isEqualTo(TEAM_ID);
		assertThat(result.getCurrencyCode()).isEqualTo(CURRENCY_CODE);
		assertThat(result.getName()).isEqualTo(NAME);
		assertThat(result.getTeamTransferFee().setScale(2, RoundingMode.HALF_UP)).isEqualTo(TEAM_TRANSFER_FEE);
		assertThat(result.getTeamCommission().setScale(2, RoundingMode.HALF_UP)).isEqualTo(TEAM_COMMISSION);
		assertThat(result.getContractFee().setScale(2, RoundingMode.HALF_UP)).isEqualTo(CONTRACT_FEE);
	}

	@Test
	public void getPlayerTeamsDetails_givenExistingPlayerIdInput_shouldReturnCode200() {
		// WHEN
		// @formatter:off
		given()
			.contentType(RestConstants.CONTENT_TYPE_APPLICATION_JSON)
		.when()
			.get(getRestOperationPath().replace("{playerId}", "955"))
		.then()
			.statusCode(HttpStatus.SC_OK);
		// @formatter:on
	}

	@Test
	public void getPlayerTeamsDetails_givenNotExistingPlayerId_shouldReturnCode404() {
		// WHEN
		// @formatter:off
		given()
				.contentType(RestConstants.CONTENT_TYPE_APPLICATION_JSON)
				.when()
				.get(getRestOperationPath().replace("{playerId}", "1"))
				.then()
				.statusCode(HttpStatus.SC_NOT_FOUND);
		// @formatter:on
	}
}
