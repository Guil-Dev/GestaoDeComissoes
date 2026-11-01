package br.edu.ifsp.xyz.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import br.edu.ifsp.xyz.service.Leitor;

/**
 * Calcula e apresenta as comissoes de um vendedor.
 * Pode trabalhar com todos os pedidos encontrados nos arquivos ou com um
 * unico pedido, mantendo compatibilidade com os testes anteriores.
 */
public class Comissao {
    private static final String CAMINHO_VENDEDOR = "Vendedor.txt";
    private static final String CAMINHO_PEDIDOS = "Pedido.txt";

    private final Vendedor vendedor;
    private final List<Pedido> pedidos;
    private final Pessoa representante;

    /** Carrega um vendedor e todos os pedidos associados ao ID informado. */
    public Comissao(String idVendedor) throws Exception {
        this.vendedor = new Vendedor(CAMINHO_VENDEDOR, 0, idVendedor);
        this.representante = new Representante("Representante");
        this.pedidos = new ArrayList<>();

        // No arquivo Pedido.txt, a coluna 1 guarda o ID do vendedor.
        ArrayList<String> registros = new Leitor(CAMINHO_PEDIDOS, 1, idVendedor).conteudo();
        for (String registro : registros) {
            String[] campos = registro.split(";", -1);
            if (campos.length < 1) {
                continue;
            }
            Pedido pedido = new Pedido(CAMINHO_PEDIDOS, 0, campos[0].trim());
            this.pedidos.add(pedido);
        }
    }

    /** Mantém compatibilidade com a versão que calcula um pedido por vez. */
    public Comissao(Pedido pedido) {
        this(pedido, new Representante("Representante"));
    }

    /** Permite informar explicitamente a pessoa que recebe comissão de representante. */
    public Comissao(Pedido pedido, Pessoa representante) {
        if (pedido == null) {
            throw new IllegalArgumentException("O pedido não pode ser nulo.");
        }
        this.vendedor = pedido.getVendedor();
        this.pedidos = new ArrayList<>(Arrays.asList(pedido));
        this.representante = representante == null
                ? new Representante("Representante") : representante;
    }

    /** Soma a comissão do vendedor em todos os pedidos ativos carregados. */
    public double calcularComissaoVendedor() {
        double total = 0.0;
        for (Pedido pedido : pedidos) {
            if (pedido.isDesfeito()) continue;
            for (ItemPedido item : pedido.getItens()) {
                double valorVenda = item.calcularValorVenda();
                double percentual = item.getProduto().getCategoria().getPercentualVendedor();
                total += pedido.getVendedor().calcularComissao(valorVenda, percentual);
            }
        }
        return total;
    }

    /** Soma a comissão do representante em todos os pedidos ativos carregados. */
    public double calcularComissaoRepresentante() {
        double total = 0.0;
        for (Pedido pedido : pedidos) {
            if (pedido.isDesfeito()) continue;
            for (ItemPedido item : pedido.getItens()) {
                double valorVenda = item.calcularValorVenda();
                double percentual = item.getProduto().getCategoria().getPercentualRepresentante();
                total += representante.calcularComissao(valorVenda, percentual);
            }
        }
        return total;
    }

    /** Imprime uma linha por item e consolida vendas e comissões de todos os pedidos. */
    public void imprimirRelatorio() {
        System.out.println();
        System.out.println("RELATORIO DE COMISSAO POR VENDEDOR");
        System.out.println("Vendedor: " + vendedor.getNome() + " (ID: " + vendedor.getIdVendedor() + ")");
        System.out.println();

        imprimirSeparador();
        System.out.printf(Locale.US,
                "| %-6s | %-9s | %-9s | %-22s | %7s | %12s | %13s | %7s | %12s |%n",
                "Pedido", "Status", "Produto", "Categoria", "% Vend.", "Venda (R$)",
                "Com.Vend.(R$)", "% Rep.", "Com.Rep.(R$)");
        imprimirSeparador();

        if (pedidos.isEmpty()) {
            System.out.println("Nenhum pedido encontrado para este vendedor.");
            imprimirSeparador();
            System.out.println("Pedidos encontrados: 0");
            System.out.println("Valor dos pedidos ativos: R$ " + dinheiro(0));
            System.out.println("Valor dos pedidos desfeitos: R$ " + dinheiro(0));
            System.out.println("Comissao total do vendedor: R$ " + dinheiro(0));
            System.out.println("Comissao total do representante: R$ " + dinheiro(0));
            return;
        }

        double totalVendasAtivas = 0.0;
        double totalPedidosDesfeitos = 0.0;
        double totalComissaoVendedor = 0.0;
        double totalComissaoRepresentante = 0.0;

        for (Pedido pedido : pedidos) {
            boolean desfeito = pedido.isDesfeito();
            String status = desfeito ? "DESFEITO" : "ATIVO";
            if (desfeito) totalPedidosDesfeitos += pedido.getValor();
            else totalVendasAtivas += pedido.getValor();

            ItemPedido[] itens = pedido.getItens();
            if (itens == null || itens.length == 0) {
                System.out.printf(Locale.US,
                        "| %-6d | %-9s | %-9s | %-22s | %7s | %12.2f | %13.2f | %7s | %12.2f |%n",
                        pedido.getIdPedido(), status, "-", "-", "-", pedido.getValor(), 0.0, "-", 0.0);
                continue;
            }

            for (ItemPedido item : itens) {
                Produto produto = item.getProduto();
                Categoria categoria = produto.getCategoria();
                double valorVenda = item.calcularValorVenda();
                double percentualVendedor = categoria.getPercentualVendedor();
                double percentualRepresentante = categoria.getPercentualRepresentante();
                double comissaoVendedor = desfeito ? 0.0
                        : pedido.getVendedor().calcularComissao(valorVenda, percentualVendedor);
                double comissaoRepresentante = desfeito ? 0.0
                        : representante.calcularComissao(valorVenda, percentualRepresentante);

                totalComissaoVendedor += comissaoVendedor;
                totalComissaoRepresentante += comissaoRepresentante;

                System.out.printf(Locale.US,
                        "| %-6d | %-9s | %-9s | %-22.22s | %6.2f%% | %12.2f | %13.2f | %6.2f%% | %12.2f |%n",
                        pedido.getIdPedido(), status, produto.getCodigo(), categoria.getNome(),
                        percentualVendedor, valorVenda, comissaoVendedor,
                        percentualRepresentante, comissaoRepresentante);
            }
        }

        imprimirSeparador();
        System.out.println("Pedidos encontrados: " + pedidos.size());
        System.out.println("Valor dos pedidos ativos: R$ " + dinheiro(totalVendasAtivas));
        System.out.println("Valor dos pedidos desfeitos: R$ " + dinheiro(totalPedidosDesfeitos));
        System.out.println("Comissao total do vendedor: R$ " + dinheiro(totalComissaoVendedor));
        System.out.println("Comissao total do representante: R$ " + dinheiro(totalComissaoRepresentante));
        System.out.println();
    }

    private void imprimirSeparador() {
        int[] larguras = {6, 9, 9, 22, 7, 12, 13, 7, 12};
        StringBuilder linha = new StringBuilder();
        for (int largura : larguras) {
            linha.append('+');
            for (int i = 0; i < largura + 2; i++) linha.append('-');
        }
        linha.append('+');
        System.out.println(linha);
    }

    private String dinheiro(double valor) {
        return String.format(Locale.US, "%.2f", valor);
    }
}
