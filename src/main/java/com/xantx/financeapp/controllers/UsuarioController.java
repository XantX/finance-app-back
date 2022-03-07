package com.xantx.financeapp.controllers;

import java.util.List;

import javax.validation.Valid;

import com.xantx.financeapp.entity.Usuario;
import com.xantx.financeapp.resources.UsuarioResource;
import com.xantx.financeapp.services.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    private Usuario ResourceToEntity(UsuarioResource resource) {
        Usuario usuario = new Usuario();
        usuario.setName(resource.getName());
        usuario.setPassword(resource.getPassword());
        return usuario;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Usuario>> findAll() throws Exception {
        try {
            List<Usuario> opcionalList = usuarioService.findAll();
            if (!opcionalList.isEmpty()) {
                return new ResponseEntity<List<Usuario>>(opcionalList, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Usuario> save(@Valid @RequestBody UsuarioResource resource) throws Exception {
        try {
            Usuario usuario = ResourceToEntity(resource);
            usuarioService.save(usuario);
            return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
