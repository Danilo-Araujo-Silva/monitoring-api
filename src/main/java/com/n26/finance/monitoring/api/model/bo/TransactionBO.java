package com.n26.finance.monitoring.api.model.bo;

import com.n26.finance.monitoring.api.model.configuration.Properties;
import com.n26.finance.monitoring.api.model.pojo.TransactionPOJO;
import com.n26.finance.monitoring.api.model.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;

/**
 *
 */
@Component
public class TransactionBO extends AbstractBO {

	/**
	 *
	 */
	@Autowired
	private Properties properties;

	/**
	 *
	 */
	@Autowired
	private Repository repository;

	/**
	 *
	 */
	public Boolean isValidTimestamp(Long timestamp) {
		Long threshold = getThresholdInstantInMillis();
		Long now = Instant.now().toEpochMilli();

		return timestamp >= threshold && timestamp <= now;
	}

	/**
	 *
	 * @return
	 */
	public Instant getThresholdInstant() {
		return Instant.now().minusSeconds(properties.getLength());
	}

	/**
	 *
	 * @return
	 */
	public Long getThresholdInstantInMillis() {
		return getThresholdInstant().toEpochMilli();
	}

	/**
	 *
	 * @param transactionPOJO
	 */
	public void insert(TransactionPOJO transactionPOJO) {
		repository.add(transactionPOJO);
	}
}
