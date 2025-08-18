package com.proyecto.grupo_3.repository;

import com.proyecto.grupo_3.entity.AutoMemoryDoll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AutoMemoryDollRepository extends JpaRepository<AutoMemoryDoll, Long> {
    @Query("""
        SELECT a FROM AutoMemoryDoll a
        LEFT JOIN Carta c ON c.autoMemoryDoll = a AND c.CartaEstado.nombre IN ('borrador', 'enviado')
        WHERE a.estado = true
        GROUP BY a
        HAVING COUNT(c) < 5
        """)
    List<AutoMemoryDoll> findDollsDisponibles();
}
