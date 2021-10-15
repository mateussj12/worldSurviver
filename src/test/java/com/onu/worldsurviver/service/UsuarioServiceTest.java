package com.onu.worldsurviver.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;

import java.util.Optional;

import com.onu.worldsurviver.exception.ErroAutenticacaoException;
import com.onu.worldsurviver.exception.RegraNegocioException;
import com.onu.worldsurviver.model.entity.DadosRepresentante;
import com.onu.worldsurviver.model.repository.RepresentanteRepository;
import com.onu.worldsurviver.services.imple.RepresentanteServiceImple;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
public class UsuarioServiceTest {
    
    @SpyBean
    RepresentanteServiceImple service;
    
    @MockBean
    RepresentanteRepository repository;

    RegraNegocioException exception;

    @Test
    public void deveSalvarUsurario() {

        //assertThrows(ErroAutenticacaoException.class, () -> {

            //Cenário
            Mockito.doNothing().when(service).validarEmail(Mockito.anyString());
            
            DadosRepresentante representante = DadosRepresentante
            .builder()
            .id(1l)
            .nome("Mateus")
            .email("mateussj305@gmail.com")
            .senha("senha")
            .build();

            Mockito.when(repository.save(Mockito.any(DadosRepresentante.class))).thenReturn(representante);

            //Ação
            DadosRepresentante representanteSalvo = service.salvarRepresentante(representante);

            //Verificação
            Assertions.assertThat(representanteSalvo).isNotNull();
            Assertions.assertThat(representanteSalvo.getId()).isEqualTo(1l);
            Assertions.assertThat(representanteSalvo.getNome()).isEqualTo("Mateus");
            Assertions.assertThat(representanteSalvo.getEmail()).isEqualTo("mateussj305@gmail.com");
            Assertions.assertThat(representanteSalvo.getSenha()).isEqualTo("senha");

        //});
    }

    @Test  
    public void deveAutenticarUmRepresentanteComSucesso() {

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
    public void naoDeveSalvarUmRepresentanteComEmailCadastrado() {
        
        assertThrows(RegraNegocioException.class, () -> {

            //Cenário
            String email = "mateussj305@gmail.com";
            DadosRepresentante representante = DadosRepresentante.builder()
            .email("mateussj305@gmail.com")
            .build();

            Mockito.doThrow(RegraNegocioException.class).when(service).validarEmail(email);

            //Ação
            service.salvarRepresentante(representante);

            //Verificação
            Mockito.verify(repository, Mockito.never()).save(representante);
        });
 
    }

    @Test
    public void deveLancarErroQuandoNaoHouverEmail() {
        
        //Cenário
        Mockito.when(repository.findByEmail(anyString())).thenReturn(Optional.empty());

        //Ação
        Throwable exception = Assertions.catchThrowable( () -> service.autenticar("mateussj305@gmail.com", "senha") ); 
        
        //Verificação
        Assertions.assertThat(exception).isInstanceOf(ErroAutenticacaoException.class).hasMessageContaining("E-mail incorreto ou inexistente");
    }

    @Test
    public void deveLancarErroSeASenhaEstiverInvalida() {

        //Cenário
        String email = "mateussj305@gmail.com";
        String senha = "12345678";
                                                                                                                                                                                                                                                                                                                                                                                                                          
        DadosRepresentante representante = DadosRepresentante
        .builder()
        .email(email)
        .senha(senha)
        .build();

        Mockito.when(repository.findByEmail(anyString())).thenReturn(Optional.of(representante));

        //Ação
        Throwable exception = Assertions.catchThrowable(() -> service.autenticar("mateussj305@gmail.com", "senha"));

        //Verificação
        Assertions.assertThat(exception).isInstanceOf(ErroAutenticacaoException.class).hasMessage("Senha incorreta");

    }

    @Test
    public void deveValirdarEmail() {

        //Ação
        service.validarEmail("email@email.com.br");
    }
    
    @Test
    public void deveLançarErroSeExistirEmailCadastrado() {
        
        assertThrows(RegraNegocioException.class, () -> {
            
            //Cenario
            Mockito.when(repository.existsByEmail(Mockito.anyString())).thenReturn(true);
 
		    //Ação
            service.validarEmail("email@gmail.com");
        });
        
    }
}
