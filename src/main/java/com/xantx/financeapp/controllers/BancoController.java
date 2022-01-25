package com.xantx.financeapp.controllers;

import java.util.List;

import javax.validation.Valid;

import com.xantx.financeapp.entity.Banco;
import com.xantx.financeapp.resources.BancoResource;
import com.xantx.financeapp.services.BancoService;

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
@RequestMapping("api/banco")
public class BancoController {
    @Autowired
    private BancoService bancoService;

    private Banco ResourceToEntity(BancoResource resource) {
        Banco banco = new Banco();
        banco.setLink(resource.getLink());
        banco.setNombre(resource.getNombre());
        return banco;
    }

    // TODO: Ver forma de modificar la lista para que retorne una lista de resources
    // del banco
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Banco> getAll() throws Exception {
        return bancoService.findAll();
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Banco> save(@Valid @RequestBody BancoResource bancoResource) {
        try {
            Banco banco = ResourceToEntity(bancoResource);
            return new ResponseEntity<Banco>(bancoService.save(banco), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    // TODO: implementacion del resto de metodos crud
}
