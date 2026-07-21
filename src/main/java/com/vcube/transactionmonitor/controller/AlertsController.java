package com.vcube.transactionmonitor.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vcube.transactionmonitor.entity.Alerts;
import com.vcube.transactionmonitor.service.AlertsService;

@RestController
@RequestMapping("/Alerts")
public class AlertsController {
	
	
	private AlertsService alertService;
	
	public AlertsController(AlertsService alertService) {
		this.alertService=alertService;
	}


	
	
	@GetMapping()
	public List<Alerts> getAllAlerts(){
		return alertService.getAllAlerts();
	}
	
	@GetMapping("/{id}")
	public List<Alerts> getAlertsById(@PathVariable Iterable<Long> id){
		return alertService.getAlertsById(id);
	}
	
}
