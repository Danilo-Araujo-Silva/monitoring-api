package com.n26.finance.monitoring.api.model.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;


/**
 *	A class used to store the main system properties.
 */
@Component
public class Properties {

	/**
	 * The unit of time considered, for example, 1 second (1000 miliseconds).
	 */
	@Value("${n26.finance.monitoring.api.repository.unit}")
	private Integer unit;

	/**
	 * The length of the repository list, for example, 60 positions.
	 */
	@Value("${n26.finance.monitoring.api.repository.length}")
	private Integer length;

	/**
	 * The amount of empty spaces on the list, for example, 10 positions.
	 */
	@Value("${n26.finance.monitoring.api.repository.space}")
	private Integer space;

	/**
	 * The statistics endpoint url.
	 */
	@Value("${n26.finance.monitoring.api.endpoint.statistics}")
	private String statisticsEndpoint;

	/**
	 * The transactions enpoint url.
	 */
	@Value("${n26.finance.monitoring.api.endpoint.transactions}")
	private String transactionsEndpoint;

	/**
	 * Spring environment object.
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
