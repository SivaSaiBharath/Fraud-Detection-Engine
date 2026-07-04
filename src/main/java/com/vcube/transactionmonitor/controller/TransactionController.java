package com.vcube.transactionmonitor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.vcube.transactionmonitor.entity.Transaction;
import com.vcube.transactionmonitor.service.TransactionService;

@RestController
public class TransactionController {

	@Autowired
	TransactionService txnService;

	@PostMapping("/requestTxn")
	public Transaction insertTxn(@RequestBody Transaction txn) {
		return txnService.requestTxn(txn);
	}
}
