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
    private RecursoRepository recursoRepository;

    public List<RecursoDTO> listarTodos() {
        return recursoRepository.findAll()
                .stream()
                .map(RecursoDTO::new)
                .toList();
    }

    public RecursoDTO inserir(RecursoDTO recurso) {
        RecursoEntity recursoEntity = new RecursoEntity(recurso);
        return new RecursoDTO(recursoRepository.save(recursoEntity));
    }

    public RecursoDTO alterar(Long id, RecursoDTO recurso) {
        RecursoEntity entity = recursoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Recurso não encontrado: " + id));
        entity.setNome(recurso.getNome());
        entity.setChave(recurso.getChave());
        return new RecursoDTO(recursoRepository.save(entity));
    }

    public void excluir(Long id) {
        RecursoEntity recurso = recursoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Recurso não encontrado: " + id));
        recursoRepository.delete(recurso);
    }

    public RecursoDTO buscarPorId(Long id) {
        return new RecursoDTO(recursoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Recurso não encontrado: " + id)));
    }
}