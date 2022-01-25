package com.xantx.financeapp.resources;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class BancoResource {
    @NotNull
    @NotBlank(message = "Nombre requerido")
    @Size(max = 15)
    private String nombre;

    @NotNull
    @NotBlank(message = "Link requerido")
    @Size(max = 15)
    private String link;
}
