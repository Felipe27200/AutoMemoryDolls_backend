package com.proyecto.grupo_3.service;

import com.proyecto.grupo_3.entity.AutoMemoryDoll;
import com.proyecto.grupo_3.error_handling.exception.GeneralException;
import com.proyecto.grupo_3.error_handling.exception.NotFoundException;
import com.proyecto.grupo_3.repository.AutoMemoryDollRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AutoMemoryDollService
{
    private final AutoMemoryDollRepository autoMemoryDollRepository;

    @Autowired
    public AutoMemoryDollService(AutoMemoryDollRepository autoMemoryDollRepository) {
        this.autoMemoryDollRepository = autoMemoryDollRepository;
    }

    @Transactional
    public AutoMemoryDoll save(AutoMemoryDoll autoMemoryDoll)
    {
        this.validarAutoMemoryDoll(autoMemoryDoll);

        return this.autoMemoryDollRepository.save(autoMemoryDoll);
    }

    public AutoMemoryDoll findByid(Long id)
    {
        Optional<AutoMemoryDoll> autoMemoryDoll = this.autoMemoryDollRepository.findById(id);

        if (autoMemoryDoll.isPresent())
            return autoMemoryDoll.get();
        else
            throw new NotFoundException("No se encontro la Auto Memory Doll con el id: " + id);
    }

    public List<AutoMemoryDoll> findAll()
    {
        return this.autoMemoryDollRepository.findAll();
    }

    @Transactional
    public AutoMemoryDoll update(AutoMemoryDoll autoMemoryDoll, Long id)
    {
        this.validarAutoMemoryDoll(autoMemoryDoll);

        AutoMemoryDoll oldAutoMemoryDoll = this.findByid(id);

        oldAutoMemoryDoll.setNombre(autoMemoryDoll.getNombre());
        oldAutoMemoryDoll.setEdad(autoMemoryDoll.getEdad());

        return this.autoMemoryDollRepository.save(oldAutoMemoryDoll);
    }

    @Transactional
    public String delete(Long id)
    {
        AutoMemoryDoll autoMemoryDoll = this.findByid(id);

        this.autoMemoryDollRepository.delete(autoMemoryDoll);

        return String.format("La Auto Memory Doll eliminado con id %d", id);
    }

    @Transactional
    public AutoMemoryDoll cambiarEstado(Long id)
    {
        AutoMemoryDoll autoMemoryDoll = this.findByid(id);

        autoMemoryDoll.setEstado(!autoMemoryDoll.isEstado());

        autoMemoryDoll = this.autoMemoryDollRepository.save(autoMemoryDoll);

        return autoMemoryDoll;
    }

    public void validarAutoMemoryDoll(AutoMemoryDoll autoMemoryDoll)
    {
        if (autoMemoryDoll.getNombre() == null || autoMemoryDoll.getNombre().trim().isEmpty())
            throw new GeneralException("El nombre es requerido");
        if (autoMemoryDoll.getEdad() == null || autoMemoryDoll.getEdad() <= 0)
            throw new GeneralException("La edad debe ser mayor a cero");
    }
}
