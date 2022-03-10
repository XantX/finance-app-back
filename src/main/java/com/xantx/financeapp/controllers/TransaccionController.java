package com.xantx.financeapp.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.xantx.financeapp.entity.Fondo;
import com.xantx.financeapp.entity.Transaccion;
import com.xantx.financeapp.entity.Usuario;
import com.xantx.financeapp.resources.TransaccionResource;
import com.xantx.financeapp.services.FondoService;
import com.xantx.financeapp.services.TransaccionService;
import com.xantx.financeapp.services.UsuarioService;
import com.xantx.financeapp.services.impl.times.TiempoPeru;

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
@RequestMapping("api")
public class TransaccionController {
    @Autowired
    private TransaccionService transaccionService;
    @Autowired
    private FondoService fondoService;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private TiempoPeru tiempoPeru;

    private Transaccion ResourceToEntity(TransaccionResource resource) throws Exception {
        Transaccion transaccion = new Transaccion();
        transaccion.setCantidad(resource.getCantidad());
        transaccion.setDescripcion(resource.getDescripcion());
        transaccion.setTipo(resource.getTipo());
        transaccion.setFecha(tiempoPeru.obtenerTiempoActual());
        return transaccion;
    }

    @PostMapping(path = "/fondos/{fondo_id}/transacciones", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> save(@Valid @RequestBody TransaccionResource resource,
            @PathVariable Long fondo_id) {
        try {
            Optional<Fondo> fondo = fondoService.findById(fondo_id);
            if (!fondo.isPresent()) {
                return new ResponseEntity<>("No se encontro el fondo de id: " + fondo_id.toString(),
                        HttpStatus.NOT_FOUND);
            }
            Transaccion transaccion = ResourceToEntity(resource);
            Fondo fondoActualizado = fondo.get();
            fondoActualizado.agrearAlTotal(transaccion);
            transaccion.setFondo(fondoActualizado);
            Fondo fondoActu = fondoService.save(fondoActualizado);
            Usuario usuario = fondoActu.getUsuario();
            usuario.updateTotal();
            usuarioService.save(usuario);
            Transaccion nuevaTransaccion = transaccionService.save(transaccion);
            return new ResponseEntity<Transaccion>(nuevaTransaccion, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/fondos/{fondo_id}/transacciones", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> listAll(@PathVariable Long fondo_id) {
        try {
            Optional<Fondo> fondo = fondoService.findById(fondo_id);
            if (!fondo.isPresent()) {
                return new ResponseEntity<>("No se encontro el fondo de id: " + fondo_id.toString(),
                        HttpStatus.NOT_FOUND);
            }
            List<Transaccion> transaciones = transaccionService.findByFondo(fondo.get());
            return new ResponseEntity<List<Transaccion>>(transaciones, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(path = "/fondos/{fondo_id}/transacciones/{transaccion_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteTransaccion(@PathVariable Long fondo_id, @PathVariable Long transaccion_id) {
        try {
            Optional<Fondo> fondo = fondoService.findById(fondo_id);
            if (!fondo.isPresent()) {
                return new ResponseEntity<>("No se encontro el fondo de id: " + fondo_id.toString(),
                        HttpStatus.NOT_FOUND);
            }
            Optional<Transaccion> transaccion = transaccionService.findById(transaccion_id);
            if (!transaccion.isPresent()) {
                return new ResponseEntity<>("No se encontro la transaccion de id: " + transaccion_id.toString(),
                        HttpStatus.NOT_FOUND);
            }
            transaccionService.deleteById(transaccion_id);
            fondoService.actualizarTotal(fondo_id);
            return new ResponseEntity<Transaccion>(transaccion.get(), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
