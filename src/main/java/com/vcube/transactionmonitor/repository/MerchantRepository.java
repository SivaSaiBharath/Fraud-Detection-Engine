package com.vcube.transactionmonitor.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vcube.transactionmonitor.entity.Merchant;


@Repository
public interface MerchantRepository extends JpaRepository<Merchant , Long>{

	boolean existsByEmail(String email);
	boolean existsByMerchantId(String merchantId);
	
	Optional<Merchant> findByMerchantId(String merchantId);

	Optional<Merchant> findByApiKey(String apiKey);
}
