# Encurtador de URL

> [Read this README in English!](README_en.md)

## Descrição
Este projeto é um serviço de encurtamento de URLs desenvolvido em Java utilizando Spring Boot. Ele permite criar URLs curtas que redirecionam para endereços longos, além de fornecer estatísticas de acesso e gerenciamento das URLs encurtadas.
baseado no https://roadmap.sh/projects/url-shortening-service

## Funcionalidades
- Criar uma URL curta a partir de uma URL longa
- Redirecionar para a URL original usando o código curto
- Consultar informações e estatísticas de uma URL encurtada
- Atualizar a URL original de um código curto existente
- Remover uma URL encurtada

## Estrutura do Projeto
- **Controller:** expõe a API REST para manipulação das URLs
- **Service:** contém a lógica de negócio para encurtamento, redirecionamento e estatísticas
- **Entity:** define o modelo de dados da URL
- **DTOs:** objetos de transferência de dados para requisições e respostas
- **Repository:** acesso ao banco de dados

## Endpoints da API

- `POST /shorten` — Cria uma nova URL curta
  - Corpo: `{ "url": "https://exemplo.com" }`
  - Resposta: dados da URL encurtada

- `GET /shorten/{code}` — Consulta informações de uma URL encurtada
- `PUT /shorten/{code}` — Atualiza a URL original de um código curto
  - Corpo: `{ "url": "https://novaurl.com" }`
- `DELETE /shorten/{code}` — Remove uma URL encurtada
- `GET /shorten/{code}/stats` — Consulta estatísticas de acesso
- `GET /shorten/{code}/redirect` — Redireciona para a URL original

## Modelo de Dados

### Entidade `Url`
- `id`: identificador único
- `url`: URL original
- `shortCode`: código curto gerado
- `createdAt`: data de criação
- `updatedAt`: data da última atualização
- `accessCount`: número de acessos

### DTOs
- **UrlRequest:** `{ "url": "https://exemplo.com" }`
- **UrlResponse:** `{ "id": 1, "url": "https://exemplo.com", "shortCode": "abc123", "createdAt": "2024-07-01T12:00:00Z", "updatedAt": "2024-07-01T12:00:00Z", "accessCount": 10 }`

## Tecnologias Utilizadas
- Java 17
- Spring Boot 3
- Spring Data JPA
- H2 Database (padrão, em memória)
- MySQL (opcional)
- Lombok
- Maven

## Configuração e Execução

1. **Pré-requisitos:**
   - Java 17+
   - Maven

2. **Configuração do Banco de Dados:**
   - Por padrão, utiliza H2 em memória. Para usar MySQL, ajuste o `application.properties`.

3. **Executando o Projeto:**
   - Via terminal: `./mvnw spring-boot:run` (Linux/Mac) ou `mvnw.cmd spring-boot:run` (Windows)

4. **Acessando o Console H2:**
   - Disponível em `/h2` durante a execução

## Testes
- Os testes estão localizados em `src/test/java/com/nullifidianz/urlShortener/`


