package com.leonardo.backend_api.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.leonardo.backend_api.entity.PerfilUsuarioEntity;

public class PerfilUsuarioDTO {
	public PerfilUsuarioDTO(){}

	@JsonProperty(access = Access.READ_ONLY)
	private Long id;

	private Long usuarioId;
	private Long perfilId;

	@JsonIgnore
	private UsuarioDTO usuario;

	@JsonIgnore
	private PerfilDTO perfil;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getUsuarioId() {
		return usuarioId;
	}
	public void setUsuarioId(Long usuarioId) {
		this.usuarioId = usuarioId;
	}
	public Long getPerfilId() {
		return perfilId;
	}
	public void setPerfilId(Long perfilId) {
		this.perfilId = perfilId;
	}
	public UsuarioDTO getUsuario() {
		return usuario;
	}
	public void setUsuario(UsuarioDTO usuario) {
		this.usuario = usuario;
	}
	public PerfilDTO getPerfil() {
		return perfil;
	}
	public void setPerfil(PerfilDTO perfil) {
		this.perfil = perfil;
	}
	public PerfilUsuarioDTO(PerfilUsuarioEntity perfilUsuario) {
		this.id = perfilUsuario.getId();
		this.usuarioId = perfilUsuario.getUsuario() != null ? perfilUsuario.getUsuario().getId() : null;
		this.perfilId = perfilUsuario.getPerfil() != null ? perfilUsuario.getPerfil().getId() : null;
		this.usuario = perfilUsuario.getUsuario() != null ? new UsuarioDTO(perfilUsuario.getUsuario()) : null;
		this.perfil = perfilUsuario.getPerfil() != null ? new PerfilDTO(perfilUsuario.getPerfil()) : null;
	}
}