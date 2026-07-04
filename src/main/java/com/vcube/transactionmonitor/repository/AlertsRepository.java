package com.vcube.transactionmonitor.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vcube.transactionmonitor.entity.Alerts;

public interface AlertsRepository extends JpaRepository<Alerts,Long> {

}
