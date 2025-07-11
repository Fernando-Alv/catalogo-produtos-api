# 📦 Catálogo de Produtos API

API REST em Java com Spring Boot para gerenciamento de produtos e usuários, com autenticação via JWT e controle de acesso por permissões (`ADMIN` e `USER`).

## ✅ Funcionalidades

- 🔐 Autenticação via token JWT
- 👤 Cadastro e gestão de usuários (apenas ADMIN pode consultar, editar ou remover)
- 📦 CRUD de produtos (apenas ADMIN pode criar, editar ou remover)
- 🔎 Listagem pública de produtos para usuários autenticados
- 👮 Controle de acesso baseado em roles
- ⚠️ Tratamento global de exceções

---

## 🚀 Tecnologias utilizadas

- Java 21
- Spring Boot 3.5.0
- Spring Security
- Spring Data JPA
- MapStruct
- Lombok
- MySQL
- JWT (Algorithm.HMAC256)
- Maven

---

## ⚙️ Configuração

### Pré-requisitos

- Java 21+
- Maven
- MySQL

---

### Configuração do banco e token (arquivo `.env` ou variáveis de ambiente)

```env
DB_URL=jdbc:mysql://localhost:3306/seu_banco
DB_USERNAME=seu_usuario
DB_PASSWORD=sua_senha
JWT_MY_SECRET=seu_segredo
```
---

## 🛠️ Estrutura do projeto

```plaintext
src/
├── config           # Configurações de segurança
├── controller       # Controladores REST
├── dto              # Data Transfer Objects
├── entity           # Entidades JPA
├── enums            # Enums do sistema
├── exception        # Exceções personalizadas
├── mapper           # MapStruct mappers
├── repository       # Repositórios Spring Data JPA
├── service          # Regras de negócio
└── resources/
    └── application.properties
```
---

### ⚠️ Criação manual do usuário ADMIN

O usuário com perfil `ADMIN` deve ser criado manualmente direto na tabela do banco de dados, pois não há endpoint para cadastro de administradores pela API.
Você pode alterar os dados depois de criado.

Exemplo de inserção SQL para criar um usuário ADMIN com senha 12345 (hash BCrypt):

```sql
INSERT INTO tb_usuarios (email, name, password, role) VALUES
('admin@admin', 'admin', '$2a$10$TCSHdO1n6JTixCn5310chubjbLCDlr/aWvQJtHj0esK7wSZnTvOMC', 'ADMIN');
```
---

## 📂 Endpoints principais

| Método | Endpoint         | Permissão   | Descrição               |
|--------|------------------|-------------|-------------------------|
| POST   | /users           | Pública     | Criação de usuário      |
| GET    | /users           | ADMIN       | Listar todos os usuários|
| PUT    | /users/{id}      | ADMIN       | Atualizar usuário       |
| DELETE | /users/{id}      | ADMIN       | Remover usuário         |
| POST   | /auth            | Pública     | Autenticação (login)    |
| GET    | /products        | USER/ADMIN  | Listar produtos         |
| POST   | /products        | ADMIN       | Cadastrar produto       |
| PUT    | /products/{id}   | ADMIN       | Atualizar produto       |
| DELETE | /products/{id}   | ADMIN       | Deletar produto         |

---

### Autenticação (Login) - Pública

#### Exemplo

```http
POST /auth
Content-Type: application/json

{
  "email": "admin@admin",
  "password": "12345"
}
Resposta esperada:

HTTP/1.1 200 OK
Content-Type: text/plain

eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...
```
---

## 📋 Exemplos de requisições - Usuários

### Criar usuário (público)

#### Exemplo 

```http
POST /users
Content-Type: application/json

{
  "email": "usuario1@example.com",
  "name": "Usuário Um",
  "password": "senha123"
}
```
---

### Consultar usuário por ID (somente ADMIN)

#### Exemplo

```http
GET /users/10
Authorization: Bearer {token_do_admin}
Resposta esperada:

{
  "id": 10,
  "email": "usuario1@example.com",
  "name": "Usuário Um",
  "role": "USER"
}
```
---

### Consultar todos usuários (somente ADMIN)

#### Exemplo

```http
GET /users
Authorization: Bearer {token_do_admin}
Resposta esperada:

[
  {
    "id": 7,
    "email": "admin@admin",
    "name": "admin",
    "role": "ADMIN"
  },
  {
    "id": 10,
    "email": "usuario1@example.com",
    "name": "Usuário Um",
    "role": "USER"
  }
]
```
---

## 📋 Exemplos de requisições - Produtos

### Cadastrar produto (somente ADMIN)

#### Exemplo

```http
POST /products
Authorization: Bearer {token_do_admin}
Content-Type: application/json

{
  "name": "Smartphone Samsung Galaxy S21",
  "price": 3499.99,
  "stock": 503,
  "department": "ELETRONICOS"
}
```
---

### Consultar produto por ID (ADMIN ou USER)

#### Exemplo

```http
GET /products/1
Authorization: Bearer {token_valido}
Resposta esperada:

{
  "id": 1,
  "name": "Smartphone Samsung Galaxy S21",
  "price": 3499.99,
  "stock": 503,
  "department": "ELETRONICOS"
}
```
---

### Consultar todos os produtos (ADMIN ou USER)

#### Exemplo

```http
GET /products
Authorization: Bearer {token_valido}
Resposta esperada:

[
  {
    "id": 1,
    "name": "Smartphone Samsung Galaxy S21",
    "price": 3499.99,
    "stock": 503,
    "department": "ELETRONICOS"
  },
  {
    "id": 2,
    "name": "Camiseta Básica",
    "price": 49.90,
    "stock": 120,
    "department": "ROUPAS"
  }
]
```
---

## 🧪 Testando a API

Você pode testar essa API com ferramentas como:

- [Postman](https://www.postman.com/)
- [Insomnia](https://insomnia.rest/)
