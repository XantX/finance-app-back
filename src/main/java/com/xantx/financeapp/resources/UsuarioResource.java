package com.xantx.financeapp.resources;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class UsuarioResource {

    @NotNull
    @NotBlank(message = "Nombre requerido")
    @Size(max = 15)
    private String name;
    @NotNull
    @NotBlank(message = "Contrasenia requerido")
    @Size(max = 40)
    private String password;
}
