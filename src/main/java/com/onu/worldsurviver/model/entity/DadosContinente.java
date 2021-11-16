package com.onu.worldsurviver.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ENTIDADE: DadosContinente
 * 
 * Nessa parte serão constituídos todos os atributos dos representantes,
 * relacionando ao banco de dados utilzando o javax.persistence (JPA).
 * Além disso, nessa etapa também serão codificados os métodos Getters e Setters,
 * equals e hashcode, constructor e toStrign através da anotação @Data do lombok.
 * 
 * 
 * @author: Mateus Santos de Jesus
 */

@Entity
@Table( name = "dadosContinente" , schema = "denuncias" )
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DadosContinente {
    
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column( name= "id" )
    private Long id;

    @Column( name = "denuncia" )
    private String denuncia;

    @Column( name = "pais" )
    private String pais;

    @Column( name = "representante" )
    private String representante;

    @Column( name = "continente" )
    private String continente;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private DadosRepresentante dadosRepresentante;
}
