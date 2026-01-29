# spring-boot-api

![CI](https://github.com/joseantoniocgonzalez/java-learning-lab/actions/workflows/ci.yml/badge.svg)

Minimal Spring Boot REST API module for learning purposes.

## What it includes
- JSON responses (DTOs)
- Input validation with consistent JSON error responses
- H2 in-memory database + Spring Data JPA
- Integration tests (MockMvc)
- OpenAPI / Swagger UI

## Requirements
- Java 21
- Maven

## Run (from repository root)

Run tests + coverage (JaCoCo):
    mvn -f spring-boot-api/pom.xml verify

Run the API:
    mvn -f spring-boot-api/pom.xml spring-boot:run

Base URL:
    http://localhost:8080

Swagger UI (OpenAPI):
    http://localhost:8080/swagger-ui/index.html

## Endpoints

### Hello
- GET /api/hello
  Response: {"message":"Hello from Spring Boot"}

- GET /api/hello?name=Jose
  Response: {"message":"Hello, Jose"}

- GET /api/hello?name=
  Status: 400
  Body: {"error":"name must not be blank"}

### Items
- POST /api/items
- GET /api/items
- GET /api/items/{id}
- PUT /api/items/{id}
- DELETE /api/items/{id}

### Books
- POST /api/books
- GET /api/books
- GET /api/books/{id}
- PUT /api/books/{id}
- DELETE /api/books/{id}

## cURL examples

Hello:
    curl "http://localhost:8080/api/hello"
    curl "http://localhost:8080/api/hello?name=Jose"
    curl "http://localhost:8080/api/hello?name="

Create sample books:
    curl -X POST "http://localhost:8080/api/books" -H "Content-Type: application/json" -d '{"title":"Dune","author":"Frank Herbert","year":1965}'
    curl -X POST "http://localhost:8080/api/books" -H "Content-Type: application/json" -d '{"title":"Neuromancer","author":"William Gibson","year":1984}'

Create sample items:
    curl -X POST "http://localhost:8080/api/items" -H "Content-Type: application/json" -d '{"name":"ItemB"}'
    curl -X POST "http://localhost:8080/api/items" -H "Content-Type: application/json" -d '{"name":"ItemA"}'

List paginated + sorted:
    curl "http://localhost:8080/api/books?page=0&size=2&sort=year,desc"
    curl "http://localhost:8080/api/items?page=0&size=5&sort=name,asc"

Not found examples:
    curl -i "http://localhost:8080/api/items/999999"
    curl -i "http://localhost:8080/api/books/999999"
