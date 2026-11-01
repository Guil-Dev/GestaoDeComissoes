package br.edu.ifsp.xyz.teste;

import br.edu.ifsp.xyz.model.*;

public class ComissaoTeste {
    public static void main(String[] args) {
        String idVendedor = args.length > 0 ? args[0] : "101";
        try {
            Comissao comissao = new Comissao(idVendedor);
            comissao.imprimirRelatorio();
        } catch (Exception e) {
            System.err.println("Não foi possível gerar o relatório de comissão.");
            System.err.println("Detalhe: " + e.getMessage());
        }
    }
}