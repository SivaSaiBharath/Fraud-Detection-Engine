package com.vcube.transactionmonitor.Utility;

import java.util.UUID;

public class MerchantIdGenerator {

    private MerchantIdGenerator() {
    }

    public static String generate() {
        return "MER" + UUID.randomUUID()
                           .toString()
                           .replace("-", "")
                           .substring(0, 8)
                           .toUpperCase();
    }
}