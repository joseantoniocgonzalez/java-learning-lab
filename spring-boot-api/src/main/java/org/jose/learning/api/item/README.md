# Items CRUD (H2 + JPA)

This package contains a minimal CRUD example implemented with Spring Boot, Spring Data JPA and an in-memory H2 database.

## Components
- `Item` — JPA entity (`items` table) with `id` and `name`
- `ItemRepository` — `JpaRepository<Item, Long>`
- `ItemController` — REST endpoints under `/api/items`
- DTOs in `dto/`:
  - `CreateItemRequest` — request body for create (`name` validated with `@NotBlank`)
  - `ItemResponse` — response body (`id`, `name`)

## Endpoints
- `POST /api/items`  
  Creates a new item. Requires JSON body with non-blank `name`.

- `GET /api/items`  
  Lists all items.

- `GET /api/items/{id}`  
  Returns one item or `404` if not found.

## Run
From `spring-boot-api/`:

    mvn spring-boot:run

## Quick test with cURL

    curl -X POST "http://localhost:8080/api/items" -H "Content-Type: application/json" -d '{"name":"Item1"}'
    curl "http://localhost:8080/api/items"
    curl "http://localhost:8080/api/items/1"
