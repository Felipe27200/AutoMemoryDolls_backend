package com.proyecto.grupo_3.service;

import com.proyecto.grupo_3.entity.Cliente;
import com.proyecto.grupo_3.repository.ClienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService
{
    private ClienteRepository clienteRepository;

    @Autowired
    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Transactional
    public Cliente save(Cliente cliente)
    {
        return this.clienteRepository.save(cliente);
    }
}
