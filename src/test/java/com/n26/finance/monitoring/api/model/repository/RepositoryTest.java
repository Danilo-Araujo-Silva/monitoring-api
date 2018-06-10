package com.n26.finance.monitoring.api.model.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.n26.finance.monitoring.api.model.pojo.StatisticPOJO;
import com.n26.finance.monitoring.api.model.pojo.TransactionPOJO;
import com.n26.finance.monitoring.api.model.test.AbstractTest;
import com.n26.finance.monitoring.api.model.util.JsonUtil;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.logging.Logger;

/**
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class RepositoryTest extends AbstractTest {

	/**
	 *
	 */
	@Autowired
	private Repository repository;

	/**
	 *
	 */
	private Logger logger = Logger.getLogger(RepositoryTest.class.getCanonicalName());

	/**
	 *
	 * @throws JsonProcessingException
	 */
	@Test
	public void run() throws JsonProcessingException, InterruptedException {
		TransactionPOJO transactionPOJO1 = TransactionPOJO.getUpToDateRandom();
		transactionPOJO1.setAmount(10d);

		TransactionPOJO transactionPOJO2 = TransactionPOJO.getUpToDateRandom();
		transactionPOJO2.setAmount(1_000d);

		repository.add(transactionPOJO1);
		repository.add(transactionPOJO2);

		for (Integer i = 0; i < 50; i++) {
			TransactionPOJO transactionPOJO = TransactionPOJO.getUpToDateRandom();

			logger.finest("LIST: " + JsonUtil.toJson(repository.getList()));
			logger.finest("SUMMARY: "+ JsonUtil.toJson(repository.getCurrentSummary()));

			repository.add(transactionPOJO);

			Thread.sleep(RandomUtils.nextLong(0, 500));
		}

		StatisticPOJO summary = repository.getCurrentSummary();

		assert summary != null;
		assert summary.getCount() > 10;
		assert summary.getAvg() > 0d;
		assert summary.getMin() <= transactionPOJO1.getAmount();
		assert summary.getMax() >= transactionPOJO2.getAmount();
		assert summary.getSum() > transactionPOJO1.getAmount() + transactionPOJO2.getAmount();
	}
}
