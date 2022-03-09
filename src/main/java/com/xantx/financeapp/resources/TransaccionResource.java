package com.xantx.financeapp.resources;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class TransaccionResource {

    @NotNull
    @NotBlank(message = "Tipo requerido")
    @Size(max = 3)
    private String tipo;
    @NotNull
    private Double cantidad;
    @NotNull
    @NotBlank(message = "Descripcion requerida")
    @Size(max = 30)
    private String descripcion;
}
