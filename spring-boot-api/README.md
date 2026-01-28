# spring-boot-api

Minimal Spring Boot REST API module for learning purposes.

## What it includes
- JSON responses using DTOs
- Optional query parameter handling
- Basic input validation (400 Bad Request) with JSON error body
- Tests:
  - MVC tests with `@WebMvcTest`
  - Unit tests for service layer

## Requirements
- Java 17+
- Maven

## Run tests

    mvn test

## Run the application

    mvn spring-boot:run

Base URL: `http://localhost:8080`

## Endpoints

### GET /api/hello
Response:

    {"message":"Hello from Spring Boot"}

### GET /api/hello?name=Jose
Response:

    {"message":"Hello, Jose"}

### GET /api/hello?name=
Response:
- Status: `400 Bad Request`
- Body:

    {"error":"name must not be blank"}

## cURL examples

    curl "http://localhost:8080/api/hello"
    curl "http://localhost:8080/api/hello?name=Jose"
    curl "http://localhost:8080/api/hello?name="
