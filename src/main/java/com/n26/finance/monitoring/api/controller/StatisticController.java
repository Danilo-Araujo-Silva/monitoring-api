package com.n26.finance.monitoring.api.controller;

import com.n26.finance.monitoring.api.model.bo.StatisticBO;
import com.n26.finance.monitoring.api.model.pojo.StatisticPOJO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * The statistics controller.
 */
@Controller("statisticController")
@CrossOrigin(origins = "*")
public class StatisticController {

	/**
	 *
	 */
	@Autowired
	private StatisticBO statisticBO;

	/**
	 *	Retrieve the latest statistical information over the transactions.
	 */
	@GetMapping("${n26.finance.monitoring.api.endpoint.statistics}")
	public ResponseEntity<StatisticPOJO> getStatistics() {
		return new ResponseEntity<>(statisticBO.run(), HttpStatus.OK);
	}
}
