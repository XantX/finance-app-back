package com.xantx.financeapp.controllers;

import com.xantx.financeapp.services.TransaccionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/transacciones")
public class TransaccionController {
    @Autowired
    private TransaccionService transaccionService;
}
