package com.xantx.financeapp.services.impl;

import java.util.List;
import java.util.Optional;

import com.xantx.financeapp.entity.Transaccion;
import com.xantx.financeapp.repository.TransaccionRepository;
import com.xantx.financeapp.services.TransaccionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransaccionServiceImpl implements TransaccionService {

    @Autowired
    private TransaccionRepository transaccionRepository;

    @Override
    public Transaccion save(Transaccion entity) throws Exception {
        return transaccionRepository.save(entity);
    }

    @Override
    public List<Transaccion> findAll() throws Exception {
        return transaccionRepository.findAll();
    }

    @Override
    public Optional<Transaccion> findById(Long id) throws Exception {
        return transaccionRepository.findById(id);
    }

    @Override
    public Transaccion update(Long id, Transaccion entity) throws Exception {
        Transaccion updatedTransaccion = null;
        if (transaccionRepository.findById(id).isPresent()) {
            updatedTransaccion = transaccionRepository.save(entity);
        }
        return updatedTransaccion;
    }

    @Override
    public void deleteById(Long id) throws Exception {
        transaccionRepository.deleteById(id);
    }

}
