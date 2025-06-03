package com.example.api_spring_boot.service;

import com.example.api_spring_boot.dto.LicitacaoDTO;
import com.example.api_spring_boot.service.fonte.FonteLicitacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LicitacaoService {

    private final FonteLicitacao fonteLicitacao;

    @Autowired
    public LicitacaoService(@Qualifier("comprasnet") FonteLicitacao fonteLicitacao) {
        this.fonteLicitacao = fonteLicitacao;
    }

    public List<LicitacaoDTO> listarLicitacoes() throws Exception {
        return fonteLicitacao.buscarLicitacoes();
    }

    public List<LicitacaoDTO> listarLicitacoesPaginada(int pg) throws Exception {
        return fonteLicitacao.buscarLicitacoesPaginada(pg);
    }
}

