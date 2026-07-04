package com.vcube.transactionmonitor.rules;

import com.vcube.transactionmonitor.entity.Accounts;
import com.vcube.transactionmonitor.entity.Transaction;

public interface FraudRule {
	
	
	//Return type , methodName, params
	
	RuleViolation check(Transaction txn, Accounts account);
	

}
