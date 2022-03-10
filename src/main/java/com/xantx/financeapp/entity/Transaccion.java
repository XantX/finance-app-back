package com.xantx.financeapp.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Table(name = "transacciones")
@Data
public class Transaccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 3, nullable = false)
    private String tipo;
    @Column(nullable = false)
    private Double cantidad;
    @Column(length = 30)
    private String descripcion;
    @Column(length = 30)
    String fecha;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "fondo_id", referencedColumnName = "id")
    @JsonIgnore
    private Fondo fondo;
}
