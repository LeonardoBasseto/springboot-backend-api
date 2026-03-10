package com.leonardo.backend_api.entity;

import org.springframework.beans.BeanUtils;

import com.leonardo.backend_api.dto.PerfilUsuarioDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_usuario_perfil")
public class PerfilUsuarioEntity {
	public PerfilUsuarioEntity (){}


	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "ID_Usuario")
	private UsuarioEntity usuario;
	
	@ManyToOne
	@JoinColumn(name = "ID_Perfil")
	private PerfilEntity perfil;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public UsuarioEntity getUsuario() {
		return usuario;
	}
	public void setUsuario(UsuarioEntity usuario) {
		this.usuario = usuario;
	}
	public PerfilEntity getPerfil() {
		return perfil;
	}
	public void setPerfil(PerfilEntity perfil) {
		this.perfil = perfil;
	}
		
	public PerfilUsuarioEntity(PerfilUsuarioDTO perfilUsuario) {
		BeanUtils.copyProperties(perfilUsuario, this);		
		if(perfilUsuario != null && perfilUsuario.getUsuario() != null) {
			this.usuario = new UsuarioEntity(perfilUsuario.getUsuario());
		}
		if(perfilUsuario != null && perfilUsuario.getPerfil() != null) {
			this.perfil = new PerfilEntity(perfilUsuario.getPerfil());
		}
	}
}
