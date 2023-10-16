package com.construcaosw.oauth;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
public class OauthController {

    @GetMapping("/register")
    public String hello(){
        return "Hello World";
    }

    @PostMapping("/login")
    public HttpEntity<String> login(@RequestParam("client_id") String clientId,
                                    @RequestParam("client_secret") String clientSecret,
                                    @RequestParam("username") String username,
                                    @RequestParam("password") String password,
                                    @RequestParam("grant_type") String grantType) {

        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("client_id", clientId);
        requestBody.add("client_secret", clientSecret);
        requestBody.add("username", username);
        requestBody.add("password", password);
        requestBody.add("grant_type", grantType);
        System.out.println(requestBody);

        // Set the request headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        // Create a RestTemplate instance
        RestTemplate restTemplate = new RestTemplate();
        System.out.println(requestBody);
        System.out.println(headers);

        // Make the POST request to the Keycloak token endpoint
        // Return the response received from Keycloak
        return restTemplate.exchange(
                "http://127.0.0.1/realms/construcao-sw/protocol/openid-connect/token",
                HttpMethod.POST,
                new HttpEntity<>(requestBody, headers),
                String.class
        );

    }

}
