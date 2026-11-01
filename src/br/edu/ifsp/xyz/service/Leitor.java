package br.edu.ifsp.xyz.service;

import java.io.BufferedReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

/** Lê um arquivo separado por ponto e vírgula e filtra por uma coluna. */
public class Leitor {
    private final String caminhoArquivo;
    private final int indiceCampoChave;
    private final String valorCampoChave;

    public Leitor(String caminhoArquivo, int indiceCampoChave, String valorCampoChave) {
        this.caminhoArquivo = CaminhosDados.resolver(caminhoArquivo);
        this.indiceCampoChave = indiceCampoChave;
        this.valorCampoChave = valorCampoChave;
    }

    public ArrayList<String> conteudo() throws Exception {
        ArrayList<String> linhas = new ArrayList<>();
        try (BufferedReader br = Files.newBufferedReader(Paths.get(caminhoArquivo), StandardCharsets.UTF_8)) {
            String linha;
            boolean cabecalho = true;
            while ((linha = br.readLine()) != null) {
                if (cabecalho) {
                    cabecalho = false;
                    continue;
                }
                String[] campos = linha.split(";", -1);
                if (indiceCampoChave >= 0 && indiceCampoChave < campos.length
                        && campos[indiceCampoChave].trim().equals(valorCampoChave)) {
                    linhas.add(linha);
                }
            }
        }
        return linhas;
    }
}
