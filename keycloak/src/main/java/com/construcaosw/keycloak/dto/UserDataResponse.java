package com.construcaosw.keycloak.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDataResponse {

    private String id;
    private String username;
    private String firstName;
    private String lastName;
    private Boolean enabled;


}