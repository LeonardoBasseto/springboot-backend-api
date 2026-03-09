package com.leonardo.backend_api.dto;

import org.springframework.beans.BeanUtils;

import com.leonardo.backend_api.entity.PerfilEntity;

public class PerfilDTO {
	public PerfilDTO () {}
	
	private Long id;
	private String descricao;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public PerfilDTO(PerfilEntity perfil) {
		BeanUtils.copyProperties(perfil, this);
	}
}
