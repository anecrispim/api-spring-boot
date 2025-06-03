package com.example.api_spring_boot.controller;

import com.example.api_spring_boot.dto.LicitacaoDTO;
import com.example.api_spring_boot.service.LicitacaoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/licitacoes")
public class LicitacoesController {

    private final LicitacaoService service;

    public LicitacoesController(LicitacaoService service) {
        this.service = service;
    }

    @GetMapping
    public List<LicitacaoDTO> getLicitacoes() throws Exception {
        return service.listarLicitacoes();
    }

    @GetMapping("{pg}")
    public List<LicitacaoDTO> getLicitacoesPaginada(@PathVariable int pg) throws Exception {
        return service.listarLicitacoesPaginada(pg);
    }
}

