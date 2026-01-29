# java-learning-lab

![CI](https://github.com/joseantoniocgonzalez/java-learning-lab/actions/workflows/ci.yml/badge.svg)

Spanish version: **README.es.md**

**java-learning-lab** is a structured Java learning and practice repository focused on **backend-oriented skills** and **software quality**.  
Work is organized by topic and validated through **automated tests (JUnit)**.

This repository includes:
- **Java core katas** (unit tested)
- A **Spring Boot REST API module** with **JSON responses**, **input validation**, **CRUD**, and **integration tests**

## Goals
- Build strong Java fundamentals (OOP, collections, exceptions, best practices).
- Practice a test-first mindset and basic TDD through katas and small exercises.
- Maintain a portfolio-ready codebase for backend work.
- Develop a Spring Boot REST API with tests, validation, and simple persistence.

## Structure
- `java-core-katas/` — topic-based exercises validated with JUnit.
- `spring-boot-api/` — Spring Boot REST API (DTO/JSON + validation + tests + H2 + JPA + Swagger).
- `docs/` — notes (if present).

## Quick start

### Run tests + coverage reports (JaCoCo)
From repository root:

    mvn -f java-core-katas/pom.xml verify
    mvn -f spring-boot-api/pom.xml verify

### Run Spring Boot API

    cd spring-boot-api
    mvn spring-boot:run

Base URL:

    http://localhost:8080

Swagger UI:

    http://localhost:8080/swagger-ui.html
