package com.onu.worldsurviver.services;

import java.util.List;
import java.util.Optional;

import com.onu.worldsurviver.model.entity.DadosContinente;


public interface ContinenteService {

    DadosContinente salvar(DadosContinente dadosContinente);
    
    DadosContinente atualizar(DadosContinente dadosContinente);
    
    void deletar(DadosContinente dadosContinente);
    
    List<DadosContinente> buscar(DadosContinente dadosContinenteFiltro);

    void validar(DadosContinente dadosContinente);

    Optional<DadosContinente> obterPorId(Long id);
}