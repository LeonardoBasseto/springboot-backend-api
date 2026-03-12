
package com.leonardo.backend_api.entity;

import java.util.Objects; 

import com.leonardo.backend_api.dto.PermissaoPerfilRecursoDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_permissao_perfil_recurso")
public class PermissaoPerfilRecursoEntity{
	public PermissaoPerfilRecursoEntity (){}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "ID_Perfil")
	private PerfilEntity perfil;
	@ManyToOne
	@JoinColumn(name = "ID_Recurso")
	private RecursoEntity recurso;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public PerfilEntity getPerfil() {
		return perfil;
	}
	public void setPerfil(PerfilEntity perfil) {
		this.perfil = perfil;
	}
	public RecursoEntity getRecurso() {
		return recurso;
	}
	public void setRecurso(RecursoEntity recurso) {
		this.recurso = recurso;
	}
	public PermissaoPerfilRecursoEntity(PermissaoPerfilRecursoDTO dto, PerfilEntity perfil, RecursoEntity recurso) {
	    this.id = dto.getId();
	    this.perfil = perfil;
	    this.recurso = recurso;
	}	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PermissaoPerfilRecursoEntity other = (PermissaoPerfilRecursoEntity) obj;
		return Objects.equals(id, other.id);
	}
}
