package com.onu.worldsurviver.service;

import java.util.Optional;

import com.onu.worldsurviver.exception.RegraNegocioException;
import com.onu.worldsurviver.model.entity.DadosRepresentante;
import com.onu.worldsurviver.model.repository.RepresentanteRepository;
import com.onu.worldsurviver.services.RepresentanteService;
import com.onu.worldsurviver.services.imple.RepresentanteServiceImple;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
public class UsuarioServiceTest {
    
    RepresentanteService service;
    
    @MockBean
    RepresentanteRepository repository;

    RegraNegocioException exception;
    
    @BeforeEach
    public void setUp() {
        repository = Mockito.mock(RepresentanteRepository.class);
        service = new RepresentanteServiceImple(repository);
    }

    @Test  
    public void deveAutenticarUmUsuarioComSucesso() {

        //Cenário
        String email = "mateussj305@gmail.com";
        String senha = "12345678";
                                                                                                                                                                                                                                                                                                                                                                                                                          
        DadosRepresentante representante = DadosRepresentante
        .builder()
        .email(email)
        .senha(senha)
        .id(1L)
        .build();

        Mockito.when(repository.findByEmail(email)).thenReturn(Optional.of(representante));

        //Verifiação
        Assertions.assertThat(service.autenticar(email, senha)).isNotNull();
    }

    @Test
    public void deveValirdarEmail() {

        //Verificação
        service.validarEmail("email@email.com.br");
    }
    
    
    @Test
    public void naoDeveLançarErroSeNaoExistirEmailCadastrado() {
            
        //Cenario
        Mockito.when(repository.existsByEmail(Mockito.anyString())).thenReturn(false);
 
		//Verificação
        Assertions.assertThat(service.validarEmail("email@gmail.com")).isNull();          
        
    }
}
