package com.onu.worldsurviver.dto;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.onu.worldsurviver.model.enums.Sexo;

import groovy.transform.builder.Builder;
import lombok.Data;

@Data
@Builder
public class RepresentanteDto {
    
    private String nome;
    private String senha;
    private String email;
    private String continente;
    private String telefone;
    private String nascimento;
    private String cpf;
    private String rg;
    private String cargo;
    
    @Enumerated(value = EnumType.STRING)
    private Sexo sexo;    
}
