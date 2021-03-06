package com.xantx.financeapp.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.xantx.financeapp.entity.Usuario;
import com.xantx.financeapp.resources.UsuarioResource;
import com.xantx.financeapp.services.UsuarioService;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @DeleteMapping(path = "/{usuario_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> delete(@PathVariable Long usuario_id) {
        try {
            Optional<Usuario> usuario = usuarioService.findById(usuario_id);
            if (!usuario.isPresent()) {
                return new ResponseEntity<>("No se encontro el usuario con id: " + usuario_id, HttpStatus.NOT_FOUND);
            }
            usuarioService.deleteById(usuario_id);
            return new ResponseEntity<Usuario>(usuario.get(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
