package com.vcube.transactionmonitor.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.vcube.transactionmonitor.entity.Alerts;
import com.vcube.transactionmonitor.repository.AlertsRepository;

@Service
public class AlertsService {
	
	
	private AlertsRepository alertRepo;
	//CONSTRUCTOR INJECTION
	public AlertsService(AlertsRepository alertRepo){
		this.alertRepo=alertRepo;
	}
	
	
	//METHODS
	
	public List<Alerts> getAllAlerts(){
		return alertRepo.findAll();
	}
	
	
	public List<Alerts> getAlertsById(Iterable<Long> id){
		return alertRepo.findAllById(id);
	}
	
	
}
