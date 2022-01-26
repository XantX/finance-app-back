package com.xantx.financeapp.services;

import java.util.UUID;

import com.xantx.financeapp.entity.Usuario;
import com.xantx.financeapp.services.common.CrudService;

import org.springframework.stereotype.Service;

@Service
public interface UsuarioService extends CrudService<Usuario, UUID> {
}
