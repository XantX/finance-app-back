package com.xantx.financeapp.repository;

import java.util.List;

import com.xantx.financeapp.entity.Fondo;
import com.xantx.financeapp.entity.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FondoRepository extends JpaRepository<Fondo, Long> {
    List<Fondo> findByUsuario(Usuario usuario);
}
