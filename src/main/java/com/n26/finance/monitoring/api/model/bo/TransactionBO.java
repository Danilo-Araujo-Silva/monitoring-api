package com.n26.finance.monitoring.api.model.bo;

import com.n26.finance.monitoring.api.model.configuration.Properties;
import com.n26.finance.monitoring.api.model.pojo.TransactionPOJO;
import com.n26.finance.monitoring.api.model.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;

/**
 * The transactions BO.
 * Handle the incoming transactions, saving them or discarding them.
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
	 *	Check if the transaction timestamp is valid for insertion.
	 */
	public Boolean isValidTimestamp(Long timestamp) {
		Long threshold = getThresholdInstantInMillis();
		Long now = Instant.now().toEpochMilli();

		return timestamp >= threshold && timestamp <= now;
	}

	/**
	 *	Gets the threshold that defines whether or not a transaction will be saved.
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
	 *	Insert a transaction into the repository.
	 *
	 * @param transactionPOJO
	 */
	public void insert(TransactionPOJO transactionPOJO) {
		repository.add(transactionPOJO);
	}
}
