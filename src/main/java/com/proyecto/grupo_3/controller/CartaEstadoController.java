package com.proyecto.grupo_3.controller;

import com.proyecto.grupo_3.entity.CartaEstado;
import com.proyecto.grupo_3.error_handling.exception.GeneralException;
import com.proyecto.grupo_3.service.CartaEstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("${apiPrefix}/carta-estados")
@CrossOrigin(origins = "*")
public class CartaEstadoController
{
    private final CartaEstadoService cartaEstadoService;

    @Autowired
    public CartaEstadoController(CartaEstadoService cartaEstadoService) {
        this.cartaEstadoService = cartaEstadoService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CartaEstado> findById(@PathVariable Long id)
    {
        this.validarId(id);

        return new ResponseEntity<>(this.cartaEstadoService.findByid(id), HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<CartaEstado>> findAll()
    {
        return new ResponseEntity<>(this.cartaEstadoService.findAll(), HttpStatus.OK);
    }

    private void validarId(Long id)
    {
        if (id == null || id <= 0)
            throw new GeneralException("El id del estado debe ser mayor a cero");

    }
}
