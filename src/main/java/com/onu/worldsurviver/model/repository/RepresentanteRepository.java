package com.onu.worldsurviver.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

import com.onu.worldsurviver.model.entity.DadosRepresentante;

/**
 * Interface: RepresanteRepository
 * 
 * Essa interface é responsável por protocolar e executar os métodos CRUD na classe: RepresentanteServiceImple
 * que são estendidas da classe JpaRepository.
 * 
 * 
 * @author: Mateus Santos de Jesus.
 */

public interface RepresentanteRepository extends JpaRepository<DadosRepresentante, Long> {
    
   Boolean existsByEmail(String email);

   Optional<DadosRepresentante> findByEmail(String email); 

   Optional<DadosRepresentante> findById(Long id);

}
