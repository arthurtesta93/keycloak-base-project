package com.construcaosw.keycloak.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class KeycloakClient {

    private String clientId;
    private String clientSecret;
    private String userName;
    private String password;
    private String grantType;

}