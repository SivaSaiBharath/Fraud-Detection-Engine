package com.vcube.transactionmonitor.rules;

import org.springframework.stereotype.Component;

import com.vcube.transactionmonitor.entity.Accounts;
import com.vcube.transactionmonitor.entity.Transaction;

@Component
public class HighAmountRule implements FraudRule {
	
	
	
	@Override
	public RuleViolation check(Transaction txn, Accounts account) {
		
		if(txn.getAmount()>100000) {
			RuleViolation violation = new RuleViolation();

			violation.setSeverity(RuleViolation.SeverityType.MEDIUM);
			violation.setAction(RuleViolation.ActionType.ALERT_ONLY);
			violation.setReason("Transaction amount exceeds safe limit");
			return violation;
		}
		
		return null;
	}

}
