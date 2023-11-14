# PalindromeHuntChallenge

## Descrição

A Palindrome Hunt Challenge (desafio do caça-palindromo) é uma API desenvolvida como parte de um desafio proposto para
uma vaga de Engenheiro de Software. A proposta da API é realizar a caça por palíndromos em matrizes.

## Funcionalidades

- **Salvar Matriz**: A API permite receber matrizes de caracteres quadradas com no máximo 10 linhas e 10 colunas e
  salvá-las no banco de dados com os respectivos palídromos encontrados dentro delas.


- **Buscar matriz**: Depois que uma matriz é salva, é possível buscar todas as matrizes presentes no banco de dados
  juntamente com seus respectivos palindromos, assim como, é possível buscal individualmente uma matriz através do seu
  ID único universal.


- **Buscar palindromo**: Uma vez que um palindromo é encontrado dentro de uma matriz, ele também é persistido no
  banco dedados, então, conseguimos realizar a busca de todos os palidromos já salvos, assim como realizar a
  busca individual de um palindromor através do seu ID único universal.

## Tecnologias Utilizadas

- **Spring Boot**: Utilizado para o desenvolvimento da aplicação.
- **Spring Data JPA**: Utilizado para integração com o banco de dados.
- **Lombok**: Reduz a verbosidade do código Java.
- **Gson**: Biblioteca para manipulação de objetos JSON.
- **H2 Database**: Banco de dados em memória.
- **Logstash Logback Encoder**: Utilizado para formatar logs no padrão Logstash.
- **JUnit**: Framework de testes.
- **Springdoc OpenAPI UI**: Ferramenta para visualização e interação com a documentação OpenAPI da API.

## Instruções de Uso

### Pré-requisitos

- Java 17
- Maven
- Docker (opcional)

### Configuração e Inicialização

1. Clone o repositório: `git clone https://github.com/igorfjales/palindromos-Igor_Jales.git`
2. Acesse o diretório do projeto: `cd PalindromeHuntChallenge`
3. Baixe as dependências do projeto: `mvn clean install`

### Execução da aplicação e observações importantes sobre credenciais

Essencialmente, para rodar uma aplicação Spring Boot, só precisaria usar o comando `mvn spring-boot:run`. No entanto, no
nosso caso, como se trata de uma aplicação que possui um banco de dados em memória somente para a exemplificação do
desafio, precisamos passar as credenciais de banco de dados como argumentos no comando de inicialização.

Isso acontece porque o objetivo foi simular um ambiente mais seguro e controlado, como acontece no ambiente corporativo,
em que as credenciais não podem estar diretamente cravadas num arquivo de configuração e expostas nos repositórios de
código porque são informações sensíveis.

Geralmente, informações de credenciais são armazenadas num serviço de armazenamento seguro para senhas, chaves
de API e outros segredos, como o Vault, são transitadas via password pushers, são configuradas via variáveis de
ambiente, ou estão presentes em arquivos locais, como por exemplo um ".env".

Como o meu objetivo era não expor as credenciais num arquivo de configuração, estou simulando um cenário em que estas
credenciais são transitadas de forma segura para este projeto de exemplo.

No meu ambiente de desenvolvimento utilizando a IDE Intellij, eu consigo configurar variáveis de ambiente (mostradas
abaixo) e rodar o projeto através da interface gráfica, clicando no botão "play", ou, clicando com o botão direito na
classe PalindromeHuntChallengeApplication.java, que por sua vez possui o método main, e depois clicar em "Run".

```
DATASOURCE_URL=jdbc:h2:mem:testdb%3BDB_CLOSE_DELAY=-1%3BDB_CLOSE_ON_EXIT=FALSE;
DATASOURCE_PASSWORD=password;
DATASOURCE_USERNAME=sa
```

Para não ter a necessidade de configuração de variáveis de ambiente, você simplesmente pode copiar o comando abaixo, que
passa as variáveis de ambiente como argumentos e executar no terminal dentro do diretório da aplicação, que o programa
será executado corretamente já com a conexão com o banco de dados configurada.

```bash
mvn clean spring-boot:run -Dspring-boot.run.arguments="--DATASOURCE_URL=jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE --DATASOURCE_PASSWORD=password --DATASOURCE_USERNAME=sa"
```

### Docker

Se preferir, a aplicação pode ser executada em um contêiner Docker:

#### Comando para Construir a Imagem e Executar o Contêiner:

```bash
docker build -t palindrome-hunt-challenge:latest . && docker run -e DATASOURCE_PASSWORD=password -e DATASOURCE_URL="jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE" -e DATASOURCE_USERNAME=sa -p 8080:8080 palindrome-hunt-challenge:latest
```

- **Observações**:
    - O terminal ficará "preso", mostrando a visualização dos logs da aplicação, o que é útil para saber se a aplicação
      está rodando como o esperado. Mas se preferir rodar e não ficar preso no terminal, adicione a flag
      -d ao lado do comando docker run.
    - Acesse a API em [http://localhost:8080](http://localhost:8080).

Vale ressaltar que as credenciais expostas aqui têm um propósito exclusivo de exemplificação e não devem ser utilizadas
em um ambiente produtivo. Em um ambiente real, a gestão de credenciais é tratada de maneira segura e não são expostas
publicamente.

### Documentação da API

Após iniciar a aplicação, a documentação da API estará disponível
em [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html).

Para iniciar a aplicação, siga as instruções na seção anterior.

### Testes

Os testes podem ser executados com o comando: `mvn test`

### Relatório de Cobertura de Testes

Use o comando Maven `mvn clean verify`. Este comando realiza várias etapas, incluindo a compilação do projeto, a
execução de testes e a verificação da cobertura de código. Siga as etapas abaixo:

1. Execute o seguinte comando para realizar a limpeza do projeto, compilar o código-fonte, executar os testes e
   verificar a cobertura:
   ```bash
   mvn clean verify
   ```

2. Após a conclusão do comando, você poderá visualizar os resultados da cobertura de código no relatório gerado. Os relatórios geralmente são encontrados no diretório `target/site/jacoco/index.html`. Abra esse arquivo em um navegador para analisar detalhadamente a cobertura de código.

