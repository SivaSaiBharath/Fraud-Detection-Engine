package com.vcube.transactionmonitor.rules;


public class RuleViolation {

	private String reason;
	private SeverityType Severity;
	private ActionType action;

	public enum SeverityType {
		LOW, MEDIUM, HIGH
	}
	public enum ActionType {
	    ALERT_ONLY,
	    LOCK_ACCOUNT
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public SeverityType getSeverity() {
		return Severity;
	}

	public void setSeverity(SeverityType severity) {
		Severity = severity;
	}

	public ActionType getAction() {
		return action;
	}

	public void setAction(ActionType action) {
		this.action = action;
	}
}
