package com.xantx.financeapp.services.impl;

import java.util.List;
import java.util.Optional;

import com.xantx.financeapp.entity.Banco;
import com.xantx.financeapp.repository.BancoRepository;
import com.xantx.financeapp.services.BancoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BancoServiceImpl implements BancoService {

    @Autowired
    private BancoRepository bancoRepository;

    @Override
    public Banco save(Banco entity) throws Exception {
        return bancoRepository.save(entity);
    }

    @Override
    public List<Banco> findAll() throws Exception {
        return bancoRepository.findAll();
    }

    @Override
    public Optional<Banco> findById(Long id) throws Exception {
        return bancoRepository.findById(id);
    }

    @Override
    public Banco update(Long id, Banco entity) throws Exception {
        Banco updatedBanco = null;
        if (bancoRepository.findById(id).isPresent()) {
            updatedBanco = bancoRepository.save(entity);
        }
        return updatedBanco;
    }

    @Override
    public void deleteById(Long id) throws Exception {
        bancoRepository.deleteById(id);
    }

}
