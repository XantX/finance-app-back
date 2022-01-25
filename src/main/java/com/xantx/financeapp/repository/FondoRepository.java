package com.xantx.financeapp.repository;

import com.xantx.financeapp.entity.Fondo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FondoRepository extends JpaRepository<Fondo, Long> {
}
