package com.xantx.financeapp.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.xantx.financeapp.entity.Banco;
import com.xantx.financeapp.resources.BancoResource;
import com.xantx.financeapp.services.BancoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/bancos")
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

    @DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            if (!bancoService.findById(id).isPresent()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            bancoService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Banco> update(@PathVariable Long id, @Valid @RequestBody BancoResource resource) {
        try {
            Optional<Banco> banco = bancoService.findById(id);
            if (!banco.isPresent()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            Banco updatedBanco = banco.get();
            updatedBanco.setLink(resource.getLink());
            updatedBanco.setNombre(resource.getNombre());
            return new ResponseEntity<Banco>(bancoService.update(id, updatedBanco), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
