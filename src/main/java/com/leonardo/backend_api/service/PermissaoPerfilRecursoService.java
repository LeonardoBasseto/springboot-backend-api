package com.leonardo.backend_api.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.leonardo.backend_api.dto.PermissaoPerfilRecursoDTO;
import com.leonardo.backend_api.entity.PerfilEntity;
import com.leonardo.backend_api.entity.PermissaoPerfilRecursoEntity;
import com.leonardo.backend_api.entity.RecursoEntity;
import com.leonardo.backend_api.repository.PerfilRepository;
import com.leonardo.backend_api.repository.PermissaoPerfilRecursoRepository;
import com.leonardo.backend_api.repository.RecursoRepository;

@Service
public class PermissaoPerfilRecursoService {

    @Autowired
    private PermissaoPerfilRecursoRepository permissaoPerfilRecursoRepository;

    @Autowired
    private RecursoRepository recursoRepository;

    @Autowired
    private PerfilRepository perfilRepository;

    public List<PermissaoPerfilRecursoDTO> listarTodos() {
        return permissaoPerfilRecursoRepository.findAll()
                .stream()
                .map(PermissaoPerfilRecursoDTO::new)
                .toList();
    }

    public PermissaoPerfilRecursoDTO inserir(PermissaoPerfilRecursoDTO dto) {
        PerfilEntity perfil = perfilRepository.findById(dto.getPerfilId())
                .orElseThrow(() -> new RuntimeException("Perfil não encontrado: " + dto.getPerfilId()));
        RecursoEntity recurso = recursoRepository.findById(dto.getRecursoId())
                .orElseThrow(() -> new RuntimeException("Recurso não encontrado: " + dto.getRecursoId()));
        PermissaoPerfilRecursoEntity entity = new PermissaoPerfilRecursoEntity(dto, perfil, recurso);
        return new PermissaoPerfilRecursoDTO(permissaoPerfilRecursoRepository.save(entity));
    }

    public void excluir(Long id) {
        PermissaoPerfilRecursoEntity entity = permissaoPerfilRecursoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("PermissaoPerfilRecurso não encontrado: " + id));
        permissaoPerfilRecursoRepository.delete(entity);
    }

    public PermissaoPerfilRecursoDTO buscarPorId(Long id) {
        return new PermissaoPerfilRecursoDTO(permissaoPerfilRecursoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("PermissaoPerfilRecurso não encontrado: " + id)));
    }
}