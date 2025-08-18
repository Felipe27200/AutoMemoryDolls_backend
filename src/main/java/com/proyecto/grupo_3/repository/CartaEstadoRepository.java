package com.proyecto.grupo_3.repository;

import com.proyecto.grupo_3.entity.CartaEstado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartaEstadoRepository extends JpaRepository<CartaEstado, Long> {
}
