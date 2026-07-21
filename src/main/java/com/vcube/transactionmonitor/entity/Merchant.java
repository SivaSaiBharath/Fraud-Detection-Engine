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



@Entity
@Table(name="merchants")
public class Merchant {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true)
	private String merchantId;
	
	

	@Column(name="business_name")
	private String businessName;
	
	@Column(unique = true)
	private String email;
	
	private String phone;
	
	@Column(unique = true)
	private String apiKey;
	
	@Enumerated(EnumType.STRING)
	private MerchantStatus status;
	
	public enum MerchantStatus  {
		ACTIVE,INACTIVE,BLOCKED;
	}
	
	@Column(name = "created_at")
	private LocalDateTime createdAt;	
	
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMerchantId() {
	    return merchantId;
	}

	public void setMerchantId(String merchantId) {
	    this.merchantId = merchantId;
	}

	public String getBusinessName() {
	    return businessName;
	}

	public void setBusinessName(String businessName) {
	    this.businessName = businessName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getApiKey() {
	    return apiKey;
	}

	public void setApiKey(String apiKey) {
	    this.apiKey = apiKey;
	}
	public MerchantStatus getStatus() {
		return status;
	}

	public void setStatus(MerchantStatus status) {
		this.status = status;
	}
	public LocalDateTime getCreatedAt() {
	    return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
	    this.createdAt = createdAt;
	}
}
