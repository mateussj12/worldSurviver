package com.onu.worldsurviver.services.imple;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;

import com.onu.worldsurviver.exception.RegraNegocioException;
import com.onu.worldsurviver.model.entity.DadosContinente;
import com.onu.worldsurviver.model.repository.ContinenteRepository;
import com.onu.worldsurviver.services.ContinenteService;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;


public class ContinenteServiceImple implements ContinenteService {

    private ContinenteRepository repository;

    public ContinenteServiceImple(ContinenteRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public DadosContinente salvar(DadosContinente dadosContinente) {
        validar(dadosContinente);
        return repository.save(dadosContinente);
    }

    @Override
    @Transactional
    public DadosContinente atualizar(DadosContinente dadosContinente) {
        Objects.requireNonNull(dadosContinente.getId());
        validar(dadosContinente);
        return repository.save(dadosContinente);
    }

    @Override
    @Transactional
    public void deletar(DadosContinente dadosContinente) {
        Objects.requireNonNull(dadosContinente);
        repository.delete(dadosContinente);
    }

    @Override
    @Transactional
    public List<DadosContinente> buscar(DadosContinente dadosContinenteFiltro) {
        Example example = Example.of(dadosContinenteFiltro, ExampleMatcher.matching().withStringMatcher(StringMatcher.CONTAINING));
        return repository.findAll(example);
    }

    @Override
    public void validar(DadosContinente dadosContinente) {
        if(dadosContinente.getContinente() == null || dadosContinente.getContinente().trim().equals("")) {
            throw new RegraNegocioException("Informe um Continente válido");
        }
        if(dadosContinente.getRepresentante() == null || dadosContinente.getRepresentante().trim().equals("")) {
            throw new RegraNegocioException("Informe um Representante válido");
        } 
        if(dadosContinente.getDenuncia() == null || dadosContinente.getDenuncia().trim().equals("")) {
            throw new RegraNegocioException("Informe uma Denúncia válida");
        } 
        if(dadosContinente.getPais() == null || dadosContinente.getPais().trim().equals("")) {
            throw new RegraNegocioException("Informe um País válido");
        }
        if(dadosContinente.getDadosRepresentante() == null || dadosContinente.getDadosRepresentante().getId() == null) {
            throw new RegraNegocioException("Informe um País válido");
        }    

    }

    @Override
    public Optional<DadosContinente> obterPorId(Long id) {
        return repository.findById(id);
    }

    
}
