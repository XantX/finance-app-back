package com.xantx.financeapp.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.xantx.financeapp.entity.Banco;
import com.xantx.financeapp.entity.Fondo;
import com.xantx.financeapp.entity.Usuario;
import com.xantx.financeapp.resources.FondoResource;
import com.xantx.financeapp.services.BancoService;
import com.xantx.financeapp.services.FondoService;
import com.xantx.financeapp.services.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/fondos")
public class FondoController {
    @Autowired
    private FondoService fondoService;

    @Autowired
    private BancoService bancoService;

    @Autowired
    private UsuarioService usuarioService;

    private Fondo ResourceToEntity(FondoResource resource) {
        Fondo fondo = new Fondo();
        fondo.setDescription(resource.getDescripcion());
        return fondo;
    }

    @PostMapping(path = "/usuarios/{usuario_id}/bancos/{banco_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> save(@Valid @RequestBody FondoResource resources,
            @PathVariable Long banco_id,
            @PathVariable Long usuario_id) {
        try {
            Fondo fondo = ResourceToEntity(resources);
            Optional<Usuario> usuario = usuarioService.findById(usuario_id);
            if (!usuario.isPresent()) {
                return new ResponseEntity<>("No encontrado el usuario de id " + usuario_id.toString(),
                        HttpStatus.NOT_FOUND);
            }
            Optional<Banco> banco = bancoService.findById(banco_id);
            if (!banco.isPresent()) {
                return new ResponseEntity<>("No encontrado banco de id " + banco_id, HttpStatus.NOT_FOUND);
            }
            fondo.setBanco(banco.get());
            fondo.setUsuario(usuario.get());
            fondoService.save(fondo);
            return new ResponseEntity<Fondo>(fondo, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAll() {
        try {
            List<Fondo> fondos = fondoService.findAll();
            if (fondos.size() == 0) {
                return new ResponseEntity<List<Fondo>>(fondos, HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<List<Fondo>>(fondos, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
