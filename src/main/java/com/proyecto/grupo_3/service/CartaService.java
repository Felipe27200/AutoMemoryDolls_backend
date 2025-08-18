package com.proyecto.grupo_3.service;

import com.proyecto.grupo_3.entity.Carta;
import com.proyecto.grupo_3.error_handling.exception.GeneralException;
import com.proyecto.grupo_3.error_handling.exception.NotFoundException;
import com.proyecto.grupo_3.repository.CartaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class CartaService
{
    private final CartaRepository cartaRepository;

    @Autowired
    public CartaService(CartaRepository cartaRepository) {
        this.cartaRepository = cartaRepository;
    }

    @Transactional
    public Carta save(Carta carta)
    {
        this.validarCarta(carta);

        carta.setFechaCreacion(LocalDate.now());

        return this.cartaRepository.save(carta);
    }

    public Carta findByid(Long id)
    {
        Optional<Carta> carta = this.cartaRepository.findById(id);

        if (carta.isPresent())
            return carta.get();
        else
            throw new NotFoundException("No se encontro el carta con el id: " + id);
    }

    public List<Carta> findAll()
    {
        return this.cartaRepository.findAll();
    }

    public Carta update(Carta carta, Long id)
    {
        this.validarCarta(carta);

        Carta oldCarta = this.findByid(id);

        oldCarta.setContenido(carta.getContenido());
        oldCarta.setMotivo(carta.getMotivo());
        oldCarta.setAutoMemoryDolls(carta.getAutoMemoryDolls());
        oldCarta.setCliente(carta.getCliente());
        oldCarta.setCartaEstado(carta.getCartaEstado());

        return this.cartaRepository.save(oldCarta);
    }

    public String delete(Long id)
    {
        Carta carta = this.findByid(id);

        this.cartaRepository.delete(carta);

        return String.format("Carta eliminado con id %d", id);
    }

    public void validarCarta(Carta carta)
    {
        if (carta.getContenido() == null || carta.getContenido().trim().isEmpty())
            throw new GeneralException("El contenido de la carta es requerido");
        if (carta.getMotivo() == null || carta.getMotivo().trim().isEmpty())
            throw new GeneralException("El motivo de la carta es requerido");
        if (carta.getCliente() == null || carta.getCliente().getId() <= 0)
            throw new GeneralException("El id del cliente es requerido");
        if (carta.getAutoMemoryDolls() == null || carta.getAutoMemoryDolls().getId() <= 0)
            throw new GeneralException("El id de la Auto Memory Doll es requerido");
    }
}
