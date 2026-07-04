package com.vcube.transactionmonitor.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vcube.transactionmonitor.entity.Accounts;

public interface AccountsRepository extends JpaRepository<Accounts,Long>{

}
