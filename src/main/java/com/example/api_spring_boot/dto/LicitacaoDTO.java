package com.example.api_spring_boot.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LicitacaoDTO {
    private String uasg;
    private String pregao;
    private String lei;
    private String objeto;
    private String editalData;
    private String endereco;
    private String telefone;
    private String fax;
    private String propostaEntrega;
}
