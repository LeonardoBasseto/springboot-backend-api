package com.leonardo.backend_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leonardo.backend_api.entity.UsuarioEntity;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {
}
