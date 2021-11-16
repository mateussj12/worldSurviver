package com.onu.worldsurviver.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ContinenteDto {

    private Long id;
    private String denuncia;
    private String pais;
    private String representante;
    private String continente;
    private Long dadosRepresentante;
}
