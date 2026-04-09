Демонстрационная микросервисная система на Kotlin и Spring Boot с асинхронным обменом сообщениями через RabbitMQ, хранением данных в PostgreSQL и документированием API через OpenAPI / Swagger.
## Что реализовано
- Разделение системы на несколько сервисов с разной ответственностью
- HTTP-взаимодействие и обмен сообщениями через RabbitMQ
- Хранение данных в PostgreSQL
- Миграции схемы базы данных через Flyway
- Валидация входных данных
- Swagger / OpenAPI для документирования API
- Локальный запуск инфраструктуры через Docker Compose

## Архитектура

### service1-sender
Сервис-отправитель, формирующий и отправляющий события / сообщения в систему.
### service2-core
Основной сервис с бизнес-логикой, интеграцией с PostgreSQL и миграциями Flyway.
### service3-admin
Административный сервис для управляющих и интеграционных сценариев.

## Технологии
- Kotlin
- Spring Boot
- RabbitMQ
- PostgreSQL
- Flyway
- OpenAPI / Swagger
- Docker Compose

## Запуск проекта

### Требования
- JDK 11+
- Docker Desktop
- Docker Compose

### 1. Поднять инфраструктуру
```bash
docker compose up --build
