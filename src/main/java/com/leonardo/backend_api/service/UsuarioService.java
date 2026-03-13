package com.leonardo.backend_api.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.leonardo.backend_api.dto.UsuarioDTO;
import com.leonardo.backend_api.entity.UsuarioEntity;
import com.leonardo.backend_api.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public List<UsuarioDTO> ListarTodos(){
		List<UsuarioEntity> usuarios = usuarioRepository.findAll();
		return usuarios.stream().map(UsuarioDTO::new).toList();
	}
	public UsuarioDTO inserir(UsuarioDTO usuario) {
	    UsuarioEntity usuarioEntity = new UsuarioEntity(usuario);
	    usuarioEntity.setSenha(passwordEncoder.encode(usuario.getSenha()));
	    return new UsuarioDTO(usuarioRepository.save(usuarioEntity));
	}
	public UsuarioDTO alterar(Long id, UsuarioDTO usuario) {
	    UsuarioEntity usuarioEntity = usuarioRepository.findById(id)
	        .orElseThrow(() -> new RuntimeException("Usuario não encontrado: " + id));
	    usuarioEntity = new UsuarioEntity(usuario);
	    usuarioEntity.setSenha(passwordEncoder.encode(usuario.getSenha()));
	    return new UsuarioDTO(usuarioRepository.save(usuarioEntity));
	}
	public void excluir(Long id) {
		UsuarioEntity usuario = usuarioRepository.findById(id).get();
		usuarioRepository.delete(usuario);
	}
	public UsuarioDTO buscarPorId(Long id) {
		return new UsuarioDTO(usuarioRepository.findById(id).get());
	}
	}
