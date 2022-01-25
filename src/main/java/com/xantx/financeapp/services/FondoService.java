package com.xantx.financeapp.services;

import com.xantx.financeapp.entity.Fondo;
import com.xantx.financeapp.services.common.CrudService;

import org.springframework.stereotype.Service;

@Service
public interface FondoService extends CrudService<Fondo, Long> {
}
