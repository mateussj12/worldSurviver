package com.onu.worldsurviver.model.repository;

import com.onu.worldsurviver.model.entity.DadosContinente;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ContinenteRepository extends JpaRepository<DadosContinente, Long> {
    
}
