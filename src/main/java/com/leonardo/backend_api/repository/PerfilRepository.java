package com.leonardo.backend_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leonardo.backend_api.entity.PerfilEntity;

public interface PerfilRepository extends JpaRepository<PerfilEntity, Long> {
	
}