package com.example.api_spring_boot.controller;

import com.example.api_spring_boot.service.fonte.ComprasNetLicitacaoFonte;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.api_spring_boot.dto.LicitacaoDTO;
import com.example.api_spring_boot.service.LicitacaoService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class LicitacoesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LicitacaoService licitacaoService;

    @MockBean
    private ComprasNetLicitacaoFonte scraper;

    @Test
    public void deveRetornarListaDeLicitacoesComTotal() throws Exception {
        // Dados mockados
        LicitacaoDTO lic1 = new LicitacaoDTO();
        lic1.setUasg("123456");
        lic1.setPregao("01/2025");
        lic1.setObjeto("Compra de papel");
        List<LicitacaoDTO> lista = List.of(lic1);

        Map<String, Object> resposta = new HashMap<>();
        resposta.put("total", lista.size());
        resposta.put("dados", lista);

        // Quando o service for chamado, retorna os dados mockados
        Mockito.when(licitacaoService.listar(null, null)).thenReturn(resposta);

        mockMvc.perform(get("/licitacoes/listar"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.total").value(1))
                .andExpect(jsonPath("$.dados[0].uasg").value("123456"))
                .andExpect(jsonPath("$.dados[0].pregao").value("01/2025"))
                .andExpect(jsonPath("$.dados[0].objeto").value("Compra de papel"));
    }
}
