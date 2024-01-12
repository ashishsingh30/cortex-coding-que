package com.salesforce.integeration.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.salesforce.integeration.service.CustomerService;

@RestController
public class SchedularController {

	@Autowired
    private CustomerService customerService;

    @PostMapping("/sync")
    public String triggerSync() {
        customerService.synchronizeWithSalesforce();
        return "Synchronization initiated";
    }
}
	
