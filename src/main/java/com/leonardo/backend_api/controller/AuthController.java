package com.leonardo.backend_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leonardo.backend_api.dto.AuthenticationDTO;
import com.leonardo.backend_api.dto.UsuarioDTO;
import com.leonardo.backend_api.service.AuthService;
import com.leonardo.backend_api.service.UsuarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/auth")
@CrossOrigin
@Tag(name = "Autenticação", description = "Endpoints de autenticação")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private UsuarioService usuarioService;

    @Operation(summary = "Realiza login", description = "Autentica o usuário e retorna o token JWT")
    @PostMapping(value = "/login")
    public ResponseEntity<?> login(@RequestBody AuthenticationDTO authDto){
        return ResponseEntity.ok(authService.login(authDto));
    }

    @Operation(summary = "Cadastra novo usuário", description = "Cria um novo usuário no sistema")
    @PostMapping(value = "/novoUsuario")
    public void inserirNovoUsuario(@RequestBody UsuarioDTO novoUsuario){
        usuarioService.inserir(novoUsuario);
    }
}