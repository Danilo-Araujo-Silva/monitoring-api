package com.n26.finance.monitoring.api.model.pojo;

import org.apache.commons.lang3.RandomUtils;

import java.time.Instant;

/**
 *	POJO class representing a transaction.
 */
public class TransactionPOJO extends AbstractPOJO {

	/**
	 *	The amount of money of the transaction.
	 */
	private Double amount;

	/**
	 * Timestamp where the transaction was created.
	 */
	private Long timestamp;

	/**
	 *
	 * @param timestamp
	 * @param amount
	 */
	public TransactionPOJO(Long timestamp, Double amount) {
		this.amount = amount;
		this.timestamp = timestamp;
	}

	/**
	 *
	 */
	public TransactionPOJO() {
	}

	/**
	 *
	 * @return
	 */
	public Double getAmount() {
		return amount;
	}

	/**
	 *
	 * @param amount
	 */
	public void setAmount(Double amount) {
		this.amount = amount;
	}

	/**
	 *
	 * @return
	 */
	public Long getTimestamp() {
		return timestamp;
	}

	/**
	 *
	 * @param timestamp
	 */
	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	/**
	 *	A method to generate a transaction with a random amount of money and a up to date timestamp.
	 *	Used usually in tests.
	 *
	 * @return
	 */
	public static TransactionPOJO getUpToDateRandom() {
		return new TransactionPOJO(
			Instant.now().toEpochMilli(),
			RandomUtils.nextDouble(0, 10_000)
		);
	}
}
