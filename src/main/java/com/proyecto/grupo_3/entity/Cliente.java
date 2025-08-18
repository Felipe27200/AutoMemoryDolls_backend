package com.proyecto.grupo_3.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "clientes")
public class Cliente
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cliente_id")
    private Long id;

    @Column(nullable = false)
    @NotBlank(message = "El nombre es requerido.")
    private String nombre;
    @Column(nullable = false)
    @NotBlank(message = "La ciudad es requerida.")
    private String ciudad;
    @Column(nullable = false, name = "info_contacto")
    @NotBlank(message = "La información de contacto es requerida.")
    private String infoContacto;

    public Cliente() {
    }

    public Cliente(Long id, String nombre, String ciudad, String infoContacto) {
        this.id = id;
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.infoContacto = infoContacto;
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

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getInfoContacto() {
        return infoContacto;
    }

    public void setInfoContacto(String infoContacto) {
        this.infoContacto = infoContacto;
    }
}
