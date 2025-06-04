# API de Licitações Públicas

Este projeto é uma API REST desenvolvida em Java com Spring Boot para capturar e disponibilizar informações de licitações públicas do site oficial [ComprasNet](http://comprasnet.gov.br/ConsultaLicitacoes/ConsLicitacaoDia.asp).

## 🧾 Funcionalidades

- Captura licitações públicas e seus respectivos itens via web scraping.
- Armazena os dados capturados em arquivo JSON local.
- Disponibiliza os dados por meio de endpoints REST.
  
## 🛠 Tecnologias utilizadas

- Java 17+
- Spring Boot
- Jsoup (para captura dos dados via scraping)
- Persistência em arquivo JSON
- Maven

## 🚀 Como executar localmente

### Pré-requisitos

- Java 17 ou superior
- Maven
- Git

### Clone o repositório

```bash
git clone https://github.com/anecrispim/api-spring-boot.git
cd api-spring-boot
```

### Compile o projeto

```bash
./mvnw clean install
```

### Execute a aplicação

```bash
./mvnw spring-boot:run
```

### Outra forma
Pode ser executado através de alguma IDE também, exemplo IntelliJ

## Documentação da API
Após rodar o projeto é possível acessar a documentação da API pelo link: 

```bash
http://localhost:8080/swagger-ui/index.html
```

## 🛠 Decisões técnicas

- **Java + Spring Boot**: framework robusto e amplamente utilizado para desenvolvimento de APIs REST.
- **Arquitetura RESTful**: para facilitar a integração e o consumo da API.
- **Persistência em arquivo JSON**: solução simples para armazenamento local sem necessidade de banco de dados.
- **Scraping com fonte oficial ComprasNet**: coleta dos dados diretamente do site oficial para garantir dados atualizados.
- **Documentação com Springdoc OpenAPI (Swagger)**: para facilitar o entendimento e teste dos endpoints.
- **Injeção de dependências e serviços**: separação clara de responsabilidades para facilitar manutenção e testes.

---

## 🧪 Como executar os testes automatizados

1. Para rodar os testes automatizados da aplicação, certifique-se que o Maven está instalado.

2. Execute o comando na raiz do projeto:

```bash
mvn test
```
ou é possível rodar diretamente pela IDE, a exemplo o IntelliJ
