package com.leonardo.backend_api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
@OpenAPIDefinition(
    info = @io.swagger.v3.oas.annotations.info.Info(
        title = "Backend API",
        version = "1.0",
        description = "API para gerenciamento de usuários, perfis e recursos"
    ),
    tags = {
        @Tag(name = "A - Autenticação"),
        @Tag(name = "B - Usuários"),
        @Tag(name = "C - Perfis"),
        @Tag(name = "D - Perfil Usuário"),
        @Tag(name = "E - Recursos"),
        @Tag(name = "F - Permissões de Perfil e Recurso")
    }
)
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .addSecurityItem(new SecurityRequirement().addList("Bearer Authentication"))
                .components(new Components()
                        .addSecuritySchemes("Bearer Authentication", new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")));
    }
}