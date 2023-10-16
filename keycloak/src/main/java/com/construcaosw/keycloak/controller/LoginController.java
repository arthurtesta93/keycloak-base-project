package com.construcaosw.keycloak.controller;

import com.construcaosw.keycloak.dto.KeycloakTokenResponse;
import com.construcaosw.keycloak.dto.LoginRequest;
import com.construcaosw.keycloak.service.LoginService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/login")
@Tag(name = "Autenticação", description = "Autenticação segura de usuários")
public class LoginController {

    private final LoginService loginService;

    @PostMapping
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Login realizado com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Erro na requisição"),
                    @ApiResponse(responseCode = "401", description = "Usuário ou senha inválidos"),
                    @ApiResponse(responseCode = "500", description = "Erro interno")
            }
    )
    public ResponseEntity<KeycloakTokenResponse> login(@RequestBody LoginRequest loginRequestDTO) {
        return ResponseEntity.ok(loginService.login(loginRequestDTO));
    }
}