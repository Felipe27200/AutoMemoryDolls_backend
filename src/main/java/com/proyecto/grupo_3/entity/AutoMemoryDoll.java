package com.proyecto.grupo_3.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "auto_memory_dolls")
public class AutoMemoryDoll
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "auto_memory_doll_id")
    private Long id;

    @Column(name = "nombre", nullable = false)
    @NotBlank(message = "El nombre es requerido.")
    private String nombre;
    @Column(nullable = false)
    @Min(value = 1, message = "La edad debe ser mayor a cero")
    @NotNull(message = "La edad es requerida")
    private Integer edad;

    @Column(nullable = false)
    private boolean estado = true;

    public AutoMemoryDoll() {
    }

    public AutoMemoryDoll(Long id, String nombre, Integer edad, boolean estado) {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
        this.estado = estado;
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

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
}
