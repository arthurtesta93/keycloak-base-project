package com.construcaosw.keycloak.service;

import com.construcaosw.keycloak.dto.UserDataRequest;
import com.construcaosw.keycloak.dto.UserDataResponse;
import com.construcaosw.keycloak.utils.AuthToken;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
@Service
@RequiredArgsConstructor
public class UserService {

    @Value("${client.keycloak.url}")
    private String baseUrl;

    public List<UserDataResponse> getAllUsers() {

        String authToken = AuthToken.retrieveToken();

        HttpHeaders headers = new HttpHeaders();
        RestTemplate restTemplate = new RestTemplate();

        headers.setBearerAuth(authToken);

        HttpEntity<String> request = new HttpEntity<>(headers);

        return restTemplate.exchange(
                baseUrl + "/admin/realms/construcao-sw/users",
                HttpMethod.GET,
                request,
                new ParameterizedTypeReference<List<UserDataResponse>>() {
                }
        ).getBody();
    }

    public UserDataResponse getUser(String userId) {

        String authToken = AuthToken.retrieveToken();

        HttpHeaders headers = new HttpHeaders();
        RestTemplate restTemplate = new RestTemplate();

        headers.setBearerAuth(authToken);
        HttpEntity<String> request = new HttpEntity<>(headers);

        return restTemplate.exchange(
                baseUrl + "/admin/realms/construcao-sw/users/" + userId,
                HttpMethod.GET,
                request,
                UserDataResponse.class
        ).getBody();
    }

    public String createUser(UserDataRequest user) {

        UserDataRequest requestBody = UserDataRequest.builder()
                .username(user.getUsername())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .password(user.getPassword())
                .email(user.getEmail())
                .enabled(user.getEnabled())
                .build();
        System.out.println(user.getUsername());
        String authToken = AuthToken.retrieveToken();

        HttpHeaders headers = new HttpHeaders();
        RestTemplate restTemplate = new RestTemplate();

        Gson gson = new Gson();

        String jsonRequest = gson.toJson(requestBody);
        System.out.println(jsonRequest);

        headers.setBearerAuth(authToken);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> request = new HttpEntity<>(jsonRequest, headers);

        return restTemplate.exchange(
                baseUrl + "/admin/realms/construcao-sw/users",
                HttpMethod.POST,
                request,
                String.class
        ).getBody();
    }

    public String updateUser(String userId, UserDataRequest userDataRequest) {

        UserDataRequest requestBody = UserDataRequest.builder()
                .username(userDataRequest.getUsername())
                .firstName(userDataRequest.getFirstName())
                .lastName(userDataRequest.getLastName())
                .email(userDataRequest.getEmail())
                .enabled(userDataRequest.getEnabled())
                .build();

        String authToken = AuthToken.retrieveToken();

        HttpHeaders headers = new HttpHeaders();
        RestTemplate restTemplate = new RestTemplate();

        Gson gson = new Gson();

        String jsonRequest = gson.toJson(requestBody);

        headers.setBearerAuth(authToken);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> request = new HttpEntity<>(jsonRequest, headers);

        return restTemplate.exchange(
                baseUrl + "/admin/realms/construcao-sw/users/" + userId,
                HttpMethod.PUT,
                request,
                String.class
        ).getBody();
    }


    public String deleteUser(String userId) {

        String authToken = AuthToken.retrieveToken();

        HttpHeaders headers = new HttpHeaders();
        RestTemplate restTemplate = new RestTemplate();

        JsonObject requestBody = new JsonObject();
        requestBody.addProperty("enabled", false);

        String jsonRequest = requestBody.toString();

        System.out.println(requestBody);
        headers.setBearerAuth(authToken);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> request = new HttpEntity<>(jsonRequest, headers);

        return restTemplate.exchange(
                baseUrl + "/admin/realms/construcao-sw/users/" + userId,
                HttpMethod.PUT,
                request,
                String.class
        ).getBody();
    }


}
