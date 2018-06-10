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
	@Value("${n26.finance.monitoring.api.threshold}")
	private Integer millisThreshold;

	/**
	 *
	 */
	private Integer secondsThreshold;

	/**
	 *
	 */
	@Value("${n26.finance.monitoring.api.breathe}")
	private Integer millisBreathe;

	/**
	 *
	 */
	private Integer secondsBreathe;

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

	@PostConstruct
	private void initialize() {
		secondsThreshold = (int) Math.floor(millisThreshold / 1000);
		secondsBreathe = (int) Math.floor(millisBreathe / 1000);
	}

	/**
	 *
	 * @return
	 */
	public Integer getMillisThreshold() {
		return millisThreshold;
	}

	/**
	 *
	 * @return
	 */
	public Integer getSecondsThreshold() {
		return secondsThreshold;
	}

	/**
	 *
	 * @return
	 */
	public Integer getMillisBreathe() {
		return millisBreathe;
	}

	/**
	 *
	 * @return
	 */
	public Integer getSecondsBreathe() {
		return secondsBreathe;
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
