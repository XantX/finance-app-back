package com.xantx.financeapp.services;

import com.xantx.financeapp.entity.Banco;
import com.xantx.financeapp.services.common.CrudService;

import org.springframework.stereotype.Service;

@Service
public interface BancoService extends CrudService<Banco, Long> {
}
