package com.vcube.transactionmonitor.DTO;

import java.time.LocalDateTime;

import com.vcube.transactionmonitor.entity.Merchant.MerchantStatus;

public class MerchantResponse {
	
	
	private String merchantId;
    private String businessName;
    private String email;
    private String phone;
    private MerchantStatus status;
    private LocalDateTime createdAt;
    
    
    
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
