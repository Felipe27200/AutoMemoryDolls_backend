package com.proyecto.grupo_3.repository;

import com.proyecto.grupo_3.entity.Carta;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartaRepository extends JpaRepository<Carta, Long> {
//    Integer findCartasEnProcesoByDoll(Long autoMemoryDollId);

    @Query("SELECT COUNT(c) FROM Carta c WHERE c.CartaEstado.nombre IN ('enviado', 'borrador') AND c.autoMemoryDoll.id = :dollId")
    Integer countCartasProcesoByDoll(@Param("dollId") Long dollId);

    @Query("SELECT c FROM Carta c WHERE c.cliente.id = :clienteId")
    List<Carta> findCartasByClienteId(@Param("clienteId") Long clienteId);
    @Query("SELECT c FROM Carta c WHERE c.CartaEstado.nombre = :estado AND c.cliente.id = :clienteId")
    List<Carta> findCartasByClienteIdAndEstado(@Param("estado") String estado, @Param("clienteId") Long clienteId);
    @Query("SELECT c FROM Carta c WHERE c.autoMemoryDoll.id = :dollId")
    List<Carta> findCartasByDollId(@Param("dollId") Long dollId);
    @Query("SELECT c FROM Carta c WHERE c.id = :cartaId AND c.autoMemoryDoll.id = :dollId")
    Carta findCartasByIdAndDollId(@Param("cartaId") Long cartaId, @Param("dollId") Long dollId);

    @Transactional
    @Modifying
    @Query("DELETE FROM Carta c WHERE c.cliente.id = :clienteId")
    int deleteByClienteId(@Param("clienteId") Long clienteId);
}
