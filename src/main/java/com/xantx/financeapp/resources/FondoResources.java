package com.xantx.financeapp.resources;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class FondoResources {
    @NotNull
    @NotBlank(message = "Descripcion requerida")
    @Size(max = 40)
    private String descripcion;

}
