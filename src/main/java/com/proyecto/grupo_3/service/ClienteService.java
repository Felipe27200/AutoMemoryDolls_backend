package com.proyecto.grupo_3.service;

import com.proyecto.grupo_3.entity.Cliente;
import com.proyecto.grupo_3.error_handling.exception.GeneralException;
import com.proyecto.grupo_3.error_handling.exception.NotFoundException;
import com.proyecto.grupo_3.repository.ClienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        this.validarCliente(cliente);

        return this.clienteRepository.save(cliente);
    }

    public Cliente findByid(Long id)
    {
        Optional<Cliente> cliente = this.clienteRepository.findById(id);

        if (cliente.isPresent())
            return cliente.get();
        else
            throw new NotFoundException("No se encontro el cliente con el id: " + id);
    }

    public List<Cliente> findAll()
    {
        return this.clienteRepository.findAll();
    }

    public Cliente update(Cliente cliente, Long id)
    {
        this.validarCliente(cliente);

        Cliente oldCliente = this.findByid(id);

        oldCliente.setNombre(cliente.getNombre());
        oldCliente.setInfoContacto(cliente.getInfoContacto());
        oldCliente.setCiudad(cliente.getCiudad());

        return this.clienteRepository.save(oldCliente);
    }

    public String delete(Long id)
    {
        Cliente cliente = this.findByid(id);

        this.clienteRepository.delete(cliente);

        return String.format("Cliente eliminado con id %d", id);
    }

    public void validarCliente(Cliente cliente)
    {
        if (cliente.getNombre() == null || cliente.getNombre().trim().isEmpty())
            throw new GeneralException("El nombre del cliente es requerido");
        if (cliente.getInfoContacto() == null || cliente.getInfoContacto().trim().isEmpty())
            throw new GeneralException("El contacto del cliente es requerido");
        if (cliente.getCiudad() == null || cliente.getCiudad().trim().isEmpty())
            throw new GeneralException("La ciudad del cliente es requerido");

    }
}
