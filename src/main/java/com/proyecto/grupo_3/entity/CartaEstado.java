package com.proyecto.grupo_3.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "carta_estados")
public class CartaEstado
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "carta_estados_id")
    private Long id;

    @Column
    private String nombre;

    public CartaEstado() {
    }

    public CartaEstado(Long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
