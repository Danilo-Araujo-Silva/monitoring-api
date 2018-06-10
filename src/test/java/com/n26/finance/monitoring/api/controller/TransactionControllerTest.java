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
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.time.Instant;

/**
 * The transactions endpoint tests.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TransactionControllerTest extends AbstractControllerTest {

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
		endpointPrefix = properties.getTransactionsEndpoint();
	}

	/**
	 * Test the insertion of a transaction up to the threshold.
	 */
	@Test
	public void insertTransactionUpToTheThreshold() throws Exception {
		TransactionPOJO transactionPOJO = TransactionPOJO.getUpToDateRandom();
		String json = JsonUtil.toJson(transactionPOJO);

		Integer status = mockMvc
			.perform(
				post(endpointPrefix)
					.content(json)
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON)
			)
			.andReturn()
			.getResponse()
			.getStatus();

		assert status.equals(HttpStatus.CREATED.value());
	}

	/**
	 *	Test not inserting a transaction older than the threshold.
	 *
	 * @throws Exception
	 */
	@Test
	public void doNotInsertTransactionOlderThanTheThreshold() throws Exception {
		Instant timestamp = Instant.now();
		timestamp = timestamp.minusSeconds(properties.getLength() + 1);

		TransactionPOJO transactionPOJO = TransactionPOJO.getUpToDateRandom();
		transactionPOJO.setTimestamp(timestamp.toEpochMilli());

		String json = JsonUtil.toJson(transactionPOJO);

		Integer status = mockMvc
			.perform(
				post(endpointPrefix)
					.content(json)
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON)
			)
			.andReturn()
			.getResponse()
			.getStatus();

		assert status.equals(HttpStatus.NO_CONTENT.value());
	}

	/**
	 * Check if multiple insertions are consistent.
	 */
	@Test
	public void checkIfMultipleInsertionsAreConsistent() throws Exception {
		TransactionPOJO transactionPOJO1 = TransactionPOJO.getUpToDateRandom();
		TransactionPOJO transactionPOJO2 = TransactionPOJO.getUpToDateRandom();

		String json = JsonUtil.toJson(transactionPOJO1);
		Integer status = mockMvc
			.perform(
				post(endpointPrefix)
					.content(json)
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON)
			)
			.andReturn()
			.getResponse()
			.getStatus();

		assert status.equals(HttpStatus.CREATED.value());

		json = JsonUtil.toJson(transactionPOJO2);
		status = mockMvc
			.perform(
				post(endpointPrefix)
					.content(json)
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON)
			)
			.andReturn()
			.getResponse()
			.getStatus();

		assert status.equals(HttpStatus.CREATED.value());

		StatisticPOJO summary = repository.getCurrentSummary();

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
