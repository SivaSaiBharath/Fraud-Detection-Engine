package com.vcube.transactionmonitor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.vcube.transactionmonitor.entity.Alerts;
import com.vcube.transactionmonitor.service.AlertsService;

@RestController
public class AlertsController {
	@Autowired
	AlertsService alertService;

	@PostMapping("/alert")
	public Alerts newAlert(@RequestBody Alerts alert) {
		System.out.println("TransactionId = " + alert.getTransactionId());
		System.out.println("Reason = " + alert.getReason());
		System.out.println("Severity = " + alert.getSeverity());
		System.out.println("Action = " + alert.getAction());
		System.out.println("Resolved = " + alert.isResolved());
		return alertService.newAlert(alert);
	}
}
