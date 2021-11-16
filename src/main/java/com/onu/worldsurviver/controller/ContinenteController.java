package com.onu.worldsurviver.controller;

import javax.persistence.Entity;

import com.onu.worldsurviver.dto.ContinenteDto;
import com.onu.worldsurviver.exception.RegraNegocioException;
import com.onu.worldsurviver.model.entity.DadosContinente;
import com.onu.worldsurviver.model.entity.DadosRepresentante;
import com.onu.worldsurviver.services.ContinenteService;
import com.onu.worldsurviver.services.RepresentanteService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/continentes")
public class ContinenteController {
    
    private ContinenteService service;
    private RepresentanteService representanteService;

    public ContinenteController(ContinenteService service, RepresentanteService representanteService) {
        this.service = service;
        this.representanteService = representanteService;
    
    }

   @PostMapping
    public ResponseEntity salvar(@RequestBody ContinenteDto dto) {
        try {
            DadosContinente entidade = converter(dto);
            entidade = service.salvar(entidade);
            return new ResponseEntity(entidade, HttpStatus.CREATED);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("{id}")
    public ResponseEntity atualizar(@PathVariable("id") Long id, @RequestBody ContinenteDto dto) {
        return service.obterPorId(id).map(entity -> {
           try {
            DadosContinente continente = converter(dto);
            continente.setId(entity.getId());
            service.atualizar(continente);
            return ResponseEntity.ok(continente);
           } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
           }
        }).orElseGet(() -> new ResponseEntity("Continente não encontrado na base de dados.", HttpStatus.BAD_REQUEST));
    }

    @DeleteMapping("{id}")
    public ResponseEntity deletar(@PathVariable("id") Long id) {
        return service.obterPorId(id).map(entidade -> {
            try {
             service.deletar(entidade);
             return new ResponseEntity(HttpStatus.NO_CONTENT);
            } catch (RegraNegocioException e) {
             return ResponseEntity.badRequest().body(e.getMessage());
            }
         }).orElseGet(() -> new ResponseEntity("Continente não encontrado na base de dados.", HttpStatus.BAD_REQUEST));
    }

    private DadosContinente converter(ContinenteDto dto) {
        DadosContinente continente = new DadosContinente();
        continente.setContinente(dto.getContinente());
        continente.setDenuncia(dto.getDenuncia());
        continente.setPais(dto.getPais());
        continente.setRepresentante(dto.getRepresentante());

        DadosRepresentante dadosRepresentante = representanteService.obterPorId(dto.getDadosRepresentante()).orElseThrow(() -> new RegraNegocioException("Representante não encontrado"));

        continente.setDadosRepresentante(dadosRepresentante);

        return continente;
    }

}