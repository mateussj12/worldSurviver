package com.onu.worldsurviver.service;

import com.onu.worldsurviver.model.entity.DadosRepresentante;
import com.onu.worldsurviver.model.repository.RepresentanteRepository;
import com.onu.worldsurviver.services.RepresentanteService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
public class UsuarioServiceTest {
    
    @Autowired
    RepresentanteService service;

    @Autowired
    RepresentanteRepository repository;
    
    @Test
    public void deveValirdarEmail() {
            
        //Cenário
        repository.deleteAll();

        //Execução
        service.validarEmail("email@email.com.br");
    }
    

    @Test
    public void deveLançarErroSeExistirEmailCadastrado() {
        
        // Cenario
        DadosRepresentante representante = DadosRepresentante.builder().nome("usuario").email("email@gmail.com").build();
        repository.save(representante);

        // Execução
        service.validarEmail("email@gmail.com");
    }
}
