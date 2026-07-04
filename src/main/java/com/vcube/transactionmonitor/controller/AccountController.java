package com.vcube.transactionmonitor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.vcube.transactionmonitor.entity.Accounts;
import com.vcube.transactionmonitor.service.AccountService;

@RestController
public class AccountController {

	@Autowired
	AccountService accService;

	@PostMapping("/acc")
	public Accounts insertAcc(@RequestBody Accounts acc) {
		return accService.insertAcc(acc);
	}

	@GetMapping("/getAccById/{id}")
	public Accounts getAccById(@PathVariable("id") long id) {
		return accService.getbyId(id);
	}

	@GetMapping("/listOfAcc")
	public List<Accounts> getAllAcc() {
		return accService.listOfAcc();
	}

}
