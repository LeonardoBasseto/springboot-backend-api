package com.leonardo.backend_api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leonardo.backend_api.dto.PerfilDTO;
import com.leonardo.backend_api.entity.PerfilEntity;
import com.leonardo.backend_api.repository.PerfilRepository;

@Service
public class PerfilService {

	@Autowired
	private PerfilRepository perfilRepository;
	
	public List<PerfilDTO> listarTodos(){
		List<PerfilEntity> perfis = perfilRepository.findAll();
		return perfis.stream().map(PerfilDTO::new).toList();
	}
	public PerfilDTO inserir(PerfilDTO perfil) {
		PerfilEntity perfilEntity = new PerfilEntity(perfil);
	    return new PerfilDTO(perfilRepository.save(perfilEntity));
	}
	public PerfilDTO alterar(Long id, PerfilDTO perfil) {
	    PerfilEntity perfilEntity = perfilRepository.findById(id)
	        .orElseThrow(() -> new RuntimeException("Perfil não encontrado: " + id));
	    perfilEntity = new PerfilEntity(perfil);
	    return new PerfilDTO(perfilRepository.save(perfilEntity));
	}
	public void excluir(Long id) {
		PerfilEntity perfil = perfilRepository.findById(id).get();
		perfilRepository.delete(perfil);
	}
	public PerfilDTO buscarPorId(Long id) {
		return new PerfilDTO(perfilRepository.findById(id).get());
	}
}
