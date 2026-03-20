package com.leonardo.backend_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leonardo.backend_api.dto.UsuarioDTO;
import com.leonardo.backend_api.service.UsuarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/usuarios")
@Tag(name = "B - Usuários", description = "Gerenciamento de usuários")
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Operation(summary = "Alterar usuário", description = "Altera os dados de um usuário já existente")
	@ApiResponses({
	@ApiResponse(responseCode = "200", description = "usuário alterado"),
	@ApiResponse(responseCode = "404", description = "usuário não encontrado")
	})
	@PutMapping("/{id}")
	public UsuarioDTO alterar(@PathVariable Long id,@RequestBody UsuarioDTO usuario) {
		return usuarioService.alterar(id, usuario);
	}
}
