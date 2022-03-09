package com.xantx.financeapp.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.xantx.financeapp.entity.Fondo;
import com.xantx.financeapp.entity.Transaccion;
import com.xantx.financeapp.resources.TransaccionResource;
import com.xantx.financeapp.services.FondoService;
import com.xantx.financeapp.services.TransaccionService;

import org.apache.catalina.connector.Response;
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
@RequestMapping("api")
public class TransaccionController {
    @Autowired
    private TransaccionService transaccionService;
    @Autowired
    private FondoService fondoService;

    private Transaccion ResourceToEntity(TransaccionResource resource) {
        Transaccion transaccion = new Transaccion();
        transaccion.setCantidad(resource.getCantidad());
        transaccion.setDescripcion(resource.getDescripcion());
        transaccion.setTipo(resource.getTipo());
        return transaccion;
    }

    @PostMapping(path = "/fondos/{fondo_id}/transacciones", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> save(@Valid @RequestBody TransaccionResource resource,
            @PathVariable Long fondo_id) {
        try {
            Optional<Fondo> fondo = fondoService.findById(fondo_id);
            if (!fondo.isPresent()) {
                return new ResponseEntity<>("Fondo de id: " + fondo_id.toString(), HttpStatus.NOT_FOUND);
            }
            Transaccion transaccion = ResourceToEntity(resource);
            transaccion.setFondo(fondo.get());
            return new ResponseEntity<Transaccion>(transaccionService.save(transaccion), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/fondos/{fondo_id}/transacciones", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> listAll(@PathVariable Long fondo_id) {
        try {
            Optional<Fondo> fondo = fondoService.findById(fondo_id);
            if (!fondo.isPresent()) {
                return new ResponseEntity<>("Fondo de id: " + fondo_id.toString(), HttpStatus.NOT_FOUND);
            }
            List<Transaccion> transaciones = transaccionService.findByFondo(fondo.get());
            return new ResponseEntity<List<Transaccion>>(transaciones, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
