package com.xantx.financeapp.services.impl;

import java.util.List;
import java.util.Optional;

import com.xantx.financeapp.entity.Fondo;
import com.xantx.financeapp.entity.Usuario;
import com.xantx.financeapp.repository.FondoRepository;
import com.xantx.financeapp.services.FondoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FondoServiceImpl implements FondoService {

    @Autowired
    private FondoRepository fondoRepository;

    @Override
    public Fondo save(Fondo entity) throws Exception {
        return fondoRepository.save(entity);
    }

    @Override
    public List<Fondo> findAll() throws Exception {
        return fondoRepository.findAll();
    }

    @Override
    public Optional<Fondo> findById(Long id) throws Exception {
        return fondoRepository.findById(id);
    }

    @Override
    public Fondo update(Long id, Fondo entity) throws Exception {
        Fondo updatedFondo = null;
        if (fondoRepository.findById(id).isPresent()) {
            updatedFondo = fondoRepository.save(entity);
        }
        return updatedFondo;
    }

    @Override
    public void deleteById(Long id) throws Exception {
        fondoRepository.deleteById(id);
    }

    @Override
    public List<Fondo> findByUsuario(Usuario usuario) throws Exception {
        return fondoRepository.findByUsuario(usuario);
    }

    @Override
    public void actualizarTotal(Long fondo_id) throws Exception {
        Optional<Fondo> fondo = fondoRepository.findById(fondo_id);
        if (fondo.isPresent()) {
            Fondo fondoActualizado = fondo.get();
            fondoActualizado.actualizarTotal();
            fondoRepository.save(fondoActualizado);
        }
    }

}
