package com.n26.finance.monitoring.api.model.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;


/**
 *
 */
@Component
public class Properties {

	/**
	 *
	 */
	@Value("${n26.finance.monitoring.api.repository.unit}")
	private Integer unit;

	/**
	 *
	 */
	@Value("${n26.finance.monitoring.api.repository.length}")
	private Integer length;

	/**
	 *
	 */
	@Value("${n26.finance.monitoring.api.repository.space}")
	private Integer space;

	/**
	 *
	 */
	@Value("${n26.finance.monitoring.api.endpoint.statistics}")
	private String statisticsEndpoint;

	/**
	 *
	 */
	@Value("${n26.finance.monitoring.api.endpoint.transactions}")
	private String transactionsEndpoint;

	/**
	 *
	 */
	@Autowired
	private Environment environment;

	/**
	 *
	 */
	@PostConstruct
	private void initialize() {
	}

	/**
	 *
	 * @return
	 */
	public Integer getUnit() {
		return unit;
	}

	/**
	 *
	 * @return
	 */
	public Integer getLength() {
		return length;
	}

	/**
	 *
	 * @return
	 */
	public Integer getSpace() {
		return space;
	}

	/**
	 *
	 * @return
	 */
	public String getStatisticsEndpoint() {
		return statisticsEndpoint;
	}

	/**
	 *
	 * @return
	 */
	public String getTransactionsEndpoint() {
		return transactionsEndpoint;
	}

	/**
	 *
	 * @return
	 */
	public Environment getEnvironment() {
		return environment;
	}
}
