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


	@RestController
	@RequestMapping(value = "/perfil")
	public class PerfilController {
		
		@Autowired
		private PerfilService perfilService;
		
		@GetMapping
		public List<PerfilDTO> listarTodos(){
		return perfilService.listarTodos();
		}
		@PostMapping
		public void inserir(@RequestBody PerfilDTO perfil) {
			perfilService.inserir(perfil);
		}
		@PutMapping
		public PerfilDTO alterar(@RequestBody PerfilDTO perfil) {
			return perfilService.alterar(perfil);
		}
		@DeleteMapping("/{id}")
		public ResponseEntity<Void> excluir(@PathVariable Long id){
			perfilService.excluir(id);
			return ResponseEntity.ok().build();
		}
	}


