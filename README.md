# 🐕 Canil API

API REST para gerenciamento de canil, desenvolvida com **Spring Boot** como evolução do projeto **Canil CLI** (Java puro). O objetivo é aplicar os mesmos conceitos de arquitetura em camadas, agora com persistência em banco de dados relacional e exposição via HTTP.

> Projeto em desenvolvimento ativo — parte da jornada de aprendizado FullStack Java.

---

## 📌 Sobre o projeto

O **Canil CLI** original era um sistema via terminal, com persistência em arquivo `.txt` e hierarquia de herança (`Animal` → `Cachorro`/`Gato`). O **Canil API** evolui essa base para:

- Persistência real em banco de dados (MySQL)
- Exposição de dados via endpoints REST
- Arquitetura em camadas profissional (Controller → Service → Repository)
- Boas práticas de mercado (DTOs, tratamento de erros, validações, testes)

---

## 🛠️ Tecnologias

| Tecnologia | Versão |
|---|---|
| Java | 21 (Azul JDK) |
| Spring Boot | 3.x |
| MySQL | 8.0 (via Docker) |
| Docker / Docker Compose | v2 |
| IntelliJ IDEA | — |
| DBeaver | 26.1.5 |

---

## 🏗️ Arquitetura

```
canil-api/
├── model/
│   └── Cachorro.java          → @Entity, UUID como ID
├── repository/
│   └── CachorroRepository.java → extends JpaRepository
├── service/
│   └── CachorroService.java   → regras de negócio
├── controller/
│   └── CachorroController.java → endpoints REST
├── docker-compose.yml
└── application.properties
```

---

## ✅ Status atual

### Feito

- [x] Projeto gerado via [start.spring.io](https://start.spring.io)
- [x] `docker-compose.yml` com MySQL 8.0
- [x] Container `canil-mysql` rodando
- [x] Conexão via DBeaver
- [x] `application.properties` configurado
- [x] `TesteController` com `GET /hello`
- [x] `Cachorro.java` com `@Entity` e UUID
- [x] `CachorroRepository extends JpaRepository`
- [x] Tabela `cachorros` criada automaticamente no banco
- [x] `CachorroService.java` com CRUD básico (`listarTodos`, `buscarPorId`, `salvar`, `deletar`)
- [x] `CachorroController.java` com endpoints REST sob `/api/cachorros`
- [x] Endpoints `GET` (listar/buscar por id), `POST`, `DELETE` implementados e testados no Postman
- [x] Tratamento de erro no `deletar`: verificação `existsById` antes de deletar, devolvendo `404` para id inexistente
- [x] Endpoint `PUT /api/cachorros/{id}` (atualizar cachorro existente), testado com id válido (200) e inválido (404)

### Módulo 1 concluído ✅

Próximo passo: iniciar o Módulo 2 (DTOs, tratamento de erros com `@ExceptionHandler`, validações).

---

## 🗺️ Roadmap

### Módulo 1 — CRUD básico
- [x] `CachorroService` (camada de regras de negócio)
- [x] `CachorroController` (endpoints REST)
- [x] `GET /api/cachorros`
- [x] `GET /api/cachorros/{id}`
- [x] `POST /api/cachorros`
- [x] `PUT /api/cachorros/{id}`
- [x] `DELETE /api/cachorros/{id}`
- [x] Testar `GET`, `POST`, `PUT`, `DELETE` no Postman (casos de sucesso e de erro)

### Módulo 2 — Boas práticas
- [ ] DTO (Data Transfer Object)
- [ ] `ResponseEntity` para controle de status HTTP
- [ ] Tratamento de erros com `@ExceptionHandler`
- [ ] Validações (`@NotNull`, `@Size`, `@Min`)

### Módulo 3 — Recursos avançados
- [ ] Relacionamentos JPA (`@ManyToOne`, `@OneToMany`)
- [ ] Queries customizadas (`@Query`, `findBy...`)
- [ ] Paginação (`Pageable`)
- [ ] Documentação automática com Swagger/OpenAPI

### Módulo 4 — Qualidade
- [ ] Testes unitários com JUnit 5
- [ ] Mock de dependências com Mockito
- [ ] Testes de integração

### Módulo 5 — Deploy
- [ ] `Dockerfile` da aplicação
- [ ] `docker-compose.yml` completo (app + banco)
- [ ] Deploy em Railway ou Render
- [ ] API acessível publicamente pela internet

---

## ⚙️ Como rodar o projeto localmente

### Pré-requisitos

- Java 21
- Docker e Docker Compose
- IntelliJ IDEA (ou outra IDE de sua preferência)

### Passos

```bash
# 1. Clonar o repositório
git clone https://github.com/jmauriciordelima/canil-api.git
cd canil-api

# 2. Subir o banco de dados MySQL
docker compose up -d

# 3. Rodar a aplicação Spring Boot
./mvnw spring-boot:run
```

A aplicação sobe por padrão na porta `8080`.

### Configuração do banco (`docker-compose.yml`)

```yaml
version: '3.8'
services:
  mysql:
    image: mysql:8.0
    container_name: canil-mysql
    restart: always
    environment:
      MYSQL_ROOT_PASSWORD: root123
      MYSQL_DATABASE: canil_db
      MYSQL_USER: dev
      MYSQL_PASSWORD: dev123
    ports:
      - "3306:3306"
    volumes:
      - canil-mysql-data:/var/lib/mysql
volumes:
  canil-mysql-data:
```

### `application.properties`

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/canil_db
spring.datasource.username=dev
spring.datasource.password=dev123
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
server.port=8080
```

---

## 📚 Contexto do projeto

Este projeto é a continuação natural do **[Canil CLI](https://github.com/jmauriciordelima/jornadaFullStackComJava_e_LevelUp)**, desenvolvido em Java puro com:

- Arquitetura em camadas (modelo / repositório / serviço / util)
- Herança e polimorfismo (`Animal` → `Cachorro`, `Gato`)
- Interfaces (`Adotavel`, `Vacinavel`)
- Enum (`FaseVida`)
- Persistência em arquivo `.csv`

A migração para Spring Boot representa a evolução de um CRUD via terminal para uma API REST real, conectada a um banco de dados relacional.

---

## 👤 Autor

**José Maurício**
Desenvolvedor em formação — foco em backend Java
[LinkedIn](https://www.linkedin.com/in/jmauriciorlima/) · [GitHub](https://github.com/JMAURICIORLIMA)

---

## 📄 Licença

Este projeto está sob a licença MIT.
