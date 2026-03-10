package com.leonardo.backend_api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leonardo.backend_api.dto.RecursoDTO;
import com.leonardo.backend_api.entity.RecursoEntity;
import com.leonardo.backend_api.repository.RecursoRepository;

@Service
public class RecursoService {

	@Autowired
	public RecursoRepository recursoRepository;
	
	public List<RecursoDTO> ListarTodos(){
		List<RecursoEntity> recurso = recursoRepository.findAll();
		return recurso.stream().map(RecursoDTO::new).toList();
	}
	public RecursoDTO inserir(RecursoDTO recurso) {
		RecursoEntity recursoEntity = new RecursoEntity(recurso);
	    return new RecursoDTO(recursoRepository.save(recursoEntity));
	}
	public RecursoDTO alterar(RecursoDTO Recurso) {
		RecursoEntity RecursoEntity = new RecursoEntity(Recurso);
		return new RecursoDTO(recursoRepository.save(RecursoEntity));
	}
	public void excluir(Long id) {
		RecursoEntity Recurso = recursoRepository.findById(id).get();
		recursoRepository.delete(Recurso);
	}
	public RecursoDTO buscarPorId(Long id) {
		return new RecursoDTO(recursoRepository.findById(id).get());
	}
	
}
