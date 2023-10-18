package com.construcaosw.keycloak.service;


import com.construcaosw.keycloak.dto.KeycloakTokenResponse;
import com.construcaosw.keycloak.dto.LoginRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class LoginService {

    @Value("${client.keycloak.url}")
    private String baseUrl;

    @Value("${client.keycloak.client-id}")
    private String clientId;

    @Value("${client.keycloak.client-secret}")
    private String clientSecret;

    private static final String GRANT_TYPE_PASSWORD = "password";

    public KeycloakTokenResponse login(LoginRequest loginRequest) {

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();

        map.add("client_id", clientId);
        map.add("client_secret", clientSecret);
        map.add("username", loginRequest.getUsername());
        map.add("password", loginRequest.getPassword());
        map.add("grant_type", GRANT_TYPE_PASSWORD);

        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map, headers);

        return restTemplate.exchange(
                baseUrl + "/realms/construcao-sw/protocol/openid-connect/token",
                HttpMethod.POST,
                entity,
                KeycloakTokenResponse.class
        ).getBody();
    }
}

