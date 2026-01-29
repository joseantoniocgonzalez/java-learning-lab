# java-learning-lab

![CI](https://github.com/joseantoniocgonzalez/java-learning-lab/actions/workflows/ci.yml/badge.svg)

**java-learning-lab** is a structured Java learning and practice repository focused on **backend-oriented skills** and **software quality**.
Work is organized by topic and validated through **automated tests (JUnit)**, reinforcing clean code habits, refactoring, and solid **object-oriented design**.

This repository includes:
- **Java core katas** (unit tested)
- A **Spring Boot REST API module** with **JSON responses**, **basic input validation**, and **MVC/unit tests**

## Goals
- Build strong Java fundamentals (OOP, collections, exceptions, best practices).
- Practice a test-first mindset and basic TDD through katas and small exercises.
- Maintain a portfolio-ready codebase for technical interviews and backend work.
- Develop a Spring Boot REST API with automated tests and simple validation.

## Structure
- `java-core-katas/` — topic-based exercises validated with JUnit (OOP, collections, exceptions, etc.).
- `spring-boot-api/` — Spring Boot REST API (DTO/JSON + validation + tests).
- `docs/` — concise notes, learning checklist, and technical decisions.

## Requirements

- Java 21
- Maven

## Quick start

### Run tests
From repository root:

    mvn -f java-core-katas/pom.xml test
    mvn -f spring-boot-api/pom.xml test

### Run Spring Boot API

    cd spring-boot-api
    mvn spring-boot:run

## Spring Boot API module

Base URL: `http://localhost:8080`

### Endpoints

- `GET /api/hello`
  Response: `{"message":"Hello from Spring Boot"}`

- `GET /api/hello?name=Jose`
  Response: `{"message":"Hello, Jose"}`

- `GET /api/hello?name=`
  Status: `400 Bad Request`
  Body: `{"error":"name must not be blank"}`

### cURL examples

    curl "http://localhost:8080/api/hello"
    curl "http://localhost:8080/api/hello?name=Jose"
    curl "http://localhost:8080/api/hello?name="
