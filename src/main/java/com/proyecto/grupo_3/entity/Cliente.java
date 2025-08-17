package com.proyecto.grupo_3.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "clientes")
public class Cliente
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cliente_id")
    private Long id;

    @Column(nullable = false)
    private String nombre;
    @Column(nullable = false)
    private String ciudad;
    @Column(nullable = false, name = "info_contacto")
    private String InfoContacto;

    public Cliente() {
    }

    public Cliente(Long id, String nombre, String ciudad, String infoContacto) {
        this.id = id;
        this.nombre = nombre;
        this.ciudad = ciudad;
        InfoContacto = infoContacto;
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
        return InfoContacto;
    }

    public void setInfoContacto(String infoContacto) {
        InfoContacto = infoContacto;
    }
}
