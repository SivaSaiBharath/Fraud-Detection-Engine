package com.vcube.transactionmonitor.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "transactions")
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Txn_ID")
	private long id;

	@Column(name = "account_id")
	private long accountId;
	
	@Column(name="pin_correct")
	private Boolean	 isPinCorrect;

	private double amount;

	@Enumerated(EnumType.STRING)
	private TransactionType type;

	private String location;

	@Enumerated(EnumType.STRING)
	private TransactionStatus status;

	private boolean flagged;

	private LocalDateTime timestamp;

	public enum TransactionType {

		DEBIT, CREDIT
	}

	public enum TransactionStatus {
		PENDING, SUCCESS, FLAGGED, REJECTED
	}

	public long getId() {
		return id;
	}

	public long getAccountId() {
		return accountId;
	}

	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public TransactionType getType() {
		return type;
	}

	public void setType(TransactionType type) {
		this.type = type;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public TransactionStatus getStatus() {
		return status;
	}

	public void setStatus(TransactionStatus status) {
		this.status = status;
	}

	public boolean isFlagged() {
		return flagged;
	}

	public void setFlagged(boolean flagged) {
		this.flagged = flagged;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public Boolean getIsPinCorrect() {
		return isPinCorrect;
	}

	public void setIsPinCorrect(Boolean isPinCorrect) {
		this.isPinCorrect = isPinCorrect;
	}

}
