package com.vcube.transactionmonitor.rules;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vcube.transactionmonitor.entity.Accounts;
import com.vcube.transactionmonitor.entity.Transaction;
import com.vcube.transactionmonitor.repository.TransactionRepository;

@Component
public class PinAttemptsRule implements FraudRule {

	@Autowired
	TransactionRepository txnRepo;

	@Override
	public RuleViolation check(Transaction txn, Accounts account) {


		if (txn.getIsPinCorrect() == null || txn.getIsPinCorrect()) {
			return null;
		} 
		
		
		else {
			
			LocalDateTime sinceTime = LocalDateTime.now().minusMinutes(10);

			long count = txnRepo.pinFailCount(txn.getAccountId(), sinceTime);

			if (count >= 3) {
				
				RuleViolation violation = new RuleViolation();

				violation.setSeverity(RuleViolation.SeverityType.HIGH);
				violation.setAction(RuleViolation.ActionType.LOCK_ACCOUNT);
				violation.setReason("Pin Try Exceeds");
				return violation;

			}
		}
		return null;

	}

}
