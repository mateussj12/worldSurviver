package com.onu.worldsurviver.controller;

import com.onu.worldsurviver.dto.RepresentanteDto;
import com.onu.worldsurviver.exception.RegraNegocioException;
import com.onu.worldsurviver.model.entity.DadosRepresentante;
import com.onu.worldsurviver.services.RepresentanteService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/representantes")
public class RepresentanteController {
    
    private RepresentanteService service;

    public RepresentanteController (RepresentanteService service) {
       
        this.service = service;
    
    }

    @PostMapping
    public ResponseEntity salvar( @RequestBody RepresentanteDto dto) {

        DadosRepresentante representante = DadosRepresentante.builder()
        .nome(dto.getNome())
        .senha(dto.getSenha())
        .email(dto.getEmail())
        .continente(dto.getContinente())
        .telefone(dto.getTelefone())
        .nascimento(dto.getNascimento())
        .cpf(dto.getCpf())
        .rg(dto.getRg())
        .cargo(dto.getCargo())
        .build();

        try {
            DadosRepresentante representanteSalvo = service.salvarRepresentante(representante);
            return new ResponseEntity(representanteSalvo, HttpStatus.CREATED);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }


    }

}
