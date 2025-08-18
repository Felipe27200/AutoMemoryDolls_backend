package com.proyecto.grupo_3.service;

import com.proyecto.grupo_3.entity.CartaEstado;
import com.proyecto.grupo_3.error_handling.exception.NotFoundException;
import com.proyecto.grupo_3.repository.CartaEstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartaEstadoService {
    private CartaEstadoRepository cartaEstadoRepository;

    @Autowired
    public CartaEstadoService(CartaEstadoRepository cartaEstadoRepository) {
        this.cartaEstadoRepository = cartaEstadoRepository;
    }

    public CartaEstado findByid(Long id) {
        Optional<CartaEstado> cartaEstado = this.cartaEstadoRepository.findById(id);

        if (cartaEstado.isPresent())
            return cartaEstado.get();
        else
            throw new NotFoundException("No se encontro el Estado con el id: " + id);
    }

    public List<CartaEstado> findAll() {
        return this.cartaEstadoRepository.findAll();
    }
}