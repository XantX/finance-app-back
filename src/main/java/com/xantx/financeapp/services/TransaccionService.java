package com.xantx.financeapp.services;

import java.util.List;

import com.xantx.financeapp.entity.Fondo;
import com.xantx.financeapp.entity.Transaccion;
import com.xantx.financeapp.services.common.CrudService;

public interface TransaccionService extends CrudService<Transaccion, Long> {
    List<Transaccion> findByFondo(Fondo fondo);
}
