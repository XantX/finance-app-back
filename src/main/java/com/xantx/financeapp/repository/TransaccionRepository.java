package com.xantx.financeapp.repository;

import java.util.List;

import com.xantx.financeapp.entity.Fondo;
import com.xantx.financeapp.entity.Transaccion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransaccionRepository extends JpaRepository<Transaccion, Long> {
    List<Transaccion> findByFondo(Fondo fondo);
}
