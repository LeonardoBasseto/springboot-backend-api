package com.leonardo.backend_api.entity;

import java.util.Objects;

import org.springframework.beans.BeanUtils;

import com.leonardo.backend_api.dto.RecursoDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_recurso")
public class RecursoEntity {
	public RecursoEntity (){}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String chave;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getChave() {
		return chave;
	}
	public void setChave(String chave) {
		this.chave = chave;
	}
	
	public RecursoEntity(RecursoDTO recurso) {
		BeanUtils.copyProperties(recurso, this);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RecursoEntity other = (RecursoEntity) obj;
		return Objects.equals(id, other.id);
	}
}
