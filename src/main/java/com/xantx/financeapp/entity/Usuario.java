package com.xantx.financeapp.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "usuarios")
@Data
public class Usuario {
    @Id
    @Column(length = 32, nullable = false)
    private String uuid;
    @Column(length = 15, nullable = false)
    private String name;
    @Column(length = 40, nullable = false)
    private String password;
    @Column(nullable = false)
    private Double total;

    @OneToMany(mappedBy = "usuario")
    private List<Fondo> fondos;
}
