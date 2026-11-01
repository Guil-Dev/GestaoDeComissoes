package br.edu.ifsp.xyz.service;

import java.io.File;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/** Localiza os arquivos da pasta dos arquivos .txt sem depender da pasta atual do terminal. */
public final class CaminhosDados {
    private CaminhosDados() { }

    public static String resolver(String caminhoOriginal) {
        if (caminhoOriginal == null || caminhoOriginal.trim().isEmpty()) {
            throw new IllegalArgumentException("O caminho do arquivo não pode ser vazio.");
        }

        Path original = Paths.get(caminhoOriginal);
        if (original.isAbsolute() && Files.isRegularFile(original)) {
            return original.normalize().toString();
        }
        if (Files.isRegularFile(original)) {
            return original.toAbsolutePath().normalize().toString();
        }

        String nomeArquivo = original.getFileName().toString();
        Set<Path> bases = new LinkedHashSet<>();
        adicionarAncestrais(bases, Paths.get("").toAbsolutePath());

        // Também procura a partir do local onde as classes compiladas estão salvas.
        try {
            URI localClasses = CaminhosDados.class.getProtectionDomain()
                    .getCodeSource().getLocation().toURI();
            adicionarAncestrais(bases, Paths.get(localClasses).toAbsolutePath());
        } catch (Exception ignored) {
            // O diretório atual ainda será usado como alternativa.
        }

        List<Path> caminhos = new ArrayList<>();
        Path pacoteSrc = Paths.get("src", "br", "edu", "ifsp", "xyz", "leitor");
        Path pacote = Paths.get("br", "edu", "ifsp", "xyz", "leitor");
        for (Path base : bases) {
            caminhos.add(base.resolve(caminhoOriginal));
            caminhos.add(base.resolve("dados").resolve(nomeArquivo));
            caminhos.add(base.resolve(pacoteSrc).resolve("dados").resolve(nomeArquivo));
            caminhos.add(base.resolve(pacoteSrc).resolve(nomeArquivo));
            caminhos.add(base.resolve(pacote).resolve("dados").resolve(nomeArquivo));
            caminhos.add(base.resolve(pacote).resolve(nomeArquivo));
        }

        for (Path candidato : caminhos) {
            if (Files.isRegularFile(candidato)) {
                return candidato.toAbsolutePath().normalize().toString();
            }
        }

        throw new IllegalArgumentException(
                "Não encontrei o arquivo '" + nomeArquivo + "'. "
                + "Mantenha os arquivos .txt na pasta dos arquivos .txt, na pasta src/br/edu/ifsp/xyz/leitor do projeto. "
                + "Caminho atual do terminal: " + new File(".").getAbsolutePath());
    }

    private static void adicionarAncestrais(Set<Path> bases, Path caminho) {
        Path atual = caminho;
        for (int i = 0; atual != null && i < 10; i++, atual = atual.getParent()) {
            bases.add(atual.normalize());
        }
    }
}
