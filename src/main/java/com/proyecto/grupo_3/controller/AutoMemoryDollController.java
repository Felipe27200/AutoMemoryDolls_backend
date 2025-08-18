package com.proyecto.grupo_3.controller;

import com.proyecto.grupo_3.entity.AutoMemoryDoll;
import com.proyecto.grupo_3.error_handling.exception.GeneralException;
import com.proyecto.grupo_3.response.BasicResponse;
import com.proyecto.grupo_3.service.AutoMemoryDollService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${apiPrefix}/auto-memory-dolls")
public class AutoMemoryDollController
{
    private final AutoMemoryDollService autoMemoryDollService;

    @Autowired
    public AutoMemoryDollController(AutoMemoryDollService autoMemoryDollService) {
        this.autoMemoryDollService = autoMemoryDollService;
    }

    @PostMapping("/")
    public ResponseEntity<AutoMemoryDoll> save (@Valid @RequestBody AutoMemoryDoll autoMemoryDoll)
    {
        this.validarAutoMemoryDoll(autoMemoryDoll);
        AutoMemoryDoll nuevoCliente = this.autoMemoryDollService.save(autoMemoryDoll);

        return new ResponseEntity<>(nuevoCliente, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AutoMemoryDoll> update(@PathVariable Long id, @Valid @RequestBody AutoMemoryDoll autoMemoryDoll)
    {
        this.validarAutoMemoryDoll(autoMemoryDoll);
        this.validarId(id);

        return new ResponseEntity<>(this.autoMemoryDollService.update(autoMemoryDoll, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?>  delete(@PathVariable Long id)
    {
        this.validarId(id);

        String mensaje = this.autoMemoryDollService.delete(id);
        BasicResponse response = new BasicResponse(mensaje, "successfull");

        return ResponseEntity.ok(response);
    }

    @PutMapping("/cambiar-estado/{id}")
    public ResponseEntity<AutoMemoryDoll> cambiarEstado(@PathVariable Long id)
    {
        this.validarId(id);

        AutoMemoryDoll autoMemoryDoll = this.autoMemoryDollService.cambiarEstado(id);

        return ResponseEntity.ok(autoMemoryDoll);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AutoMemoryDoll> findById(@PathVariable Long id)
    {
        this.validarId(id);

        return new ResponseEntity<>(this.autoMemoryDollService.findByid(id), HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<AutoMemoryDoll>> findAll()
    {
        return new ResponseEntity<>(this.autoMemoryDollService.findAll(), HttpStatus.OK);
    }

    private void validarAutoMemoryDoll(AutoMemoryDoll autoMemoryDoll)
    {
        if (autoMemoryDoll.getNombre() == null || autoMemoryDoll.getNombre().trim().isEmpty())
            throw new GeneralException("El nombre del cliente es requerido");
        if (autoMemoryDoll.getEdad() == null || autoMemoryDoll.getEdad() <= 0)
            throw new GeneralException("La edad debe ser mayor a cero");
    }

    private void validarId(Long id)
    {
        if (id == null || id <= 0)
            throw new GeneralException("El id del cliente debe ser mayor a cero");

    }
}
