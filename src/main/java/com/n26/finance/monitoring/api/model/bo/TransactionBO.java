package com.n26.finance.monitoring.api.model.bo;

import com.n26.finance.monitoring.api.model.pojo.TransactionPOJO;

import java.time.Instant;

/**
 *
 */
public class TransactionBO extends AbstractBO {

	/**
	 *
	 */
	public Boolean isValidTimestamp(Long timestamp) {
		Long threshold = getThresholdInstantInMillis();

		return timestamp >= threshold;
	}

	/**
	 *
	 * @return
	 */
	public Instant getThresholdInstant() {
		return Instant.now().minusSeconds(60);
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
	public void handle(TransactionPOJO transactionPOJO) {
	}
}
