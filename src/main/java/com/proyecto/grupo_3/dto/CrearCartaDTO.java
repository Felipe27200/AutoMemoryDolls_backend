package com.proyecto.grupo_3.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CrearCartaDTO
{
    @NotBlank(message = "El motivo de la carta es necesario")
    private String motivo;
    @NotBlank(message = "El contenido de la carta es necesario")
    private String contenido;

    @Min(value = 1, message = "El id del Auto Doll memory debe ser mayor a cero")
    @NotNull(message = "El id del Auto Doll memory es requerida")
    private Long autoMemoryDollId;
    @Min(value = 1, message = "El id cliente debe ser mayor a cero")
    @NotNull(message = "El id cliente es requerido")
    private Long clienteId;
    @Min(value = 1, message = "El id del estado debe ser mayor a cero")
    @NotNull(message = "El id del estado es requerido")
    private Long cartaEstadoId;

    public CrearCartaDTO() {
    }

    public CrearCartaDTO(String motivo, String contenido, Long autoMemoryDollId, Long clienteId, Long cartaEstadoId) {
        this.motivo = motivo;
        this.contenido = contenido;
        this.autoMemoryDollId = autoMemoryDollId;
        this.clienteId = clienteId;
        this.cartaEstadoId = cartaEstadoId;
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

    public Long getAutoMemoryDollId() {
        return autoMemoryDollId;
    }

    public void setAutoMemoryDollId(Long autoMemoryDollId) {
        this.autoMemoryDollId = autoMemoryDollId;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public Long getCartaEstadoId() {
        return cartaEstadoId;
    }

    public void setCartaEstadoId(Long cartaEstadoId) {
        this.cartaEstadoId = cartaEstadoId;
    }
}
