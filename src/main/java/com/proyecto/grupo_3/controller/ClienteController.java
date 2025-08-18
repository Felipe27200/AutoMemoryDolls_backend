package com.proyecto.grupo_3.controller;

import com.proyecto.grupo_3.entity.Cliente;
import com.proyecto.grupo_3.error_handling.exception.GeneralException;
import com.proyecto.grupo_3.response.BasicResponse;
import com.proyecto.grupo_3.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${apiPrefix}/clientes")
public class ClienteController {
    private ClienteService clienteService;

    @Autowired
    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping("/")
    public ResponseEntity<Cliente> save (@Valid @RequestBody Cliente cliente)
    {
        this.validarCliente(cliente);
        Cliente nuevoCliente = this.clienteService.save(cliente);

        return new ResponseEntity<>(nuevoCliente, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> update(@PathVariable Long id, @Valid @RequestBody Cliente cliente)
    {
        this.validarCliente(cliente);
        this.validarId(id);

        return new ResponseEntity<>(this.clienteService.update(cliente, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?>  delete(@PathVariable Long id)
    {
        this.validarId(id);

        String mensaje = this.clienteService.delete(id);
        BasicResponse response = new BasicResponse(mensaje, "successfull");

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> findById(@PathVariable Long id)
    {
        this.validarId(id);

        return new ResponseEntity<>(this.clienteService.findByid(id), HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<Cliente>> findAll()
    {
        return new ResponseEntity<>(this.clienteService.findAll(), HttpStatus.OK);
    }

    private void validarCliente(Cliente cliente)
    {
        if (cliente.getNombre() == null || cliente.getNombre().trim().isEmpty())
            throw new GeneralException("El nombre del cliente es requerido");
        if (cliente.getInfoContacto() == null || cliente.getInfoContacto().trim().isEmpty())
            throw new GeneralException("El contacto del cliente es requerido");
        if (cliente.getCiudad() == null || cliente.getCiudad().trim().isEmpty())
            throw new GeneralException("La ciudad del cliente es requerido");

    }

    private void validarId(Long id)
    {
        if (id == null || id <= 0)
            throw new GeneralException("El id del cliente debe ser mayor a cero");

    }
}
