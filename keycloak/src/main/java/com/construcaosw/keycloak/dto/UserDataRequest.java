package com.construcaosw.keycloak.dto;

import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Builder;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class UserDataRequest {

    private String username;
    private String firstName;
    private String lastName;
    private String password;
    @Nullable
    private String email;
    @Nullable
    private Boolean enabled;

}