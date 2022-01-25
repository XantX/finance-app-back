package com.xantx.financeapp.repository;

import com.xantx.financeapp.entity.Transaccion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransaccionRepository extends JpaRepository<Transaccion, Long> {

}
