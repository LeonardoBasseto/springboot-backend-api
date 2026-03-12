package com.leonardo.backend_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leonardo.backend_api.dto.PerfilUsuarioDTO;
import com.leonardo.backend_api.entity.PerfilEntity;
import com.leonardo.backend_api.entity.PerfilUsuarioEntity;
import com.leonardo.backend_api.entity.UsuarioEntity;
import com.leonardo.backend_api.repository.PerfilRepository;
import com.leonardo.backend_api.repository.PerfilUsuarioRepository;
import com.leonardo.backend_api.repository.UsuarioRepository;
import java.util.List;


@Service
public class PerfilUsuarioService {
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private PerfilRepository perfilRepository;
	
	@Autowired
	private PerfilUsuarioRepository perfilUsuarioRepository;

	
	public List<PerfilUsuarioDTO> listarTodos(){
	    List<PerfilUsuarioEntity> perfilUsuario = perfilUsuarioRepository.findAll();
	    return perfilUsuario.stream().map(PerfilUsuarioDTO::new).toList();
	}
	public PerfilUsuarioDTO inserir(PerfilUsuarioDTO dto) {
		UsuarioEntity usuario = usuarioRepository.findById(dto.getUsuarioId())
			.orElseThrow(() -> new RuntimeException("Usuario não encontrado: " + dto.getUsuarioId()));
		PerfilEntity perfil = perfilRepository.findById(dto.getPerfilId())
			.orElseThrow(() -> new RuntimeException("Perfil não encontrado: " + dto.getPerfilId()));
		PerfilUsuarioEntity entity = new PerfilUsuarioEntity(dto, usuario, perfil);
		return new PerfilUsuarioDTO(perfilUsuarioRepository.save(entity));
	}

	public PerfilUsuarioDTO alterar(Long id, PerfilUsuarioDTO dto) {
		perfilUsuarioRepository.findById(id)
			.orElseThrow(() -> new RuntimeException("PerfilUsuario não encontrado: " + id));
		UsuarioEntity usuario = usuarioRepository.findById(dto.getUsuarioId())
			.orElseThrow(() -> new RuntimeException("Usuario não encontrado: " + dto.getUsuarioId()));
		PerfilEntity perfil = perfilRepository.findById(dto.getPerfilId())
			.orElseThrow(() -> new RuntimeException("Perfil não encontrado: " + dto.getPerfilId()));
		PerfilUsuarioEntity entity = new PerfilUsuarioEntity(dto, usuario, perfil);
		entity.setId(id);
		return new PerfilUsuarioDTO(perfilUsuarioRepository.save(entity));
	}
	public void excluir(Long id) {
		PerfilUsuarioEntity recurso = perfilUsuarioRepository.findById(id).get();
		perfilUsuarioRepository.delete(recurso);
	}
	public PerfilUsuarioDTO buscarPorId(Long id) {
		return new PerfilUsuarioDTO(perfilUsuarioRepository.findById(id).get());
	}
}
