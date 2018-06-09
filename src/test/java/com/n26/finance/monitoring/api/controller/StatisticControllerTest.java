package com.n26.finance.monitoring.api.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

/**
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class StatisticControllerTest extends AbstractControllerTest {

	/**
	 *
	 */
	@Autowired
	private MockMvc mockMvc;

	/**
	 *
	 */
	private String endpointPrefix = "/statistics";

	/**
	 *
	 */
	@Before
	public void setup() {
	}

	/**
	 *
	 */
	@Test
	public void getStatistics() throws Exception {
		Integer status = mockMvc
			.perform(
				post(endpointPrefix)
					.accept(MediaType.APPLICATION_JSON)
			)
			.andReturn()
			.getResponse()
			.getStatus();

		assert status.equals(HttpStatus.OK.value());
	}
}
