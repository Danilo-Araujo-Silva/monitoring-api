package com.n26.finance.monitoring.api.model.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;


/**
 *
 */
@Component
public class Properties {

	/**
	 *
	 */
	@Value("${n26.finance.monitoring.api.threshold}")
	private Integer threshold;

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
	 * @return
	 */
	public Integer getThreshold() {
		return threshold;
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
