# spring-boot-api

Minimal Spring Boot REST API module for learning purposes.

## What it includes
- JSON responses using DTOs
- Basic input validation (400 Bad Request) with JSON error body
- H2 in-memory database + Spring Data JPA
- Tests:
  - MVC tests with `@WebMvcTest` (hello)
  - Integration tests with `@SpringBootTest` + MockMvc (items)

## Requirements
- Java 17+
- Maven

## Run tests

    mvn test

## Run the application

    mvn spring-boot:run

Base URL: `http://localhost:8080`

## Endpoints

### Hello

- `GET /api/hello`  
  Response:

    {"message":"Hello from Spring Boot"}

- `GET /api/hello?name=Jose`  
  Response:

    {"message":"Hello, Jose"}

- `GET /api/hello?name=`  
  Status: `400 Bad Request`  
  Body:

    {"error":"name must not be blank"}

### Items (CRUD minimal)

- `POST /api/items`  
  Body:

    {"name":"Item1"}

  Response (201 Created):

    {"id":1,"name":"Item1"}

- `GET /api/items`  
  Response:

    [{"id":1,"name":"Item1"}]

- `GET /api/items/{id}`  
  Example: `GET /api/items/1`  
  Response:

    {"id":1,"name":"Item1"}

- Validation: `POST /api/items` with blank name  
  Status: `400 Bad Request`

## cURL examples

    curl "http://localhost:8080/api/hello"
    curl "http://localhost:8080/api/hello?name=Jose"
    curl "http://localhost:8080/api/hello?name="

    curl -X POST "http://localhost:8080/api/items" -H "Content-Type: application/json" -d '{"name":"Item1"}'
    curl "http://localhost:8080/api/items"
    curl "http://localhost:8080/api/items/1"
