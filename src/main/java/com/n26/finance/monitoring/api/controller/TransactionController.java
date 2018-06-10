package com.n26.finance.monitoring.api.controller;

import com.n26.finance.monitoring.api.model.bo.TransactionBO;
import com.n26.finance.monitoring.api.model.pojo.TransactionPOJO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 *
 */
@Controller("transactionController")
@CrossOrigin(origins = "*")
public class TransactionController {

	/**
	 *
	 */
	@Autowired
	private TransactionBO transactionBO;

	/**
	 *
	 */
	@PostMapping("${n26.finance.monitoring.api.endpoint.transactions}")
	public ResponseEntity<Void> insertTransaction(@RequestBody(required = true) TransactionPOJO transactionPOJO) {
		if (!transactionBO.isValidTimestamp(transactionPOJO.getTimestamp())) {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		} else {
			transactionBO.insert(transactionPOJO);

			return new ResponseEntity<Void>(HttpStatus.CREATED);
		}
	}
}
