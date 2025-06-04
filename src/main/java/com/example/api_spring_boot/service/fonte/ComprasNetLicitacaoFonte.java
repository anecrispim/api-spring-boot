package com.example.api_spring_boot.service.fonte;

import com.example.api_spring_boot.dto.LicitacaoDTO;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.*;

@Component("comprasnet")
public class ComprasNetLicitacaoFonte implements FonteLicitacao {

    private static final String URL = "http://comprasnet.gov.br/ConsultaLicitacoes/ConsLicitacaoDia.asp";

    @Override
    public List<LicitacaoDTO> buscarTodasLicitacoes() {
        try {
            List<LicitacaoDTO> licitacoes = new ArrayList<>();
            int paginaAtual = 1;
            Document doc = Jsoup.connect(URL+"?pagina="+paginaAtual).get();
            Elements tds = doc.select("td.td");
            Element tdPaginacao = tds.selectFirst("center");
            String textoPaginacao = tdPaginacao.text();
            int paginaFinal = Integer.parseInt(extrairCampo(textoPaginacao, "Licitações (1 - 20 de", ")"));

            while (paginaAtual <= paginaFinal) {
                Elements trs = doc.select("tr.tex3[bgcolor=#FFFFFF]");

                for (Element tr : trs) {
                    Element td = tr.selectFirst("td");
                    if (td == null) continue;

                    String texto = td.text();

                    LicitacaoDTO dto = new LicitacaoDTO();

                    dto.setUasg(extrairCampo(texto, "Código da UASG:", "Pregão"));
                    dto.setPregao(extrairCampo(texto, "Pregão Eletrônico Nº", "-"));
                    dto.setLei(extrairCampo(texto, "(Lei Nº", ")"));
                    dto.setObjeto(extrairCampo(texto, "Objeto: Objeto:", "Edital"));
                    dto.setEditalData(extrairCampo(texto, "Edital a partir de:", "Endereço:"));
                    dto.setEndereco(extrairCampo(texto, "Endereço:", "Telefone:"));
                    dto.setTelefone(extrairCampo(texto, "Telefone:", "Fax:"));
                    dto.setFax(extrairCampo(texto, "Fax:", "Entrega da Proposta:"));
                    dto.setPropostaEntrega(extrairCampo(texto, "Entrega da Proposta:", "Histórico"));

                    licitacoes.add(dto);
                }

                paginaAtual++;

                doc = Jsoup.connect(URL+"?pagina="+paginaAtual).get();
            }
            return licitacoes;
        } catch (IOException e) {
            throw new IllegalStateException("Erro ao acessar a página do ComprasNet.", e);
        } catch (Exception e) {
            throw new RuntimeException("Erro inesperado ao processar a busca de licitações.", e);
        }
    }

    private String extrairCampo(String texto, String inicio, String fim) {
        int start = texto.indexOf(inicio);
        if (start == -1) return "";
        start += inicio.length();

        int end = fim != null ? texto.indexOf(fim, start) : texto.length();
        if (end == -1) end = texto.length();

        return texto.substring(start, end).replaceAll("&nbsp;", "").trim();
    }
}
