package com.xantx.financeapp.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Table(name = "fondos")
@Data
public class Fondo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = true)
    private String description;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "banco_id", referencedColumnName = "id")
    private Banco banco;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    @JsonIgnore
    private Usuario usuario;

    @Column
    private Double total = 0.0;

    @OneToMany(mappedBy = "fondo", cascade = CascadeType.REMOVE)
    private List<Transaccion> transacciones;

    public void agrearAlTotal(Transaccion transaccion) {
        this.total += transaccion.getCantidad();
    }

    public void actualizarTotal() {
        this.total = 0.0;
        for (Transaccion transaccion : transacciones) {
            this.total += transaccion.getCantidad();
        }
    }

}
