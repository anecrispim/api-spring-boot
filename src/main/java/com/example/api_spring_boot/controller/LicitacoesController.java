package com.example.api_spring_boot.controller;

import com.example.api_spring_boot.dto.LicitacaoDTO;
import com.example.api_spring_boot.service.LicitacaoService;
import com.example.api_spring_boot.service.fonte.ComprasNetLicitacaoFonte;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/licitacoes")
public class LicitacoesController {

    @Autowired
    private LicitacaoService service;
    @Autowired
    private ComprasNetLicitacaoFonte scraper;


    @PostMapping("/salvar")
    @Operation(
            summary = "Captura e salva novas licitações públicas",
            description = "Realiza o scraping das licitações disponíveis no site do ComprasNet e salva os dados obtidos em um arquivo JSON local."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Confirmação de sucesso no salvamento das licitações",
            content = @Content(
                    mediaType = "text/plain",
                    examples = @ExampleObject(
                            name = "Mensagem de sucesso",
                            value = "Dados obtidos e salvos com sucesso."
                    )
            )
    )
    public ResponseEntity<String> salvar() {
        List<LicitacaoDTO> dados = scraper.buscarTodasLicitacoes();
        service.salvar(dados);
        return ResponseEntity.ok("Dados obtidos e salvos com sucesso.");
    }

    @GetMapping("/listar")
    @Operation(
            summary = "Lista licitações salvas com filtros opcionais por UASG e pregão",
            description = "Retorna as licitações públicas capturadas com possibilidade de filtrar por código UASG e número de pregão"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Lista de licitações filtradas",
            content = @Content(
                    mediaType = "application/json",
                    examples = @ExampleObject(
                            name = "Exemplo de resposta",
                            value = """
                {
                  "total": 1,
                  "dados": [
                    {
                      "uasg": "250005",
                      "pregao": "90079/2025",
                      "lei": "14.133/2021",
                      "objeto": "Pregão Eletrônico - Registro de Preços para aquisição de BIOTINA 2,5MG, CÁPSULA, conforme especificações contidas no",
                      "editalData": "03/06/2025 das 08:00 às 12:00 Hs e das 14:00 às 17:59 Hs",
                      "endereco": "Esplanada Dos Ministérios, Bloco g Anexo, Ala a 4º Andar Sala 471 - Setor de Administração Federal - Asa Sul - BRASÍLIA (DF)",
                      "telefone": "(0xx61) 33152551",
                      "fax": "(0xx61)",
                      "propostaEntrega": "03/06/2025 às 08:00Hs"
                    }
                  ]
                }
                """
                    )
            )
    )
    public ResponseEntity<Map<String, Object>> listar(
            @RequestParam(required = false) String uasg,
            @RequestParam(required = false) String pregao
    ) {
        return ResponseEntity.ok(service.listar(uasg, pregao));
    }
}
