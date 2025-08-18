package com.proyecto.grupo_3.repository;

import com.proyecto.grupo_3.entity.AutoMemoryDoll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AutoMemoryDollRepository extends JpaRepository<AutoMemoryDoll, Long> {
    @Query("SELECT COUNT(c) FROM Carta c WHERE c.CartaEstado.nombre IN ('enviado', 'borrador')")
    Integer countCartasProcesoByDoll(@Param("dollId") Long dollId);
}
