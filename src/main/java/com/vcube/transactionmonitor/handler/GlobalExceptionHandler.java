package com.vcube.transactionmonitor.handler;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.vcube.transactionmonitor.exception.AccountLockedException;
import com.vcube.transactionmonitor.exception.AccountNotFound;
import com.vcube.transactionmonitor.exception.ErrorResponse;

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
}