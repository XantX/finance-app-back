package com.xantx.financeapp.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Table(name = "usuarios")
@Data
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 15, nullable = false)
    private String name;
    @Column(length = 40, nullable = false)
    private String password;
    @Column(nullable = false)
    private Double total = 0.0;

    @OneToMany(mappedBy = "usuario")
    @JsonIgnore
    private List<Fondo> fondos;
}
