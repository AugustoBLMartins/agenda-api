# 📇 Agenda API

API REST para gerenciamento de contatos desenvolvida com **Java** e **Spring Boot**. O projeto foca em boas práticas de desenvolvimento, como o uso de **DTOs**, **Mappers**, **Testes Unitários** e validações de regras de negócio.

---

## 🛠️ Tecnologias Utilizadas

* **Java 17** (ou superior)
* **Spring Boot 3.x**
* **Spring Data JPA** (Persistência de dados)
* **H2 Database** (Banco de dados em memória para testes e desenvolvimento)
* **JUnit 5 & Mockito** (Testes unitários e mocks)
* **Lombok** (Produtividade e código limpo)
* **Maven** (Gerenciador de dependências)

---

## 🔄 Fluxo da Informação (Arquitetura)

```mermaid
graph LR
    A[Cliente/Postman] -->|JSON/DTO| B(Controller)
    B -->|DTO| C{Service}
    C -->|Validação/Regra| D[Repository]
    D -->|Entity| E[(H2 Database)]
    E -->|Entity| D
    D -->|Entity| C
    C -->|Conversão DTO| B
    B -->|JSON/DTO| A

## 🚀 Como Rodar o Projeto

### 1. Pré-requisitos
Certifique-se de ter instalado:
* [JDK 17+](https://www.oracle.com/java/technologies/downloads/)
* [Git](https://git-scm.com/)

### 2. Configuração do Ambiente
1. Clone o repositório:
   ```bash
   git clone [https://github.com/SEU_USUARIO/agenda-api.git](https://github.com/SEU_USUARIO/agenda-api.git)

2. Entre na pasta do projeto:

cd agenda-api

3. Compile e rode o projeto usando o Maven Wrapper:

./mvnw spring-boot:run

A API estará disponível em http://localhost:8080.

### 🗄️ Configuração do Banco de Dados (H2)
O projeto está configurado para usar o banco de dados H2, que roda em memória. Para acessar o console do banco e visualizar as tabelas, utilize as configurações abaixo no seu arquivo src/main/resources/application.properties:

# H2 Console
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console

# Datasource
spring.datasource.url=jdbc:h2:mem:agendadb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.hibernate.ddl-auto=update

Acesso ao Console: Após rodar a aplicação, acesse http://localhost:8080/h2-console. No campo JDBC URL, coloque jdbc:h2:mem:agendadb.

### 🧪 Testes
Para garantir a qualidade, o projeto conta com testes unitários na camada de Service. Para rodar todos os testes, execute:

./mvnw test

### 📌 Diferenciais Implementados
Validação de CPF: Impede o cadastro de contatos com o mesmo CPF.

Conventional Commits: Histórico de commits organizado e padronizado.

Tratamento de Exceções: Retornos claros para erros de ID não encontrado ou duplicidade.

### 🔍 Verificação do `pom.xml` (Para não dar erro)

Para o H2 e os Testes funcionarem, garanta que essas dependências estejam no seu `pom.xml`:

```xml
<dependency>
    <groupId>com.h2database</groupId>
    <artifactId>h2</artifactId>
    <scope>runtime</scope>
</dependency>

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-test</artifactId>
    <scope>test</scope>
</dependency>

