# Agregador de Investimentos — API REST com Spring Boot

API REST desenvolvida em **Java + Spring Boot**, utilizando arquitetura em camadas (**Controller → Service → Repository**), persistência com **Spring Data JPA + Hibernate**, e banco de dados **MySQL**.  
O projeto implementa cadastro e consulta de usuários utilizando **UUID como identificador único**.

---

## Tecnologias Utilizadas

- Java 17+
- Spring Boot
- Spring Web
- Spring Data JPA
- Hibernate
- MySQL
- Maven
- Insomnia (para testar a API)

---

## Arquitetura do Projeto

O projeto segue o padrão de arquitetura:
Controller → Service → Repository → Database

### **Controller**
Recebe requisições HTTP e retorna respostas apropriadas (200, 201, 404).

### **Service**
Contém regras de negócio e conversão DTO → Entity.

### **Repository**
Interface que comunica com o banco usando Spring Data JPA.

### **Entity**
Representa a tabela do banco (`tb_users`).

---

## 🧱 Estrutura de Pastas

src/main/java/tech/buildrun/agregadorinvestimentos
│
├── Controller
│ ├── UserController.java
│ └── CreateUserDto.java
│
├── Entity
│ └── User.java
│
├── Repository
│ └── UserRepository.java
│
└── Service
└── UserService.java


---

## 🧪 Testando a API (Insomnia/Postman)

### 📌 **1. Criar usuário — POST**

**URL:**  POST /v1/users

**Body (JSON):**
```json
{
  "username": "Lara",
  "email": "lara@gmail.com",
  "password": "1234"
}

Resposta esperada:
201 Created
Location: /v1/users/{id}

---

Buscar usuário por ID — GET
URL: GET /v1/users/{userId}

Exemplo: GET /v1/users/7fbb941d-1231-4a10-a0cd-992af4b5c341

Respostas possíveis:
200 OK
{
  "id": "uuid-gerado",
  "username": "Lara",
  "email": "lara@gmail.com",
  "password": "1234",
  "creationTimestamp": "...",
  "updateTimestamp": "..."
}

404 Not Found
Usuário não existe.

---

Configuração do MySQL

No arquivo application.properties:

spring.datasource.url=jdbc:mysql://localhost:3306/agregador?useSSL=false
spring.datasource.username=SEU_USUARIO
spring.datasource.password=SUA_SENHA

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

Modelo da Entidade User
@Entity
@Table(name = "tb_users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String username;
    private String email;
    private String password;

    @CreationTimestamp
    private Instant creationTimestamp;

    @UpdateTimestamp
    private Instant updateTimestamp;
}

O que aprendi neste projeto:

--> Como funciona o Hibernate e o JPA
--> Diferença entre DTO e Entity
--> Como transformar DTO → Entity
--> Por que deixar o id = null ao criar entidades
--> Como gerar respostas HTTP adequadas (201, 200, 404)
--> Como testar API no Insomnia

Próximos Passos:
--> Criar endpoint para listar todos os usuários
--> Criar UPDATE (PUT/PATCH)
--> Criar DELETE
--> Criar DTO de retorno (Response DTO) para não expor senha
--> Implementar validação com Spring Validation
--> Implementar camadas para investimentos

Autora

Lara Kanashiro
Desenvolvedora Java em formação 
