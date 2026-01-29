# spring-boot-api

![CI](https://github.com/joseantoniocgonzalez/java-learning-lab/actions/workflows/ci.yml/badge.svg)

Minimal Spring Boot REST API module for learning purposes.

## What it includes
- JSON responses (DTOs)
- Input validation with consistent JSON errors
- H2 in-memory database + Spring Data JPA
- Integration tests (MockMvc)
- OpenAPI / Swagger UI

## Requirements
- Java 21
- Maven

## Run
From repository root:

    mvn -f spring-boot-api/pom.xml test
    mvn -f spring-boot-api/pom.xml spring-boot:run

Base URL:

    http://localhost:8080

## Endpoints

### Hello
- GET /api/hello
- GET /api/hello?name=Jose
- GET /api/hello?name=  (400)

### Items
- POST /api/items
- GET /api/items
- GET /api/items/{id}

Example:

    curl -X POST "http://localhost:8080/api/items" -H "Content-Type: application/json" -d '{"name":"Item1"}'
    curl "http://localhost:8080/api/items"
    curl "http://localhost:8080/api/items/1"

### Books (CRUD)
- POST /api/books
- GET /api/books
- GET /api/books/{id}
- PUT /api/books/{id}
- DELETE /api/books/{id}

Examples:

    curl -X POST "http://localhost:8080/api/books" -H "Content-Type: application/json" -d '{"title":"A Study in Scarlet","author":"Arthur Conan Doyle","year":1887}'
    curl "http://localhost:8080/api/books"
    curl "http://localhost:8080/api/books/1"
    curl -X PUT "http://localhost:8080/api/books/1" -H "Content-Type: application/json" -d '{"title":"The Sign of the Four","author":"Arthur Conan Doyle","year":1890}'
    curl -X DELETE "http://localhost:8080/api/books/1"

## API documentation (Swagger / OpenAPI)
- Swagger UI: http://localhost:8080/swagger-ui/index.html
- OpenAPI JSON: http://localhost:8080/v3/api-docs
