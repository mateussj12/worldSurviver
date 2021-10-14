package com.onu.worldsurviver.model.repository;

import com.onu.worldsurviver.model.entity.DadosRepresentante;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class RepresentanteRepositoryTest {
   
   @Autowired
   RepresentanteRepository repository;

   @Autowired
   TestEntityManager entityManager;

   @Test
   public void deveVerificarEmailExisteTest() {
     
      //Cenário
      DadosRepresentante representante = DadosRepresentante.builder().nome("usuario").email("usr123@email.com").build();
      entityManager.persist(representante);

      //Execução
      Boolean result = repository.existsByEmail("usr123@email.com");

      //Verificação
      Assertions.assertThat(result).isTrue();
   }

   @Test
   public void deveRetornarFalsoSeNaoHouverEmailTest() {
      
      //Execução
      Boolean result = repository.existsByEmail("usr123@gmail.com");
      
      //Verificação
      Assertions.assertThat(result).isFalse();
   }

}
