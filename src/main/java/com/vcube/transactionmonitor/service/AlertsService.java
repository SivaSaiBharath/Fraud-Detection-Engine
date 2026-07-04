package com.vcube.transactionmonitor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vcube.transactionmonitor.entity.Alerts;
import com.vcube.transactionmonitor.repository.AlertsRepository;

@Service
public class AlertsService {
	
	@Autowired
	AlertsRepository alertRepo;
	
	public Alerts newAlert(Alerts alert) {
		return alertRepo.save(alert);
	}

}
