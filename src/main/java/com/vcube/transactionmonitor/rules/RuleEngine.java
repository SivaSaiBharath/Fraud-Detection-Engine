package com.vcube.transactionmonitor.rules;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.vcube.transactionmonitor.entity.Accounts;
import com.vcube.transactionmonitor.entity.Transaction;

@Component
public class RuleEngine {

	private final List<FraudRule> fraudRules;

	public RuleEngine(List<FraudRule> rules) {
		this.fraudRules = rules;
	}

	public List<RuleViolation> evaluate(Transaction txn, Accounts acc) {

		List<RuleViolation> violations = new ArrayList<>();
		
		for (FraudRule rules : fraudRules) {

			RuleViolation violation = rules.check(txn, acc);
			if (violation != null) {
				violations.add(violation);
			}
		}

		return violations;
	}

}
