# p2SistemaCRUD — Sistema de Login com CRUD (Java Swing + MySQL)

Projeto desenvolvido para a avaliação **P2** da disciplina **Desenvolvimento para Servidores **
Prof. Marcos, do curso de Sistemas para Internet da **FATEC São Roque**.
Ele segue as notas de aula, seção 32 ("Prática do começo ao final").

O sistema é uma aplicação desktop com interface gráfica que implementa um **CRUD completo**
(Create, Read, Update e Delete) de usuários. Ele aplica os conceitos de Orientação a Objetos
vistos em aula e se integra a um banco de dados MySQL.

---

## Funcionalidades

| Operação | Onde acontece | O que faz |
|---|---|---|
| **Create** | Tela de Cadastro | Cadastra um usuário (nome, usuário, senha e confirmação), valida os campos e impede usuário duplicado |
| **Read** | Tela de Login e Tela Inicial | Valida usuário e senha no banco e exibe "Bem vindo" com o nome do usuário |
| **Update** | Tela de Alteração | Altera o nome e a senha do usuário, exigindo a senha atual |
| **Delete** | Tela Inicial (botão Excluir) | Exclui o usuário logado, após confirmação (Sim/Não) |

A senha é gravada no banco criptografada com a função `MD5`.

---

## Tecnologias

- Java 21 (JDK)
- Maven
- Java Swing (`JFrame`, `JPanel`, `JLabel`, `JTextField`, `JPasswordField`, `JButton`, `JOptionPane`)
- JDBC com MySQL Connector/J 8.0.33
- MySQL / MariaDB
- NetBeans

---

## Estrutura do projeto

```
p2SistemaCRUD
├── pom.xml
└── src/main/java
    ├── com/vanessarosa/p2sistemacrud
    │   ├── Conexao.java
    │   ├── Main.java
    │   ├── TelaAlteracao.java
    │   ├── TelaCadastro.java
    │   ├── TelaInicio.java
    │   ├── TelaLogin.java
    │   └── Usuario.java
    └── resouces
        └── P2_Login.sql
```

| Classe | Responsabilidade |
|---|---|
| `Main` | Ponto de entrada: abre a tela de login |
| `Conexao` | Abre e fecha a conexão com o banco de dados (`abrirConexao()` e `fecharConexao()`) |
| `Usuario` | Atributos privados, getters/setters e acesso ao banco: `verificaUsuario()` (com sobrecarga), `cadastraUsuario()`, `alteraUsuario()` e `excluiUsuario()` |
| `TelaLogin` | Tela de identificação, com os botões Entrar e Cadastrar |
| `TelaCadastro` | Tela de cadastro de novo usuário |
| `TelaInicio` | Tela inicial, com os botões Excluir, Alterar Dados e Voltar |
| `TelaAlteracao` | Tela de alteração de nome e senha |

---

## Banco de dados

O script de criação do banco está **salvo neste repositório**, no arquivo **`P2_Login.sql`**
(pasta `resouces`, dentro de `src/main/java`). Basta abri-lo no MySQL Workbench, DBeaver ou
outro cliente e executá-lo como script completo.

Conteúdo do script (cria o banco `bd_login` e a tabela `usuario`):

```sql
create database if not exists bd_login;

use bd_login;

create table if not exists usuario(
    usuario varchar(10) primary key,
    nome    varchar(30) default '',
    senha   char(32) default ''
);

insert ignore into usuario (usuario, nome, senha)
values ('Vanessa', 'Vanessa Rosa', md5('1234'));
```

- O usuário `Vanessa` (senha `1234`) é apenas um registro de teste.
- A coluna `senha` tem 32 caracteres porque o MD5 sempre gera um valor hexadecimal desse tamanho.
- A coluna `usuario` aceita no máximo 10 caracteres.

---

## Como executar

### Pré-requisitos

- JDK 21 instalado
- MySQL  em execução
- NetBeans (ou outra IDE com suporte a Maven)
- Internet na primeira execução, para o Maven baixar o driver do banco

### Passo a passo

**1. Crie o banco de dados**

Execute o arquivo `P2_Login.sql` no seu cliente de banco de dados, como script completo
(no DBeaver, use `Alt + X`), e não comando por comando.

**2. Confira a dependência do driver no `pom.xml`**

```xml
<dependencies>
    <dependency>
        <groupId>com.mysql</groupId>
        <artifactId>mysql-connector-j</artifactId>
        <version>8.0.33</version>
    </dependency>
</dependencies>
```

**3. Ajuste a conexão na classe `Conexao`**

```java
private final String servidor = "jdbc:mysql://localhost:3307/bd_login";
private final String usuario = "root";
private final String senha = "";
private final String driver = "com.mysql.cj.jdbc.Driver";
```

- O projeto está configurado para o banco na porta **3307**. A porta padrão do MySQL é 3306,
  então troque o número se o seu servidor usar outra.
- Ajuste `usuario` e `senha` conforme o seu servidor de banco de dados.

**4. Compile e execute**

No NetBeans, clique com o botão direito no projeto e escolha **Clean and Build**.
Depois execute a classe `Main` (ou pressione `F6`).

---

## Roteiro de uso

1. Na tela de **Login**, deixe os campos vazios e clique em Entrar para ver as validações.
2. Clique em **Cadastrar**, preencha os campos e confirme o cadastro.
3. Faça **login** com o usuário criado. Abre a tela inicial com "Bem vindo" e o nome do usuário.
4. Clique em **Alterar Dados**, informe a senha atual e os novos dados. O sistema volta ao login.
5. Entre novamente com a nova senha e confira que o nome foi alterado.
6. Clique em **Excluir** e confirme com "Sim". O sistema volta ao login.
7. Tente entrar com o usuário excluído: aparece "Usuário inválido ou inexistente".
8. No banco, confira o resultado com `SELECT * FROM bd_login.usuario;`.

---

## Solução de problemas

| Mensagem no console | Causa provável | O que fazer |
|---|---|---|
| `com.mysql.cj.jdbc.Driver` (ClassNotFoundException) | Driver do MySQL não está no projeto | Confira a dependência no `pom.xml` e rode **Clean and Build** |
| `Communications link failure` | Servidor desligado ou porta errada | Inicie o banco e confira a porta na URL da `Conexao` |
| `Unknown database 'bd_login'` | Banco não foi criado | Execute o script `P2_Login.sql` |
| `Access denied for user 'root'` | Senha incorreta | Coloque a senha do seu banco no atributo `senha` da `Conexao` |

---

## Conceitos de programação aplicados

- **Classes e objetos**, com atributos e métodos
- **Encapsulamento**: atributos privados com `getters` e `setters`
- **Método construtor** nas telas
- **Sobrecarga de métodos**: `verificaUsuario(usuario, senha)` e `verificaUsuario(usuario)`
- **Atributos estáticos** (`nomeUsuario` e `usuarioSistema`) para compartilhar o usuário logado entre as telas
- **Tratamento de exceções** com `try/catch`
- **Eventos** com `ActionListener` nos botões
- **Integração com banco de dados** via JDBC

---

## Observações

- O sistema foi feito para fins didáticos.

---

## Autor

- **Nome:** _Vanessa Aparecida Rosa da Silva_
- **Curso:** Sistemas para Internet 4° Semestre — FATEC São Roque
- **Disciplina:** Desenvolvimento para Servidores  — Prof. Marcos 
- **Data de entrega:** até 12/11/2026
