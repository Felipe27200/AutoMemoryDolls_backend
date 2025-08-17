package com.proyecto.grupo_3.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "cartas")
public class Carta
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "carta_id")
    private Long id;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_creacion", nullable = false)
    private LocalDate fechaCreacion;

    @Column(nullable = false)
    private String motivo;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String contenido;

    @ManyToOne()
    @JoinColumn(
        name = "auto_memory_doll_id",
        referencedColumnName = "auto_memory_doll_id",
        nullable = false
    )
    private AutoMemoryDolls autoMemoryDolls;

    @ManyToOne()
    @JoinColumn(
            name = "cliente_id",
            referencedColumnName = "cliente_id",
            nullable = false
    )
    private Cliente cliente;

    @ManyToOne()
    @JoinColumn(
            name = "carta_estados_id",
            referencedColumnName = "carta_estados_id",
            nullable = false
    )
    private CartaEstado CartaEstado;

    public Carta() {
    }

    public Carta(Long id, LocalDate fechaCreacion, String motivo, String contenido, AutoMemoryDolls autoMemoryDolls, Cliente cliente, CartaEstado cartaEstado) {
        this.id = id;
        this.fechaCreacion = fechaCreacion;
        this.motivo = motivo;
        this.contenido = contenido;
        this.autoMemoryDolls = autoMemoryDolls;
        this.cliente = cliente;
        CartaEstado = cartaEstado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public AutoMemoryDolls getAutoMemoryDolls() {
        return autoMemoryDolls;
    }

    public void setAutoMemoryDolls(AutoMemoryDolls autoMemoryDolls) {
        this.autoMemoryDolls = autoMemoryDolls;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public CartaEstado getCartaEstado() {
        return CartaEstado;
    }

    public void setCartaEstado(CartaEstado cartaEstado) {
        CartaEstado = cartaEstado;
    }
}
