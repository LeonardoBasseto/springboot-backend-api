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

import com.leonardo.backend_api.dto.PerfilDTO;
import com.leonardo.backend_api.service.PerfilService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;


	@RestController
	@RequestMapping("/perfis")
	@Tag(name = "Perfis", description = "Operações de perfis")
	public class PerfilController {
		
		@Autowired
		private PerfilService perfilService;
		
		@Operation(summary = "Listar todos os perfis", description = "Retorna uma lista com todos os perfis cadastrados")
		@ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
		@GetMapping
		public List<PerfilDTO> listarTodos(){
		return perfilService.listarTodos();
		}
		
		@Operation(summary = "Buscar perfis por ID", description = "Retorna o ID do perfil especificado")
		@ApiResponses({
		@ApiResponse(responseCode = "200", description = "Perfil encontrado"),
		@ApiResponse(responseCode = "404", description = "Perfil não encontrado")
		})
		@GetMapping("/{id}")
		public PerfilDTO buscarPorId(@PathVariable Long id){
		    return perfilService.buscarPorId(id);
		}
		
		@Operation(summary = "Inserir perfis", description = "Cria um novo perfil")
		@ApiResponses({
		@ApiResponse(responseCode = "200", description = "Perfil criado com sucesso"),
		@ApiResponse(responseCode = "400", description = "Dados inválidos")
		})
		@PostMapping
		public ResponseEntity<PerfilDTO> inserir(@RequestBody PerfilDTO perfil) {
			PerfilDTO novo = perfilService.inserir(perfil);
		    return ResponseEntity.status(201).body(novo);
		}
		
		@Operation(summary = "Alterar perfil", description = "Altera os dados de um perfil já existente")
		@ApiResponses({
		@ApiResponse(responseCode = "200", description = "perfil alterado"),
		@ApiResponse(responseCode = "404", description = "perfil não encontrado")
		})
		@PutMapping("/{id}")
		public PerfilDTO alterar(@PathVariable Long id, @RequestBody PerfilDTO perfil) {
			return perfilService.alterar(perfil);
		}
		
		@Operation(summary = "Excluir perfil", description = "Excluir os dados de um perfil")
		@ApiResponses({
		@ApiResponse(responseCode = "200", description = "perfil excluido"),
		@ApiResponse(responseCode = "404", description = "perfil não encontrado")
		})
		@DeleteMapping("/{id}")
		public ResponseEntity<Void> excluir(@PathVariable Long id){
			perfilService.excluir(id);
			return ResponseEntity.ok().build();
		}
	}


