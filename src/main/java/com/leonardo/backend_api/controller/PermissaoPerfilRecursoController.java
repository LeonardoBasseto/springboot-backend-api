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

import com.leonardo.backend_api.dto.PermissaoPerfilRecursoDTO;
import com.leonardo.backend_api.service.PermissaoPerfilRecursoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/permissões")
@Tag(name = "Permissões de Perfil e Recurso", description = "Gerenciamento de permissões entre perfil e recurso")
public class PermissaoPerfilRecursoController {

	@Autowired
	private PermissaoPerfilRecursoService permissaoPerfilRecursoService;
	
	@Operation(summary = "Listar todas as permissões de perfil e recurso", description =  "Retorna uma lista "
			+ "com todas as permissões de perfil e recurso cadastradas")
	@ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
	@GetMapping
	public List<PermissaoPerfilRecursoDTO> ListarTodos(){
		return permissaoPerfilRecursoService.ListarTodos();
	}
	
	@Operation(summary = "Buscar a permissão por ID", description = "Retorna a permissão especificada pelo ID")
	@ApiResponses({
	@ApiResponse(responseCode = "200", description = "ID encontrado com sucesso"),
	@ApiResponse(responseCode = "404", description = "Permissão não encontrada")
	})
	@GetMapping("/{id}")
	public PermissaoPerfilRecursoDTO buscarPorId (@PathVariable Long id) {
		return permissaoPerfilRecursoService.buscarPorId(id);
	}
	
	@Operation(summary = "Inserir permissões", description = "Cria uma nova permissão")
	@ApiResponses({
	@ApiResponse(responseCode = "200", description = "Permissão criada com sucesso"),
	@ApiResponse(responseCode = "400", description = "Dados inválidos")
	})
	@PostMapping
	public ResponseEntity<PermissaoPerfilRecursoDTO> inserir(@RequestBody PermissaoPerfilRecursoDTO permissaoPerfilRecurso) {
		PermissaoPerfilRecursoDTO novo = permissaoPerfilRecursoService.inserir(permissaoPerfilRecurso);
	    return ResponseEntity.status(201).body(novo);
	}
	
	@Operation(summary = "Alterar permissões", description = "Altera uma permissão")
	@ApiResponses({
	@ApiResponse(responseCode = "200", description = "Permissão alterada com sucesso"),
	@ApiResponse(responseCode = "404", description = "Permissão não encontrada")
	})
	@PutMapping("/{id}")
	public PermissaoPerfilRecursoDTO alterar(@PathVariable Long id, @RequestBody PermissaoPerfilRecursoDTO permissaoPerfilRecurso) {
		return permissaoPerfilRecursoService.alterar(id, permissaoPerfilRecurso);
	}
	
	@Operation(summary = "Excluir permissões", description = "Exclui uma permissão")
	@ApiResponses({
	@ApiResponse(responseCode = "200", description = "Permissão excluida com sucesso"),
	@ApiResponse(responseCode = "404", description = "Permissão não encontrada")
	})
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluir(@PathVariable Long id) {
		permissaoPerfilRecursoService.excluir(id);
		return ResponseEntity.ok().build();
	}	
}
