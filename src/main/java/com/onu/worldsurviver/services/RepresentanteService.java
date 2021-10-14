package com.onu.worldsurviver.services;

import com.onu.worldsurviver.model.entity.DadosRepresentante;


/**
 * INTERFACE: RepresentanteService
 * 
 * Essa interface é responsável por ser uma forma de "protocolo"
 * para os métodos que serão implementados na classe: RepresentanteServiceImple.
 * O Objetivo dessa classe é manter o fluxo de métodos que a classe respectiva deverá implementar
 * a fim de prezar pela segurança dos dados.
 * 
 * 
 * @author: Mateus Santos de Jesus
 */

public interface RepresentanteService {
    
    DadosRepresentante autenticar(String email, String senha);
    
    DadosRepresentante salvarRepresentante(DadosRepresentante representante);

    Void validarEmail(String email);
}
