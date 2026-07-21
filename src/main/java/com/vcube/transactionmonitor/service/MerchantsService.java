package com.vcube.transactionmonitor.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.vcube.transactionmonitor.DTO.MerchantResponse;
import com.vcube.transactionmonitor.Utility.ApiKeyGenerator;
import com.vcube.transactionmonitor.Utility.MerchantIdGenerator;
import com.vcube.transactionmonitor.entity.Merchant;
import com.vcube.transactionmonitor.entity.Merchant.MerchantStatus;
import com.vcube.transactionmonitor.exception.MerchantAlreadyExists;
import com.vcube.transactionmonitor.exception.MerchantNotFoundException;
import com.vcube.transactionmonitor.repository.MerchantRepository;

@Service
public class MerchantsService {

	private MerchantRepository merchantRepo;

	public MerchantsService(MerchantRepository merchantRepo) {
		this.merchantRepo = merchantRepo;
	}

	public String registerMerchant(Merchant merchant) throws MerchantAlreadyExists {

		String businessName = merchant.getBusinessName();
		String email = merchant.getEmail();
		String phone = merchant.getPhone();

		// Duplicate email check
		if (merchantRepo.existsByEmail(email)) {
			System.out.println(" merchant already exists...");

			throw new MerchantAlreadyExists("Merchant already exists with email: " + email);
		}

		// Business name validation
		if (businessName == null || businessName.isBlank()) {
			System.out.println(" merchant null...");

			throw new IllegalArgumentException("Business name is required");
		}

		// Email validation
		if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
			throw new IllegalArgumentException("Invalid email format");
		}

		// Phone validation
		if (!phone.matches("^[6-9][0-9]{9}$")) {
			System.out.println(" merchant num exists...");

			throw new IllegalArgumentException("Invalid phone number");
		}

		// Set default values
		merchant.setStatus(MerchantStatus.ACTIVE);
		merchant.setCreatedAt(LocalDateTime.now());
		
		// Generate business IDs
		merchant.setMerchantId(MerchantIdGenerator.generate());
		merchant.setApiKey(ApiKeyGenerator.generate());

		//  save (DB generates ID)

		merchantRepo.save(merchant);

		return "Merchant registered successfully";

	}

	public MerchantResponse merchantByID(String MerchantId) {

		// NEED TO USE DTO , SO WE STOP EXPOSING APIS
		// EXPOSE ONLY MERCHANTiD,NAME,EMAIL,PHONE,STATUS,CREATEDAT
		
		
		Optional<Merchant> optionalMerchant=merchantRepo.findByMerchantId(MerchantId);

		if (optionalMerchant.isEmpty()) {
		    throw new MerchantNotFoundException("Merchant Not Found");
		}
		else {
			MerchantResponse response=new MerchantResponse();
			
			Merchant merchant = optionalMerchant.get();
			
			response.setMerchantId(merchant.getMerchantId());
			response.setBusinessName(merchant.getBusinessName());
			response.setEmail(merchant.getEmail());
			response.setPhone(merchant.getPhone());
			response.setStatus(merchant.getStatus());
			response.setCreatedAt(merchant.getCreatedAt());

			return response;
		}
	}

	
	
	public List<MerchantResponse> listOfMerchants() {

	    List<Merchant> merchants = merchantRepo.findAll();

	    List<MerchantResponse> responseList = new ArrayList<>();

	    for (Merchant merchant : merchants) {

	        MerchantResponse response = new MerchantResponse();

	        response.setMerchantId(merchant.getMerchantId());
	        response.setBusinessName(merchant.getBusinessName());
	        response.setEmail(merchant.getEmail());
	        response.setPhone(merchant.getPhone());
	        response.setStatus(merchant.getStatus());
	        response.setCreatedAt(merchant.getCreatedAt());

	        responseList.add(response);
	    }

	    return responseList;
	}
	
}
