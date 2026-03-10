package com.leonardo.backend_api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leonardo.backend_api.dto.PerfilUsuarioDTO;
import com.leonardo.backend_api.service.PerfilUsuarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/perfil-usuario")
@CrossOrigin
@Tag(name = "Perfil Usuário", description = "Relacionamento entre perfil e usuário")
public class PerfilUsuarioController {

	@Autowired
	private PerfilUsuarioService perfilUsuarioService;
	
	@Operation(summary = "Listar todos os vínculos", description = "Retorna uma lista com todos os vínculos cadastrados")
	@ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
	@GetMapping
	public List<PerfilUsuarioDTO> listarTodos(){
		return perfilUsuarioService.listarTodos();
	}
	
	@Operation(summary = "Buscar vínculo por ID", description = "Retorna o ID do vínculo especificado")
	@ApiResponses({
	@ApiResponse(responseCode = "200", description = "Vínculo encontrado"),
	@ApiResponse(responseCode = "404", description = "Vínculo não encontrado")
	})
	@GetMapping("/{id}")
	public PerfilUsuarioDTO buscarPorId(@PathVariable Long id){
	    return perfilUsuarioService.buscarPorId(id);
	}
	
	@Operation(summary = "Inserir vínculo", description = "Cria um novo Vínculo")
	@ApiResponses({
	@ApiResponse(responseCode = "200", description = "Vínculo criado com sucesso"),
	@ApiResponse(responseCode = "400", description = "Dados inválidos")
	})
	@PostMapping
	public ResponseEntity<PerfilUsuarioDTO> inserir(@RequestBody PerfilUsuarioDTO perfilUsuario) {
		PerfilUsuarioDTO novo = perfilUsuarioService.inserir(perfilUsuario);
	    return ResponseEntity.status(201).body(novo);
	}
	
	@Operation(summary = "Alterar Vínculo", description = "Altera os dados de um Vínculo já existente")
	@ApiResponses({
	@ApiResponse(responseCode = "200", description = "Vínculo alterado"),
	@ApiResponse(responseCode = "404", description = "Vínculo não encontrado")
	})
	@PutMapping("/{id}")
	public PerfilUsuarioDTO alterar(@PathVariable Long id, @RequestBody PerfilUsuarioDTO perfilUsuario) {
		return 	perfilUsuarioService.alterar(perfilUsuario);
	}
	
	@Operation(summary = "Excluir Vínculo", description = "Excluir os dados de um Vínculo")
	@ApiResponses({
	@ApiResponse(responseCode = "200", description = "Vínculo excluido"),
	@ApiResponse(responseCode = "404", description = "Vínculo não encontrado")
	})
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluir(@PathVariable Long id) {
		perfilUsuarioService.excluir(id);
		return ResponseEntity.ok().build();
	}
}
