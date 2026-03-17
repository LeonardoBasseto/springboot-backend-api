package com.leonardo.backend_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.leonardo.backend_api.dto.AcessDTO;
import com.leonardo.backend_api.dto.AuthenticationDTO;
import com.leonardo.backend_api.dto.UsuarioDTO;
import com.leonardo.backend_api.service.AuthService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/auth")
@CrossOrigin
@Tag(name = "Autenticação", description = "Endpoints de autenticação")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Operation(summary = "Realiza login", description = "Autentica o usuário, retorna o token JWT e envia por email")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Login realizado com sucesso"),
        @ApiResponse(responseCode = "401", description = "Credenciais inválidas")
    })
    @PostMapping("/login")
    public ResponseEntity<AcessDTO> login(@RequestBody AuthenticationDTO authDto) {
        return ResponseEntity.ok(authService.login(authDto));
    }

    @Operation(summary = "Registrar novo usuário", description = "Cria um novo usuário e envia email de confirmação")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Usuário criado, aguardando confirmação por email"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PostMapping("/registrar")
    public ResponseEntity<UsuarioDTO> registrar(@RequestBody UsuarioDTO usuario) {
        return ResponseEntity.status(201).body(authService.registrar(usuario));
    }

    @Operation(summary = "Confirmar cadastro", description = "Confirma o cadastro através do token enviado por email")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Cadastro confirmado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Token inválido ou expirado")
    })
    @GetMapping("/confirmar")
    public ResponseEntity<String> confirmarCadastro(
        @Parameter(description = "Token de confirmação enviado por email", required = true)
        @RequestParam String token
    ) {
        return ResponseEntity.ok(authService.confirmarCadastro(token));
    }
}