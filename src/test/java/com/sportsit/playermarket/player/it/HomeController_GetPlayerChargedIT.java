package com.sportsit.playermarket.player.it;

import static com.jayway.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_OK;
import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sportsit.playermarket.player.AbstractRestOperationIT;
import com.sportsit.playermarket.player.dto.PlayerOutputRest;
import com.sportsit.playermarket.util.RestConstants;
import org.apache.http.HttpStatus;
import org.junit.Test;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import java.math.BigDecimal;
import java.math.RoundingMode;

@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class,
		TransactionalTestExecutionListener.class, DbUnitTestExecutionListener.class })
@DirtiesContext
public class HomeController_GetPlayerChargedIT extends AbstractRestOperationIT {

	private static final Long PLAYER_ID = 955L;
	private static final Long CURRENT_TEAM_ID = 957L;
	private static final String NAME = "Lionel Messi";
	private static final Long AGE = 25L;
	private static final Long MONTHS_OF_EXP = 9L;
	private static final BigDecimal TRANSFER_FEE = BigDecimal.valueOf(1198381).setScale(2, RoundingMode.HALF_UP);

	@Override
	protected String getRestOperationPath() {
		return RestConstants.PATH_PLAYER_CHARGED;
	}

	@Test
	public void getPlayerCharged_givenPlayerInput_shouldReturnExistingTeam() {
		// WHEN
		// @formatter:off
		PlayerOutputRest player = given()
				.contentType(RestConstants.CONTENT_TYPE_APPLICATION_JSON)
				.when()
				.get(getRestOperationPath().replace("{playerId}", "955"))
				.then()
				.statusCode(SC_OK)
				.extract()
				.as(PlayerOutputRest.class);
		// @formatter:on

		// THEN
		assertThat(player.getCurrentTeamId()).isEqualTo(CURRENT_TEAM_ID);
		assertThat(player.getPlayerId()).isEqualTo(PLAYER_ID);
		assertThat(player.getFullName()).isEqualTo(NAME);
		assertThat(player.getAge()).isEqualTo(AGE);
		assertThat(player.getMonthsOfExperience()).isEqualTo(MONTHS_OF_EXP);
		assertThat(player.getTransferFee().setScale(2, RoundingMode.HALF_UP)).isEqualTo(TRANSFER_FEE);
	}

	@Test
	public void getPlayerCharged_givenExistingPlayerId_shouldReturnCode200() {
		// WHEN
		// @formatter:off
		given()
			.contentType(RestConstants.CONTENT_TYPE_APPLICATION_JSON)
		.when()
			.get(getRestOperationPath().replace("{playerId}", "959"))
		.then()
			.statusCode(HttpStatus.SC_OK);
		// @formatter:on
	}

}
