package com.leonardo.backend_api.entity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.BeanUtils;

import com.leonardo.backend_api.dto.UsuarioDTO;
import com.leonardo.backend_api.entity.enums.TipoSituacaoUsuario;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_usuario")
public class UsuarioEntity {
	public UsuarioEntity (){}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String nome;
	@Column(nullable = false, unique = true)
	private String login;
	@Column(nullable = false)
	private String senha;
	@Column(nullable = false)
	private String email;
	
	@Column(name = "token_confirmacao")
	private String tokenConfirmacao;
	@Column(name = "token_expiracao")
	private LocalDateTime tokenExpiracao;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private TipoSituacaoUsuario situacao;
	
	@OneToMany(mappedBy = "usuario")
	private List<PerfilUsuarioEntity> perfis;

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
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public UsuarioEntity(UsuarioDTO usuario) {
		BeanUtils.copyProperties(usuario, this);
	}
	
	public TipoSituacaoUsuario getSituacao() {
		return situacao;
	}
	public void setSituacao(TipoSituacaoUsuario situacao) {
		this.situacao = situacao;
	}
	public String getTokenConfirmacao() {
		return tokenConfirmacao;
	}
	public void setTokenConfirmacao(String tokenConfirmacao) {
		this.tokenConfirmacao = tokenConfirmacao;
	}
	public LocalDateTime getTokenExpiracao() {
		return tokenExpiracao;
	}
	public void setTokenExpiracao(LocalDateTime tokenExpiracao) {
		this.tokenExpiracao = tokenExpiracao;
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
		UsuarioEntity other = (UsuarioEntity) obj;
		return Objects.equals(id, other.id);
	}
	
}
