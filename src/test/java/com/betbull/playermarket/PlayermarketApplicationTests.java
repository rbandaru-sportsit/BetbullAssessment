package com.betbull.playermarket;

import org.junit.Rule;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.rule.OutputCapture;

import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.assertThat;


@SpringBootTest
public class PlayermarketApplicationTests {

	/*@Test
	void contextLoads() {
	}*/

	@Rule
	public OutputCapture outputCapture= new OutputCapture();

	@Test
	public void shouldStartSuccessfully() {
		// When
		PlayermarketApplication.main(new String[] {});
		String output = outputCapture.toString();

		// Then
		assertThat(output, containsString("Tomcat started on port(s):"));
	}

}
