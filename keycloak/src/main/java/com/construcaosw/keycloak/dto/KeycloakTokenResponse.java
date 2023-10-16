package com.construcaosw.keycloak.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class KeycloakTokenResponse {

    private String access_token;
    private String token_type;
    private String expires_in;
    private String refresh_token;
    private String refresh_expires_in;
}