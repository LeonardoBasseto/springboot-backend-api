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

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping
	public List<UsuarioDTO> listarTodos(){
	return usuarioService.ListarTodos();
	}
	@GetMapping("/{id}")
	public UsuarioDTO buscarPorId(@PathVariable Long id){
	    return usuarioService.buscarPorId(id);
	}
	@PostMapping
	public void inserir(@RequestBody UsuarioDTO usuario) {
		usuarioService.inserir(usuario);
	}
	@PutMapping(value = "/{id}")
	public UsuarioDTO alterar(@PathVariable Long id,@RequestBody UsuarioDTO usuario) {
		return usuarioService.alterar(usuario);
	}
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> excluir (@PathVariable Long id){
		usuarioService.excluir(id);
		return ResponseEntity.ok().build();
	}
}
