# Gestão de Vagas - API RESTful

[![Java](https://img.shields.io/badge/Java-17-orange)](#)
[![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.0-brightgreen)](#)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-15-blue)](#)
[![Docker](https://img.shields.io/badge/Docker-Ready-2496ED)](#)
[![Spring Security](https://img.shields.io/badge/Security-JWT-red)](#)

## Sobre o Projeto

A **Gestão de Vagas** é uma API RESTful completa desenvolvida para gerenciar processos de recrutamento e seleção. O
sistema atua como uma ponte segura entre **Empresas** (que publicam vagas) e **Candidatos** (que buscam oportunidades),
garantindo que cada perfil tenha acesso estrito apenas às suas próprias funcionalidades e dados.

Este projeto foi construído com foco em **Arquitetura Limpa**, **Segurança de Nível Corporativo** e **Sustentabilidade
do Código**.

## Arquitetura e Padrões Aplicados

Para garantir que a aplicação seja escalável e fácil de manter, o sistema foge do padrão comum de serviços inflados e
adota a separação por **Casos de Uso (Use Cases)**. Cada regra de negócio tem a sua própria classe, respeitando o
Princípio da Responsabilidade Única (**SRP**) do SOLID.

### Destaques Técnicos:

- **Autenticação Stateless com JWT**: Geração e validação de tokens usando HMAC256 através da biblioteca Auth0.
- **Role-Based Access Control (RBAC)**: Filtros customizados e anotações de segurança garantindo que rotas sensíveis
  sejam acessadas apenas pelas roles adequadas (`COMPANY` ou `CANDIDATE`).
- **Tratamento Global de Exceções**: Uso de `ControllerAdvice` para encapsular erros internos e devolver mensagens de
  erro (400, 401, 403, 404) formatadas e seguras.
- **Validação de Dados**: Proteção nas bordas dos Controllers usando anotações do Jakarta Validation.
- **Mapeamento de Dados (DTOs)**: Separação estrita entre Entidades de Banco de Dados e Objetos de Transferência (
  Records e Classes), prevenindo vulnerabilidades de exposição indevida de dados.

## Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3**
- **Spring Security**
- **Auth0 java-jwt**
- **Spring Data JPA**
- **PostgreSQL**
- **Docker e Docker Compose**
- **Lombok**
- **Swagger (OpenAPI 3)**

## Como Executar o Projeto Localmente

A infraestrutura do banco de dados está containerizada para facilitar a execução em qualquer ambiente de
desenvolvimento.

### Pré-requisitos

- Java 17+ instalado
- Docker e Docker Compose instalados

### Passo a Passo

1. **Clone o repositório:**

```bash
git clone [https://github.com/seu-usuario/gestao-de-vagas.git](https://github.com/seu-usuario/gestao-de-vagas.git)
cd gestao-de-vagas

2. Suba o Banco de Dados (PostgreSQL) via Docker:

Utilize o comando abaixo para iniciar o contêiner do banco de dados definido no arquivo docker-compose.yml. Isso garantirá que o ambiente de dados esteja pronto para a aplicação.

```bash
docker-compose up -d
O banco de dados estará rodando na porta 5432. As credenciais e o nome do banco seguem as definições configuradas no arquivo application.properties da aplicação.

Execute a Aplicação Spring Boot:

Com o banco de dados ativo, você pode iniciar o servidor da API utilizando o Maven Wrapper incluído no projeto:

Bash
./mvnw spring-boot:run
A aplicação será iniciada e estará disponível para receber requisições no endereço: http://localhost:8080

Documentação da API (Swagger)
O projeto utiliza o OpenAPI 3 (Swagger) para fornecer uma interface interativa de testes e documentação de todos os endpoints disponíveis.

Para acessar a documentação, utilize o link abaixo com a aplicação em execução:

Swagger UI: http://localhost:8080/swagger-ui/index.html

Instruções para Testes de Rotas Protegidas no Swagger:
Acesse os endpoints de autenticação: Realize uma requisição POST em /company/auth (para empresas) ou /candidate/auth (para candidatos) com credenciais válidas.

Obtenha o Token: Copie o valor do campo access_token retornado no JSON.

Autorize no Swagger: Clique no botão "Authorize" localizado no topo da página do Swagger UI.

Formate o Token: No campo de valor, digite a palavra Bearer seguida de um espaço e cole o token (Exemplo: Bearer eyJhbGci...).

Confirme: Clique em "Authorize" e depois em "Close". Agora as rotas protegidas por @PreAuthorize estarão liberadas para teste na sessão.

Arquitetura e Estrutura
O projeto foi estruturado seguindo os princípios de Separação de Preocupações e Clean Architecture, dividindo-se em:

Providers: Classes responsáveis por serviços externos e ferramentas de suporte, como a validação e geração de tokens JWT.

Use Cases: Classes que contêm a lógica de negócio central de forma isolada, facilitando testes e manutenção.

Security: Filtros e configurações que interceptam as requisições para validar a identidade e as permissões do usuário.

Exception Handling: Controlador global que padroniza as respostas de erro da API.

Autor
Desenvolvido por Hugo Trevizan.
