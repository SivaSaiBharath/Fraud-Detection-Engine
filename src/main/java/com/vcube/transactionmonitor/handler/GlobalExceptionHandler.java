package com.vcube.transactionmonitor.handler;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.vcube.transactionmonitor.exception.AccountLockedException;
import com.vcube.transactionmonitor.exception.AccountNotFound;
import com.vcube.transactionmonitor.exception.ErrorResponse;
import com.vcube.transactionmonitor.exception.MerchantAlreadyExists;
import com.vcube.transactionmonitor.exception.MerchantNotFoundException;
import com.vcube.transactionmonitor.exception.TransactionNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(AccountLockedException.class)
	public ResponseEntity<ErrorResponse> handleAccountLocked(AccountLockedException ex) {
		ErrorResponse response = new ErrorResponse(403, "ACCOUNT_LOCKED", ex.getMessage(), LocalDateTime.now());
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
	}

	@ExceptionHandler(AccountNotFound.class)
	public ResponseEntity<ErrorResponse> handleAccountNotFound(AccountNotFound ex) {
		ErrorResponse response = new ErrorResponse(404, "ACCOUNT_NOT_FOUND", ex.getMessage(), LocalDateTime.now());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	}

	@ExceptionHandler(TransactionNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleTransactionNotFound(TransactionNotFoundException ex) {

		ErrorResponse response = new ErrorResponse(404, "TRANSACTION_NOT_FOUND", ex.getMessage(), LocalDateTime.now());

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	}

	@ExceptionHandler(MerchantAlreadyExists.class)
	public ResponseEntity<ErrorResponse> handleMerchantAlreadyExists(MerchantAlreadyExists ex) {
		ErrorResponse response = new ErrorResponse(409, "MERCHANT_ALREADY_EXISTS", ex.getMessage(),
				LocalDateTime.now());

		return ResponseEntity.status(HttpStatus.CONFLICT).body(response);

	}

	@ExceptionHandler(MerchantNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleMerchantNotFound(MerchantNotFoundException ex) {

		ErrorResponse response = new ErrorResponse(404, "MERCHANT_NOT_FOUND", ex.getMessage(), LocalDateTime.now());

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<ErrorResponse> handleIllegalArgument(IllegalArgumentException ex) {

		ErrorResponse response = new ErrorResponse(400, "BAD_REQUEST", ex.getMessage(), LocalDateTime.now());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	}

}