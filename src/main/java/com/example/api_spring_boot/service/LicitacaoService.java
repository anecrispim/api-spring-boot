package com.example.api_spring_boot.service;

import com.example.api_spring_boot.dto.LicitacaoDTO;
import com.example.api_spring_boot.util.JsonStorage;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class LicitacaoService {

    public void salvar(List<LicitacaoDTO> novos) {
        List<LicitacaoDTO> existentes = JsonStorage.load();
        existentes.addAll(novos);
        JsonStorage.save(existentes);
    }

    public Map<String, Object> listar(String uasgFiltro, String pregaoFiltro) {
        List<LicitacaoDTO> licitacoes = JsonStorage.load();

        List<LicitacaoDTO> filtradas = licitacoes.stream()
                .filter(l -> uasgFiltro == null || l.getUasg().equalsIgnoreCase(uasgFiltro))
                .filter(l -> pregaoFiltro == null || l.getPregao().equalsIgnoreCase(pregaoFiltro))
                .collect(Collectors.toList());

        Map<String, Object> resultado = new HashMap<>();
        resultado.put("total", filtradas.size());
        resultado.put("dados", filtradas);

        return resultado;
    }
}
