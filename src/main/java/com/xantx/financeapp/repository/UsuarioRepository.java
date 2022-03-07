package com.xantx.financeapp.repository;

import java.util.UUID;

import com.xantx.financeapp.entity.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
