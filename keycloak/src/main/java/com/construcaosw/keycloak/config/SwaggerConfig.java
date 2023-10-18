package com.construcaosw.keycloak.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi api1() {
        return GroupedOpenApi.builder()
                .group("auth-api-1.0")
                .pathsToMatch("/**")
                .build();
    }

    @Bean
    public OpenAPI customizeOpenAPI() {
        final String securitySchemeName = "bearerAuth";
        return new OpenAPI()
                .info(new Info()
                        .title("Spring API")
                        .description("Spring application using keycloak.")
                        .version("v1")
                )
                .externalDocs(new ExternalDocumentation()
                        .description("Spring Api Repo")
                        .url("https://github.com/pucrs-constrsw-2023-2/grupo1-p1")
                )
                .addSecurityItem(new SecurityRequirement()
                        .addList(securitySchemeName)
                )
                .components(new Components()
                        .addSecuritySchemes(securitySchemeName, new SecurityScheme()
                                .name(securitySchemeName)
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")
                        )
                );
    }
}