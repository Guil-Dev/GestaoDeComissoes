# Gestão de Comissões

Aplicação de consola em Java para consultar pedidos de um vendedor e calcular as comissões do vendedor e do representante. O projeto foi desenvolvido como exercício prático de Programação Orientada a Objetos (POO) e arquitetura de software em camadas.

---

## Funcionalidades
- Consulta os pedidos associados ao ID de um vendedor.
- Exibe os itens de cada pedido, o produto, a categoria, os percentuais aplicados e os valores de venda e comissão.
- Resume o valor total dos pedidos ativos e desfeitos.
- Mantém pedidos desfeitos visíveis no relatório, sem atribuir comissão a eles.
- Lê dados de ficheiros de texto (`.txt`) estruturados com separador ponto e vírgula (`;`).
- Aplica arquitetura limpa com separação entre modelos, serviços e testes.

---

## Requisitos
- **Java Development Kit (JDK):** Versão 8 ou superior (JDK 17 ou 21 recomendado).
- **IDE:** VS Code com a extensão **Extension Pack for Java** (opcional, para execução na IDE).

---

## Estrutura do Projeto

```text
GestaoDeComissoes/
├── dados/
│   ├── Categoria.txt
│   ├── ItemPedido.txt
│   ├── Pedido.txt
│   ├── Produto.txt
│   └── Vendedor.txt
├── src/br/edu/ifsp/xyz/
│   ├── model/
│   │   ├── Categoria.java
│   │   ├── Comissao.java
│   │   ├── ItemPedido.java
│   │   ├── Pedido.java
│   │   ├── Pessoa.java
│   │   ├── Produto.java
│   │   ├── Representante.java
│   │   └── Vendedor.java
│   ├── service/
│   │   ├── CaminhosDados.java
│   │   └── Leitor.java
│   └── teste/
│       ├── CategoriaTeste.java
│       ├── ComissaoTeste.java
│       ├── PedidoTeste.java
│       ├── ProdutoTeste.java
│       └── VendedorTeste.java
├── .gitignore
└── README.md

```

---

## Como Executar

### Pelo VS Code

1. Abra a pasta raiz `GestaoDeComissoes` no VS Code.
2. Navegue até à classe de teste desejada dentro de `src/br/edu/ifsp/xyz/teste/` (ex: `ComissaoTeste.java`).
3. Clique na opção **Run** exibida acima do método `main`.

---

### Pelo Terminal

Execute os comandos a partir da pasta raiz do projeto (`GestaoDeComissoes`).

#### Linux / macOS / Git Bash:

```bash
mkdir -p out
javac -encoding UTF-8 -d out src/br/edu/ifsp/xyz/*/*.java
java -Dfile.encoding=UTF-8 -cp out br.edu.ifsp.xyz.teste.ComissaoTeste

```

#### Windows PowerShell:

```powershell
New-Item -ItemType Directory -Force out | Out-Null
javac -encoding UTF-8 -d out (Get-ChildItem -Recurse src/*.java)
java -Dfile.encoding=UTF-8 -cp out br.edu.ifsp.xyz.teste.ComissaoTeste

```

> **Nota:** Por padrão, o relatório consulta o vendedor com ID `101`. Para consultar outro vendedor, passe o ID como argumento:
> ```bash
> java -Dfile.encoding=UTF-8 -cp out br.edu.ifsp.xyz.teste.ComissaoTeste 102
> 
> ```
> 
> 

---

## Ficheiros de Dados

Os ficheiros de dados ficam armazenados na pasta `dados/` na raiz do projeto e utilizam `;` como separador:

* `Vendedor.txt`: ID, CPF e nome.
* `Produto.txt`: ID, código, preço e ID da categoria.
* `Categoria.txt`: ID, nome e percentuais de comissão do vendedor e do representante.
* `Pedido.txt`: ID do pedido, ID do vendedor, valor registrado e indicador de pedido desfeito (`true` ou `false`).
* `ItemPedido.txt`: ID do item, ID do pedido, ID do produto e quantidade.

*Ao editar os ficheiros, preserve a ordem das colunas e os cabeçalhos. Os registos contêm dados fictícios para fins de estudo.*

---

## Conceitos de POO Praticados

* **Encapsulamento:** Atributos privados protegidos por métodos de acesso e construtores.
* **Herança:** As classes `Vendedor` e `Representante` estendem a classe abstrata `Pessoa`.
* **Abstração:** A classe `Pessoa` define o método abstrato `calcularComissao`.
* **Polimorfismo:** Sobrescrita e implementação específica do método `calcularComissao` nas subclasses.
* **Sobrecarga:** Construtores com diferentes assinaturas para flexibilidade de instanciação.
* **Arquitetura em Camadas:** Organização modular dividida em entidades de domínio (`model`), lógica de infraestrutura e leitura (`service`) e suíte de execução (`teste`).

```

---

Para atualizar no teu GitHub, basta substituir o conteúdo no ficheiro `README.md` do teu projeto, guardar (`Ctrl + S`) e executar no terminal:

```bash
git add README.md
git commit -m "docs: atualiza README com nova estrutura de pacotes e caminhos"
git push origin main

```