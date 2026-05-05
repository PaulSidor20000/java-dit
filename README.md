# 📦 Java Microservices Project

## 📖 Overview

This project consists of multiple Spring Boot microservices running in Docker:

* **order-service** — основной сервис заказов
* **mock-service** — мок-сервис для имитации внешнего API
* **order-db** — PostgreSQL база данных
* **order-db-migration** — сервис миграций (Liquibase)
* **grafana + prometheus + k6** — мониторинг и нагрузочное тестирование

---

## 🚀 Requirements (что нужно на хост машине)

Обязательно:

* Docker
* Docker Compose (или `docker compose` встроенный)

Не требуется:

* ❌ Java (всё собирается внутри Docker)
* ❌ Maven
* ❌ PostgreSQL (контейнер)

---

## ⚡ Quick Start

⚡ Лучше сразу отключить VPN, т.к. сборка подгружает все зависимости и может упасть.

⚡ С быстрым интернетом контейнеры стартуют за 3-5 минут.

```bash
docker-compose up --build
```

или (если используется новая версия CLI):

```bash
docker compose up --build
```

---

## 🔌 Ports (занимаемые порты на хосте)

| Service       | Port (Host → Container) | Description         |
| ------------- | ----------------------- | ------------------- |
| order-service | 9089 → 9089             | API сервиса заказов |
| mock-service  | 8080 → 8080             | Mock API            |
| PostgreSQL    | 6541 → 5432             | База данных         |
| Grafana       | 3002 → 3000             | UI мониторинга      |
| Prometheus    | 9091 → 9090             | Метрики             |

⚠️ Обрати внимание:

* **9090 внутри order-service НЕ проброшен наружу** (используется только для healthcheck)

---

## 🧩 Services

### 1️⃣ Order Service

**Base URL:**

```
http://localhost:9089
```

**Swagger:**

```
http://localhost:9089/swagger-ui.html
```

**OpenAPI:**

```
http://localhost:9089/v3/api-docs
```

**Endpoints:**

```
GET /api/v1/orders   — получить список заказов
GET /api/v1/order    — получить заказ
```

**Actuator (внутренний):**

```
http://localhost:9090/actuator/health
```

---

### 2️⃣ Mock Service

**Base URL:**

```
http://localhost:8080
```

**Swagger:**

```
http://localhost:8080/swagger-ui.html
```

**OpenAPI:**

```
http://localhost:8080/v3/api-docs
```

**Endpoints:**

```
GET /api/v1/order — мок ответа заказа
```

---

## 🗄 Database

**PostgreSQL:**

```
Host: localhost
Port: 6541
DB: order-db
User: postgres
Password: password
```

---

## 🔄 Service взаимодействие

```
order-service → mock-service
```

Через:

```
http://mock-service:8080/api/v1/order
```

---

## 🏥 Healthchecks

* order-service:

```
/actuator/health (порт 9090 внутри контейнера)
```

* mock-service:

```
/actuator/health (порт 8080)
```

---

## 📊 Monitoring

### Grafana

```
http://localhost:3002
```

### Prometheus

```
http://localhost:9091
```

---

## 🧪 Load Testing (k6)

Скрипты лежат в:

```
/docker/k6
```

Запускаются автоматически после старта сервисов.

---

## 🛠 Build Details

Сборка происходит в 2 этапа:

1. **Maven (build stage)**

    * скачивает зависимости
    * собирает нужный модуль

2. **Runtime (Corretto 21 Alpine)**

    * запускает JAR

---

## ⚠️ Важно

* Все сервисы запускаются через Docker — локальный запуск не обязателен
* Swagger включён по умолчанию
* Убедись, что порты свободны перед запуском

---

## 🧹 Stop

```bash
docker-compose down
```

или

```bash
docker compose down
```

---

## 💡 Полезно

Если что-то не стартует:

```bash
docker-compose logs -f
```

---

## ✅ Готово

После запуска:

👉 Открывай Swagger:

* http://localhost:9089/swagger-ui.html
* http://localhost:8080/swagger-ui.html

и можно тестировать API 🚀
