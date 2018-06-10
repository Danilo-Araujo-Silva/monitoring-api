package com.n26.finance.monitoring.api.model.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
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
		for (Integer i = 0; i < 100; i++) {
			TransactionPOJO transactionPOJO = TransactionPOJO.getUpToDateRandom();

			logger.fine("LIST: " + JsonUtil.toJson(repository.getList()));
			logger.fine("SUMMARY: "+ JsonUtil.toJson(repository.getCurrentSummary()));

			repository.add(transactionPOJO);

			Thread.sleep(RandomUtils.nextLong(0, 500));
		}
	}
}
