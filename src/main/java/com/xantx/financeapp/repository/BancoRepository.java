package com.xantx.financeapp.repository;

import com.xantx.financeapp.entity.Banco;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BancoRepository extends JpaRepository<Banco, Long> {
}
