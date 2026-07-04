package com.vcube.transactionmonitor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vcube.transactionmonitor.entity.Accounts;
import com.vcube.transactionmonitor.repository.AccountsRepository;

@Service
public class AccountService {

	@Autowired
	AccountsRepository accrepo;

	public Accounts getbyId(long id) {
		return accrepo.findById(id).orElseThrow();
	}

	public List<Accounts> listOfAcc() {
		return accrepo.findAll();
	}

	public Accounts insertAcc(Accounts Acc) {
		return accrepo.save(Acc);
	}
}
