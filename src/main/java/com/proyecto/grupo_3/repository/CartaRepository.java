package com.proyecto.grupo_3.repository;

import com.proyecto.grupo_3.entity.Carta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CartaRepository extends JpaRepository<Carta, Long> {
//    Integer findCartasEnProcesoByDoll(Long autoMemoryDollId);
}
