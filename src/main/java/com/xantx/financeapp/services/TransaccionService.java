package com.xantx.financeapp.services;

import com.xantx.financeapp.entity.Transaccion;
import com.xantx.financeapp.services.common.CrudService;

import org.springframework.stereotype.Service;

@Service
public interface TransaccionService extends CrudService<Transaccion, Long> {
}
