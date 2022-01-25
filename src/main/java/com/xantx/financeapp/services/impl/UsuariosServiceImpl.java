package com.xantx.financeapp.services.impl;

import java.util.List;
import java.util.Optional;

import com.xantx.financeapp.entity.Usuario;
import com.xantx.financeapp.repository.UsuarioRepository;
import com.xantx.financeapp.services.common.CrudService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuariosServiceImpl implements CrudService<Usuario, String> {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public Usuario save(Usuario entity) throws Exception {
        return usuarioRepository.save(entity);
    }

    @Override
    public List<Usuario> findAll() throws Exception {
        return usuarioRepository.findAll();
    }

    @Override
    public Optional<Usuario> findById(String id) throws Exception {
        return usuarioRepository.findById(id);
    }

    @Override
    public Usuario update(String id, Usuario entity) throws Exception {
        Usuario updatedUsuario = null;
        if (usuarioRepository.findById(id).isPresent()) {
            updatedUsuario = usuarioRepository.save(entity);
        }
        return updatedUsuario;
    }

    @Override
    public void deleteById(String id) throws Exception {
        usuarioRepository.deleteById(id);
        ;
    }

}