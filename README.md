# Auth API — Spring Boot + JWT

<p align="center">
  <img src="https://img.shields.io/badge/Java-21-orange?style=for-the-badge&logo=openjdk&logoColor=white"/>
  <img src="https://img.shields.io/badge/Spring%20Boot-4.0.3-brightgreen?style=for-the-badge&logo=springboot&logoColor=white"/>
  <img src="https://img.shields.io/badge/Spring%20Security-JWT-blue?style=for-the-badge&logo=springsecurity&logoColor=white"/>
  <img src="https://img.shields.io/badge/PostgreSQL-Database-4169E1?style=for-the-badge&logo=postgresql&logoColor=white"/>
  <img src="https://img.shields.io/badge/Docker-Container-2496ED?style=for-the-badge&logo=docker&logoColor=white"/>
  <img src="https://img.shields.io/badge/License-MIT-green?style=for-the-badge"/>
</p>

<p align="center">
  <b>API REST de autenticação e autorização robusta, segura e pronta para produção, desenvolvida com Java 21 e Spring Boot.</b>
</p>

---

## Sobre o Projeto

Esta API implementa um sistema completo de **autenticação stateless com JWT**, cobrindo todo o ciclo de vida do usuário: cadastro com confirmação por e-mail, login com geração de token, proteção de rotas e renovação de sessão.

O projeto foi desenvolvido com foco em **segurança real**, **boas práticas de arquitetura** e **código limpo** — aplicando padrões utilizados no mercado de trabalho backend.

---

##  Funcionalidades

-  Cadastro de usuários com validação de dados
-  **Confirmação de conta via e-mail** (token enviado ao registrar)
-  Login com geração de **token JWT**
-  **Refresh token** para renovar sessões sem novo login
-  Proteção de rotas autenticadas com Spring Security
-  Expiração e invalidação de tokens
-  Tratamento global de exceções personalizado
-  Documentação interativa com **Swagger UI**
-  Ambiente containerizado com **Docker**

---

##  Tecnologias Utilizadas

| Tecnologia | Versão | Finalidade |
|---|---|---|
| Java | 21 | Linguagem principal |
| Spring Boot | 4.0.3 | Framework base |
| Spring Security | — | Autenticação e autorização |
| Spring Data JPA | — | Persistência de dados |
| JWT | 0.12.6 | Geração e validação de tokens JWT |
| PostgreSQL | — | Banco de dados relacional |
| Spring Mail | — | Envio de e-mails (confirmação / recuperação) |
| SpringDoc OpenAPI | 2.5.0 | Documentação Swagger automática |
| Bean Validation | — | Validação de entrada de dados |
| Docker | — | Containerização da aplicação |
| Maven | — | Gerenciamento de dependências |

---

##  Arquitetura

```
src/
├── controller/       # Endpoints REST (camada de entrada)
├── service/          # Regras de negócio
├── repository/       # Acesso ao banco de dados
├── entity/           # Entidades JPA
├── dto/              # Objetos de transferência de dados
├── security/         # Configuração JWT e Spring Security Filter Chain
└── exception/        # Exceções customizadas e @ControllerAdvice
```

```
Controller → Service → Repository → Database
                ↕
       Security (JWT Filter Chain)
                ↕
          E-mail Service (SMTP)
```

---

##  Como Executar

###  Com Docker (recomendado)

```bash
# 1. Clonar o repositório
git clone https://github.com/LeonardoBasseto/springboot-backend-api

# 2. Entrar na pasta do projeto
cd springboot-backend-api

# 3. Configurar as variáveis de ambiente (veja a seção abaixo)

# 4. Subir os containers
docker compose up --build
```

###  Sem Docker

**Pré-requisitos:** Java 21+, Maven, PostgreSQL

```bash
git clone https://github.com/LeonardoBasseto/springboot-backend-api
cd springboot-backend-api
./mvnw spring-boot:run
```

A API estará disponível em: `http://localhost:8080`

---

##  Variáveis de Ambiente

Crie um arquivo `.env` ou configure o `application.properties`:

```properties
# Banco de dados
spring.datasource.url=jdbc:postgresql://localhost:5432/seu_banco
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha

# JWT
jwt.secret=sua_chave_secreta_aqui
jwt.expiration=86400000

# E-mail (SMTP)
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=seu_email@gmail.com
spring.mail.password=sua_senha_de_app
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
```

##  Endpoints

### 🔓 Autenticação (público)

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| `POST` | `/auth/register` | Cadastrar novo usuário (envia e-mail de confirmação) |
| `POST` | `/auth/login` | Autenticar e receber token JWT |
| `POST` | `/auth/refresh` | Renovar token JWT com refresh token |

### 🔒 Usuários (requer autenticação)

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| `GET` | `/users/me` | Retornar dados do usuário autenticado |
| `PUT` | `/users/me` | Atualizar dados do usuário |
| `DELETE` | `/users/me` | Deletar conta |

> **Como autenticar:** Após o login, copie o `token` retornado e inclua no header das requisições protegidas:
> ```
> Authorization: Bearer {seu_token_aqui}
> ```

---

##  Testando com Swagger UI

A forma mais rápida de explorar e testar a API é pelo Swagger, disponível após subir a aplicação:

```
http://localhost:8080/swagger-ui/index.html
```

**Passo a passo:**
1. Acesse o Swagger UI no navegador
2. Use o endpoint `/auth/register` para criar uma conta (um e-mail de confirmação será enviado)
3. Use o `/auth/login` para obter o token JWT
4. Clique em **"Authorize"** no topo da página e cole o token
5. Agora você pode testar todos os endpoints protegidos diretamente pela interface

---

##  Conceitos Aplicados

- Autenticação **stateless** com **JWT**
- Filtros de segurança com **Spring Security Filter Chain**
- Hash de senhas com **BCrypt**
- **Refresh Token** para renovação de sessão
- Confirmação de conta e recuperação de senha via **SMTP / Spring Mail**
- Arquitetura em camadas: Controller → Service → Repository
- Validação de entrada com **Bean Validation** (`@Valid`, `@NotBlank`, `@Email`)
- Mapeamento objeto-relacional com **JPA / Hibernate**
- Tratamento global de exceções com **@ControllerAdvice**
- Containerização com **Docker**
- Documentação automática com **Swagger / OpenAPI 3**

---

##  Autor

**Leonardo Basseto Rodrigues**

<p>
  <a href="https://www.linkedin.com/in/leonardo-basseto-rodrigues-a267b9284/">
    <img src="https://img.shields.io/badge/-LinkedIn-0077B5?style=flat-square&logo=linkedin&logoColor=white"/>
  </a>
  <a href="https://github.com/LeonardoBasseto">
    <img src="https://img.shields.io/badge/-GitHub-181717?style=flat-square&logo=github&logoColor=white"/>
  </a>
</p>
