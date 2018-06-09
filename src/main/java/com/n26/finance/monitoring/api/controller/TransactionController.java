package com.n26.finance.monitoring.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 */
@Controller("transactionController")
@CrossOrigin(origins = "*")
public class TransactionController {

	/**
	 *
	 */
	@PostMapping("/transactions")
	public ResponseEntity<Void> insertTransaction() {
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
}
