package com.n26.finance.monitoring.api.model.bo;

import com.n26.finance.monitoring.api.model.configuration.Properties;
import com.n26.finance.monitoring.api.model.pojo.StatisticPOJO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 */
@Component
public class StatisticBO extends AbstractBO {

	/**
	 *
	 */
	@Autowired
	private Properties properties;

	/**
	 *
	 * @return
	 */
	public StatisticPOJO run() {
		return new StatisticPOJO();
	}
}
