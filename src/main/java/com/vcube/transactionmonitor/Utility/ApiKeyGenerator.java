package com.vcube.transactionmonitor.Utility;

import java.util.UUID;

public class ApiKeyGenerator {
	private ApiKeyGenerator() {
		
	}
	
	public static String generate() {
		
		UUID key= UUID.randomUUID();
		String value=UUID.randomUUID().toString().replace("-","");
		
		return "PK"+value ;
	}
}
