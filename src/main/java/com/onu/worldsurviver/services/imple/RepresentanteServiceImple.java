package com.onu.worldsurviver.services.imple;

import java.util.Optional;

import com.onu.worldsurviver.exception.ErroAutenticacaoException;
import com.onu.worldsurviver.exception.RegraNegocioException;
import com.onu.worldsurviver.model.entity.DadosRepresentante;
import com.onu.worldsurviver.model.repository.RepresentanteRepository;
import com.onu.worldsurviver.services.RepresentanteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Class: RepresentanteServiceImple
 * 
 * Essa classe é responsável por consttituir os métodos das regras de négocios
 * dos representantes.
 * O objetivo dessa classe é implementar os métodos "autenticar", "validar" e "cadastrar"
 * juntamente da interface "RepresentanteRepository" e
 * seguindo toda a lógica da regra de négocio que possibilita as devidas validações.
 * 
 * @author: Mateus Santos de Jesus
 */

@Service
public class RepresentanteServiceImple implements RepresentanteService {
    
    private RepresentanteRepository representanteRepository;
    
    @Autowired
    public RepresentanteServiceImple(RepresentanteRepository representanteRepository) {
        super();
        this.representanteRepository = representanteRepository;
    }
        
    @Override
    public DadosRepresentante autenticar(String email, String senha) {
        Optional<DadosRepresentante> representante = representanteRepository.findByEmail(email);
        
        if(!representante.isPresent()) {
            throw new ErroAutenticacaoException("E-mail incorreto ou inexistente");
        }
        else if(!representante.get().getSenha().equals(senha))
        {
            throw new ErroAutenticacaoException("Senha incorreta");
        }
        else {
            return representante.get();
        }
    }

    @Override
    @Transactional
    public DadosRepresentante salvarRepresentante(DadosRepresentante representante) {
        validarEmail(representante.getEmail());
        return representanteRepository.save(representante);
    }

    @Override
    public Void validarEmail(String email) {
        boolean existe = representanteRepository.existsByEmail(email);
        
        if(existe) {
            throw new RegraNegocioException("Email já cadastrado.");
        }
        else {
            return null;
        }
    }
    
}
