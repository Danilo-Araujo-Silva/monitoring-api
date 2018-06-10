package com.n26.finance.monitoring.api.model.bo;

import com.n26.finance.monitoring.api.model.pojo.StatisticPOJO;
import com.n26.finance.monitoring.api.model.repository.Repository;
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
	private Repository repository;

	/**
	 *
	 * @return
	 */
	public StatisticPOJO run() {
		return repository.getCurrentSummary();
	}
}
