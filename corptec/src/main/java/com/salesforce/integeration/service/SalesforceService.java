package com.salesforce.integeration.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.salesforce.integeration.model.Customer;
import com.salesforce.integeration.security.SalesforceOAuthClient;

@Service
public class SalesforceService {

    @Autowired
    private SalesforceOAuthClient salesforceOAuthClient;

    
    public List<Customer> getUsersFromSalesforce() {
       
    	 String accessToken = salesforceOAuthClient.getAccessToken();
    	String url = "https://drive-nosoftware-4833.my.salesforce.com/services/data/v52.0/query/?q=SELECT+Id,+Username,+Email+FROM+User";

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

        ObjectMapper mapper = new ObjectMapper();
        List<Customer> customers = null;
        try {
            // Assuming the response body has the user data in a field named 'records'
            String jsonContent = mapper.readTree(response.getBody()).get("records").toString();
            customers = mapper.readValue(jsonContent, new TypeReference<List<Customer>>() {});
        } catch (IOException e) {
            e.printStackTrace();
        }

        return customers;
    }
    
    
}
