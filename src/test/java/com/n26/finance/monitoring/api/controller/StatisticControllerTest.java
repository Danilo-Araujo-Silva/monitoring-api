package com.n26.finance.monitoring.api.controller;

import com.n26.finance.monitoring.api.model.configuration.Properties;
import com.n26.finance.monitoring.api.model.pojo.StatisticPOJO;
import com.n26.finance.monitoring.api.model.pojo.TransactionPOJO;
import com.n26.finance.monitoring.api.model.repository.Repository;
import com.n26.finance.monitoring.api.model.util.JsonUtil;
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
	@Autowired
	private Repository repository;

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

	/**
	 *	Check if the statistic POJO is present.
	 *
	 * @throws Exception
	 */
	@Test
	public void checkIfTheStatisticPOJOIsPresent() throws Exception {
		MockHttpServletResponse response = mockMvc
			.perform(
				get(endpointPrefix)
			)
			.andReturn()
			.getResponse();

		assert response.getStatus() == HttpStatus.OK.value();

		StatisticPOJO pojo = JsonUtil.fromJson(response.getContentAsString(), StatisticPOJO.class);

		assert pojo != null;
	}

	/**
	 *	Check if the statistics are consistent.
	 *
	 * @throws Exception
	 */
	@Test
	public void checkIfTheStatisticsAreConsistent() throws Exception {
		TransactionPOJO transactionPOJO1 = TransactionPOJO.getUpToDateRandom();
		TransactionPOJO transactionPOJO2 = TransactionPOJO.getUpToDateRandom();

		repository.add(transactionPOJO1);
		repository.add(transactionPOJO2);

		MockHttpServletResponse response = mockMvc
			.perform(
				get(endpointPrefix)
			)
			.andReturn()
			.getResponse();

		assert response.getStatus() == HttpStatus.OK.value();

		StatisticPOJO summary = JsonUtil.fromJson(response.getContentAsString(), StatisticPOJO.class);

		assert summary != null;
		assert summary.getCount() >= 2;
		assert summary.getAvg() > 0d;
		assert summary.getMin() <= transactionPOJO1.getAmount();
		assert summary.getMin() <= transactionPOJO2.getAmount();
		assert summary.getMax() >= transactionPOJO1.getAmount();
		assert summary.getMax() >= transactionPOJO2.getAmount();
		assert summary.getSum() >= transactionPOJO1.getAmount() + transactionPOJO2.getAmount();
	}
}
