# java-learning-lab

![CI](https://github.com/joseantoniocgonzalez/java-learning-lab/actions/workflows/ci.yml/badge.svg)

Repositorio de práctica y aprendizaje de Java orientado a backend, organizado por módulos y validado con tests automáticos.

Incluye:
- **Katas de Java** (con JUnit)
- Un módulo **Spring Boot REST API** con **JSON**, **validación**, **CRUD** y **tests**

## Estructura
- `java-core-katas/` — ejercicios por temas con JUnit.
- `spring-boot-api/` — API REST con Spring Boot (DTO/JSON + validación + tests + H2 + JPA + Swagger).

## Uso rápido

Tests + cobertura (JaCoCo):
    mvn -f java-core-katas/pom.xml verify
    mvn -f spring-boot-api/pom.xml verify

Ejecutar la API:
    cd spring-boot-api
    mvn spring-boot:run
