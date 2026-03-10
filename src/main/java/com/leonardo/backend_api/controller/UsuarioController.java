package com.leonardo.backend_api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
@Tag(name = "Usuários", description = "Gerenciamento de usuários")
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Operation(summary = "Listar todos os usuários", description = "Retorna uma lista com todos os usuários cadastrados")
	@ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
	@GetMapping
	public List<UsuarioDTO> listarTodos(){
	return usuarioService.ListarTodos();
	}
	
	@Operation(summary = "Buscar usuários por ID", description = "Retorna o ID do usuário especificado")
	@ApiResponses({
	@ApiResponse(responseCode = "200", description = "usuário encontrado"),
	@ApiResponse(responseCode = "404", description = "usuário não encontrado")
	})
	@GetMapping("/{id}")
	public UsuarioDTO buscarPorId(@PathVariable Long id){
	    return usuarioService.buscarPorId(id);
	}
	
	@Operation(summary = "Inserir usuários", description = "Cria um novo usuário")
	@ApiResponses({
	@ApiResponse(responseCode = "200", description = "usuário criado com sucesso"),
	@ApiResponse(responseCode = "400", description = "Dados inválidos")
	})
	@PostMapping
	public ResponseEntity<UsuarioDTO> inserir(@RequestBody UsuarioDTO usuario) {
	    UsuarioDTO novo = usuarioService.inserir(usuario);
	    return ResponseEntity.status(201).body(novo);
	}	
	
	@Operation(summary = "Alterar usuário", description = "Altera os dados de um usuário já existente")
	@ApiResponses({
	@ApiResponse(responseCode = "200", description = "usuário alterado"),
	@ApiResponse(responseCode = "404", description = "usuário não encontrado")
	})
	@PutMapping("/{id}")
	public UsuarioDTO alterar(@PathVariable Long id,@RequestBody UsuarioDTO usuario) {
		return usuarioService.alterar(usuario);
	}
	
	@Operation(summary = "Excluir usuário", description = "Excluir os dados de um usuário")
	@ApiResponses({
	@ApiResponse(responseCode = "200", description = "usuário excluido"),
	@ApiResponse(responseCode = "404", description = "usuário não encontrado")
	})
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluir (@PathVariable Long id){
		usuarioService.excluir(id);
		return ResponseEntity.ok().build();
	}
}
