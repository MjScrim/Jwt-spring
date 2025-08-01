# Auth JWT Spring Boot

Sistema de autenticação com **JWT (JSON Web Token)** utilizando **Spring Boot**, com suporte a papéis de usuário (`USER`, `ADMIN`), proteção de rotas, registro, login, e documentação com Swagger.

## Tecnologias Utilizadas

- **Java 17+**
- **Spring Boot**
    - Spring Web
    - Spring Security
    - Spring Data JPA
- **H2 Database** 
- **JWT (jjwt)**
- **Lombok**
- **Swagger/OpenAPI** para documentação

## Funcionalidades

- Registro de usuários com e-mail, senha e papel (`USER` ou `ADMIN`)
- Login e geração de token JWT
- Proteção de rotas com autenticação JWT
- Suporte a roles no token
- Documentação da API com Swagger

## Estrutura das Rotas

### `POST /api/auth/register`
Cria um novo usuário.

**Body:**
```json
{
  "email": "exemplo@email.com",
  "password": "senha123",
  "role": "USER"
}
