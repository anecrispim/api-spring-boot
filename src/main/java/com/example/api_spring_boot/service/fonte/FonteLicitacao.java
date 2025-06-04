package com.example.api_spring_boot.service.fonte;

import com.example.api_spring_boot.dto.LicitacaoDTO;

import java.util.List;

public interface FonteLicitacao {
    List<LicitacaoDTO> buscarTodasLicitacoes() throws Exception;
}
