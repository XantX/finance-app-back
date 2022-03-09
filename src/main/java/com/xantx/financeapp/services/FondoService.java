package com.xantx.financeapp.services;

import java.util.List;

import com.xantx.financeapp.entity.Fondo;
import com.xantx.financeapp.entity.Usuario;
import com.xantx.financeapp.services.common.CrudService;

public interface FondoService extends CrudService<Fondo, Long> {
    List<Fondo> findByUsuario(Usuario usuario);
}
