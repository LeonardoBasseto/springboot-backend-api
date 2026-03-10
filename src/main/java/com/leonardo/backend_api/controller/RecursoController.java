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

import com.leonardo.backend_api.dto.RecursoDTO;
import com.leonardo.backend_api.service.RecursoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;


	@RestController
	@RequestMapping("/recursos")
	@Tag(name = "Recursos", description = "Operações de recursos")
	public class RecursoController {
		
		@Autowired
		private RecursoService recursoService;
		
		@Operation(summary = "Listar todos os recursos", description = "Retorna uma lista com todos os recursos cadastrados")
		@ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
		@GetMapping
		public List<RecursoDTO> ListarTodos(){
		return recursoService.ListarTodos();
		}
		
		@Operation(summary = "Buscar recursos por ID", description = "Retorna o ID do recurso especificado")
		@ApiResponses({
		@ApiResponse(responseCode = "200", description = "Recurso encontrado"),
		@ApiResponse(responseCode = "404", description = "Recurso não encontrado")
		})
		@GetMapping("/{id}")
		public RecursoDTO buscarPorId(@PathVariable Long id){
		    return recursoService.buscarPorId(id);
		}
		
		@Operation(summary = "Inserir recursos", description = "Cria um novo recurso")
		@ApiResponses({
		@ApiResponse(responseCode = "200", description = "Recurso criado com sucesso"),
		@ApiResponse(responseCode = "400", description = "Dados inválidos")
		})
		@PostMapping
		public ResponseEntity<RecursoDTO> inserir(@RequestBody RecursoDTO recurso) {
			RecursoDTO novo = recursoService.inserir(recurso);
		    return ResponseEntity.status(201).body(novo);
		}
		
		@Operation(summary = "Alterar recurso", description = "Altera os dados de um recurso já existente")
		@ApiResponses({
		@ApiResponse(responseCode = "200", description = "Recurso alterado"),
		@ApiResponse(responseCode = "404", description = "Recurso não encontrado")
		})
		@PutMapping("/{id}")
		public RecursoDTO alterar(@PathVariable Long id, @RequestBody RecursoDTO recurso) {
			return recursoService.alterar(recurso);
		}
		
		@Operation(summary = "Excluir recurso", description = "Excluir os dados de um recurso")
		@ApiResponses({
		@ApiResponse(responseCode = "200", description = "Recurso excluido"),
		@ApiResponse(responseCode = "404", description = "Recurso não encontrado")
		})
		@DeleteMapping("/{id}")
		public ResponseEntity<Void> excluir(@PathVariable Long id){
			recursoService.excluir(id);
			return ResponseEntity.ok().build();
		}
	}


