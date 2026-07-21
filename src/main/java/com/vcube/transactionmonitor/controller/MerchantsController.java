package com.vcube.transactionmonitor.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vcube.transactionmonitor.DTO.MerchantResponse;
import com.vcube.transactionmonitor.entity.Merchant;
import com.vcube.transactionmonitor.exception.MerchantAlreadyExists;
import com.vcube.transactionmonitor.service.MerchantsService;

@RestController
@RequestMapping("/api/v1/merchants")
public class MerchantsController {
	
	
	private MerchantsService merchantsService;
	
	public MerchantsController(MerchantsService merchantsService) {
		this.merchantsService=merchantsService;
	}
	
	
	@PostMapping("/register")
	public String register(@RequestBody Merchant merchant) throws MerchantAlreadyExists {

		return merchantsService.registerMerchant(merchant);
	}
	
	
	
	@GetMapping("/{merchantId}")
	public MerchantResponse getMerchantById(@PathVariable String merchantId	) {

		return merchantsService.merchantByID(merchantId);
	}
	
	
	
	@GetMapping
	public List<MerchantResponse> listOfMerchants(	) {

		return merchantsService.listOfMerchants();
	}

}
