package com.n26.finance.monitoring.api.model.bo;

import com.n26.finance.monitoring.api.model.pojo.StatisticPOJO;

/**
 *
 */
public class StatisticBO extends AbstractBO {

	/**
	 *
	 * @return
	 */
	public StatisticPOJO run() {
		return new StatisticPOJO();
	}
}
