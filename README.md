# Gestão de Comissões

Aplicação de console em Java para consultar pedidos de um vendedor e calcular as comissões de vendedor e representante. O projeto foi desenvolvido como exercício de Programação Orientada a Objetos.

## Funcionalidades

- Consulta os pedidos associados ao ID de um vendedor.
- Exibe os itens de cada pedido, o produto, a categoria, o percentual aplicado e os valores de venda e comissão.
- Resume o valor dos pedidos ativos e desfeitos.
- Mantém pedidos desfeitos visíveis no relatório, sem atribuir comissão a eles.
- Lê os dados de arquivos de texto separados por ponto e vírgula.
- Usa herança e polimorfismo com a classe abstrata `Pessoa` e as classes `Vendedor` e `Representante`.

## Requisitos

- JDK 8 ou superior (JDK 17 ou 21 recomendado).
- Para executar pelo VS Code, instale a extensão **Extension Pack for Java**.

## Abrir no VS Code

1. Extraia o ZIP.
2. Abra no VS Code a pasta `GestaoDeComissoes`, que contém `src` e `.vscode`.
3. Em **Run and Debug**, escolha `Gestao de Comissoes - Relatorio`.

## Compilar e executar pelo terminal

Execute os comandos a partir da pasta raiz do projeto.

Linux/macOS/Git Bash:

```bash
mkdir -p out
javac -encoding UTF-8 -d out src/br/edu/ifsp/xyz/leitor/*.java
java -Dfile.encoding=UTF-8 -cp out br.edu.ifsp.xyz.leitor.ComissaoTeste
```

Windows PowerShell:

```powershell
New-Item -ItemType Directory -Force out | Out-Null
javac -encoding UTF-8 -d out src/br/edu/ifsp/xyz/leitor/*.java
java -Dfile.encoding=UTF-8 -cp out br.edu.ifsp.xyz.leitor.ComissaoTeste
```

Por padrão, o relatório consulta o vendedor de ID `101`. Para consultar outro vendedor, informe o ID como argumento. Exemplo:

```bash
java -Dfile.encoding=UTF-8 -cp out br.edu.ifsp.xyz.leitor.ComissaoTeste 102
```

## Arquivos de dados

Os arquivos ficam em `src/br/edu/ifsp/xyz/leitor` e usam ponto e vírgula (`;`) como separador:

- `Vendedor.txt`: ID, CPF e nome.
- `Produto.txt`: ID, código, preço e ID da categoria.
- `Categoria.txt`: ID, nome e percentuais de comissão do vendedor e do representante.
- `Pedido.txt`: ID, ID do vendedor, valor registrado e indicador de pedido desfeito (`true` ou `false`).
- `ItemPedido.txt`: ID do item, ID do pedido, ID do produto e quantidade.

Ao editar os arquivos, preserve a ordem das colunas e os cabeçalhos. Os registros incluídos são dados fictícios para fins de estudo; os CPFs presentes não devem ser tratados como dados reais validados.

## Conceitos de POO praticados

- **Encapsulamento:** atributos privados e métodos de acesso.
- **Herança:** `Vendedor` e `Representante` estendem `Pessoa`.
- **Abstração:** `Pessoa` define o método abstrato `calcularComissao`.
- **Polimorfismo:** as subclasses implementam `calcularComissao`.
- **Sobrecarga:** algumas classes oferecem construtores com diferentes parâmetros.

## Estrutura principal

```text
GestaoDeComissoes/
├── .vscode/
├── src/br/edu/ifsp/xyz/leitor/
│   ├── *.java
│   └── *.txt
├── .gitignore
└── README.md
```
