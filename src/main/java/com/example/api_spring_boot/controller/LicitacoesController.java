package com.example.api_spring_boot.controller;

import com.example.api_spring_boot.dto.LicitacaoDTO;
import com.example.api_spring_boot.service.LicitacaoService;
import com.example.api_spring_boot.service.fonte.ComprasNetLicitacaoFonte;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/licitacoes")
public class LicitacoesController {

    @Autowired
    private LicitacaoService service;
    @Autowired
    private ComprasNetLicitacaoFonte scraper;


    @PostMapping("/salvar")
    public ResponseEntity<String> salvar() {
        List<LicitacaoDTO> dados = scraper.buscarTodasLicitacoes();
        service.salvar(dados);
        return ResponseEntity.ok("Dados obtidos e salvos com sucesso.");
    }

    @GetMapping("/listar")
    public ResponseEntity<Map<String, Object>> listar(
            @RequestParam(required = false) String uasg,
            @RequestParam(required = false) String pregao
    ) {
        return ResponseEntity.ok(service.listar(uasg, pregao));
    }
}
