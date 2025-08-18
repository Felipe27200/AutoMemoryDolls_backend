package com.proyecto.grupo_3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutoMemoryDollRepository extends JpaRepository<AutoMemoryDollRepository, Long> {
}
