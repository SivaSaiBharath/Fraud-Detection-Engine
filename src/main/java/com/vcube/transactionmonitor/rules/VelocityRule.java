package com.vcube.transactionmonitor.rules;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vcube.transactionmonitor.entity.Accounts;
import com.vcube.transactionmonitor.entity.Transaction;
import com.vcube.transactionmonitor.repository.TransactionRepository;

@Component
public class VelocityRule implements FraudRule {

	@Autowired
	TransactionRepository txnRepo;

	@Override
	public RuleViolation check(Transaction txn, Accounts account) {

		Transaction prevTxn = txnRepo.findTopByAccountIdAndTimestampLessThanOrderByTimestampDesc(account.getId(),
				txn.getTimestamp());

		if (prevTxn == null) {
			return null;
		}

		if (prevTxn.getLocation().equalsIgnoreCase(txn.getLocation())) {
			return null;
		}

		Duration duration = Duration.between(prevTxn.getTimestamp(), txn.getTimestamp());
		long minutes = duration.toMinutes();

		if (minutes < 60) {

			RuleViolation violation = new RuleViolation();
			violation.setSeverity(RuleViolation.SeverityType.HIGH);
			violation.setAction(RuleViolation.ActionType.LOCK_ACCOUNT);
			violation.setReason("Suspicious location change detected — possible card cloning");
			return violation;

		}
		return null;

	}

}
