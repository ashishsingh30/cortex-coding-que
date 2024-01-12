package com.salesforce.integeration.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SchedularService {
	 @Autowired
	    private CustomerService customerService;

	    @Scheduled(fixedRate = 100000) // runs every 100 seconds
	    public void syncData() {
	        customerService.synchronizeWithSalesforce();
	    }
}
