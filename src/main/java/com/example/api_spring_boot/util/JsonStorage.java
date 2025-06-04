package com.example.api_spring_boot.util;

import com.example.api_spring_boot.dto.LicitacaoDTO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class JsonStorage {
    private static final File FILE = new File("src/main/resources/data/licitacoes.json");
    private static final ObjectMapper mapper = new ObjectMapper();

    public static List<LicitacaoDTO> load() {
        try {
            if (!FILE.exists()) return new ArrayList<>();
            return mapper.readValue(FILE, new TypeReference<List<LicitacaoDTO>>() {});
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public static void save(List<LicitacaoDTO> licitacoes) {
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(FILE, licitacoes);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
