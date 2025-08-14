# Stock API Integration Service

> **Spring Boot microservice for integrating with an external REST Stock API (Wiremock), demonstrating modern best practices: OpenAPI, Feign, MapStruct, Resilience4j centralized error handling, integration tests.**

---

## Table of Contents

1. [Description and Features](#description-and-features)
2. [Run Instructions](#run-instructions)
3. [Testing](#testing)

---

## Description and Features

**Stock API Integration** — a Spring Boot microservice demonstrating best practices for integrating with an external REST API using a stub Stock API (Wiremock) as an example.

**The project implements:**

* OpenAPI-first approach: auto-generating client from specification
* Integration via Feign + Resilience4j RateLimiter
* Centralized error handler (GlobalExceptionHandler)
* DTOs and mapping via MapStruct
* Integration and contract tests (WireMock, Testcontainers)
* Production-ready infrastructure (Docker, Docker Compose, healthchecks)
* Unified error format (ErrorDto)

---

## Run Instructions

### 1. Run via Docker Compose

```bash
docker compose up --build
```

Two containers will start:

* **stock-integration-app** (Spring Boot, port 8099)
* **stub-stock-api** (Wiremock, port 8081, expects X-API-Key: demo-secret-key)

### 2. Request examples

**Get list of stocks:**

```bash
curl -H "X-API-Key: demo-secret-key" http://localhost:8099/api/v1/cats
```

**Get stock by id:**

```bash
curl -H "X-API-Key: demo-secret-key" http://localhost:8099/api/v1/cats/1
```

**Error (no API key):**

```bash
curl http://localhost:8099/api/v1/cats
```

```json
{
  "message": "Missing or incorrect API key",
  "code": "STOCK_API_UNAUTHORIZED",
  "timestamp": "2025-07-15T12:34:56.789123",
  "path": "/api/v1/cats"
}
```

---

## Testing

The project contains **integration tests**:

* run:

  ```bash
  ./gradlew clean test
  ```
* uses MockMvc + WireMockExtension (stub mappings in `src/test/resources/wiremock/mappings`)
* tests all key scenarios: 200/401/404/invalid API key/ratelimiter.

---

