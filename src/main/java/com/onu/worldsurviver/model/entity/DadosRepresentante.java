package com.onu.worldsurviver.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ENTIDADE: DadosRepresentante
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
@Table( name = "dadosRepresentante" , schema = "denuncias" )
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DadosRepresentante {
    
    // Atributos
    @Id
    @Column( name = "id" )
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;
    
    @Column( name = "nome" )
    private String nome;

    @Column( name = "senha" )
    private String senha;

    @Column( name = "email" )
    private String email;

    @Column( name = "continente" )
    private String continente;

    @Column( name = "telefone" )
    private String telefone;

    @Column( name = "nascimento" )
    private String nascimento;

    @Column( name = "cpf" )
    private String cpf;

    @Column( name = "rg" )
    private String rg;

    @Column( name = "cargo" )
    private String cargo;

}
