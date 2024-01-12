package com.salesforce.integeration.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.HashMap;
import java.util.Map;

@Service
public class SalesforceOAuthClient {

    @Value("${salesforce.consumer-key}")
    private String consumerKey;

    @Value("${salesforce.consumer-secret}")
    private String consumerSecret;

    @Value("${salesforce.username}")
    private String username;

    @Value("${salesforce.password}")
    private String password;

    @Value("${salesforce.token-url}")
    private String tokenUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    public String getAccessToken() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        Map<String, String> params = new HashMap<>();
        params.put("grant_type", "password");
        params.put("client_id", consumerKey);
        params.put("client_secret", consumerSecret);
        params.put("username", username);
        params.put("password", password);

        HttpEntity<Map<String, String>> entity = new HttpEntity<>(params, headers);

        ResponseEntity<Map> response = restTemplate.postForEntity(tokenUrl, entity, Map.class);
        Map<String, String> responseBody = response.getBody();

        return responseBody != null ? responseBody.get("access_token") : null;
    }
}
