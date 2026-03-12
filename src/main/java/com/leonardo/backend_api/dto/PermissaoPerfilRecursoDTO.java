package com.leonardo.backend_api.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.leonardo.backend_api.entity.PermissaoPerfilRecursoEntity;

public class PermissaoPerfilRecursoDTO {
	public PermissaoPerfilRecursoDTO(){}

	@JsonProperty(access = Access.READ_ONLY)
	private Long id;

	@JsonIgnore
    private PerfilDTO perfil;

	@JsonIgnore
    private RecursoDTO recurso;
    
	private Long perfilId;
	private Long recursoId;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public PerfilDTO getPerfil() {
		return perfil;
	}

	public void setPerfil(PerfilDTO perfil) {
		this.perfil = perfil;
	}

	public RecursoDTO getRecurso() {
		return recurso;
	}

	public void setRecurso(RecursoDTO recurso) {
		this.recurso = recurso;
	}

	public Long getPerfilId() {
		return perfilId;
	}

	public void setPerfilId(Long perfilId) {
		this.perfilId = perfilId;
	}

	public Long getRecursoId() {
		return recursoId;
	}

	public void setRecursoId(Long recursoId) {
		this.recursoId = recursoId;
	}

	public PermissaoPerfilRecursoDTO(PermissaoPerfilRecursoEntity permissaoPerfilRecurso) {
		this.id = permissaoPerfilRecurso.getId();
		this.perfilId = permissaoPerfilRecurso.getPerfil() != null ? permissaoPerfilRecurso.getPerfil().getId() : null;
		this.recursoId = permissaoPerfilRecurso.getRecurso() != null ? permissaoPerfilRecurso.getRecurso().getId() : null;
		this.perfil = permissaoPerfilRecurso.getPerfil() != null ? new PerfilDTO(permissaoPerfilRecurso.getPerfil()) : null;
        this.recurso = permissaoPerfilRecurso.getRecurso() != null ? new RecursoDTO(permissaoPerfilRecurso.getRecurso()) : null;
	}
}