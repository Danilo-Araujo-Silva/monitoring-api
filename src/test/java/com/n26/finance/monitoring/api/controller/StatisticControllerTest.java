package com.n26.finance.monitoring.api.controller;

import com.n26.finance.monitoring.api.model.configuration.Properties;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

/**
 * The statistics endpoint tests.
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
	@Autowired
	public Properties properties;

	/**
	 *
	 */
	private String endpointPrefix;

	/**
	 *
	 */
	@Before
	public void setup() {
		endpointPrefix = properties.getStatisticsEndpoint();
	}

	/**
	 *	Verify that you can get the statical information correctly.
	 */
	@Test
	public void getStatistics() throws Exception {
		MockHttpServletResponse response = mockMvc
			.perform(
				get(endpointPrefix)
			)
			.andReturn()
			.getResponse();

		assert response.getStatus() == HttpStatus.OK.value();
	}
}
