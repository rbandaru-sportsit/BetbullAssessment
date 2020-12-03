package com.sportsit.betbulldemo;

import com.sportsit.betbulldemo.dao.HomePageRepository;
import com.sportsit.betbulldemo.dto.Commission;
import com.sportsit.betbulldemo.dto.Player;
import com.sportsit.betbulldemo.dto.ResponseTO;
import com.sportsit.betbulldemo.dto.Team;
import com.sportsit.betbulldemo.service.HomePageService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BetbulldemoApplicationTests {

	@Autowired
	private HomePageService homePageService;

	@MockBean
	private HomePageRepository homePageRepository;

	@Test
	public void allPlayers(){
		when(homePageRepository.getAllPlayers()).thenReturn(Stream.of(new Player(2,"christiano rolando",
						35,70,"juventus"),
				new Player(2,"christiano rolando",35,70,"juventus")).collect(Collectors.toList()));
		//When(homePageRepository.getAllPlayers()).
		Assert.assertEquals(2, homePageService.allPlayers().size());
	}


	@Test
	public void getTeamName(){

		int teamName = 354;

		when(homePageRepository.getTeamName(teamName)).thenReturn("juventus");
		String s = homePageRepository.getTeamName(teamName);
		Assert.assertEquals("juventus", s);
	}


	@Test
	public void insertPlayer(){
		Player player = new Player(54,"christiano",33,60,"juventus");
		when(homePageRepository.insertPlayer(player)).thenReturn(1);
		Assert.assertEquals(1, homePageRepository.insertPlayer(player));
	}

	@Test
	public void deletePlayer(){
		Player player = new Player(2,"christiano",33,60,"juventus");
		when(homePageRepository.deletePlayer(51)).thenReturn("success");
		Assert.assertEquals("success", homePageRepository.deletePlayer(51));
	}


	@Test
	public void updatePlayer(){
		Player player = new Player(2,"christiano",33,60,"juventus");
		//when(homePageRepository.updatePlayerByName(player)).thenReturn();
		ResponseTO responseTO = new ResponseTO();
		responseTO.setDescription(HttpStatus.OK.getReasonPhrase());
		responseTO.setData("updated");
		responseTO.setStatus(true);
		responseTO.getStatusCode(HttpStatus.OK.value());
		Assert.assertEquals(responseTO, homePageService.updatePlayerByName(player));
	}

	@Test
	public void deleteTeamPlayer(){
		when(homePageRepository.deleteTeamPlayer("christiano rolando","juventus")).thenReturn("success");
		Assert.assertEquals("success", homePageRepository.deleteTeamPlayer("christiano rolando","juventus"));
	}


	@Test
	public void commissionList(){
		when(homePageRepository.getPlayerFee()).thenReturn((List<Commission>) Stream.of(new Commission("lionel messi",181818,183636.18,1818.18),
				new Commission("griezmann", 206896, 208964.96,2068.96)).collect(Collectors.toList()));
		//When(homePageRepository.getAllPlayers()).
		Assert.assertEquals(2, homePageRepository.getPlayerFee().size());
	}


}
