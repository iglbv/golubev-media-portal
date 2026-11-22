# Голубев Илья Дмитриевич РИМ-160980к
# Итоговый проект "Система управления контентом для мультиформатного медиапортала"

Backend-приложение Spring Boot для управления статьями, видео и подкастами с эпизодами.

## Требования

- Docker Desktop или Docker Engine с Docker Compose
- Для запуска без Docker: JDK 21, Maven 3.9+ и PostgreSQL 16

## Запуск через Docker Compose

```powershell
docker compose up --build
```

Приложение и Swagger: http://localhost:8080  
Прямой адрес Swagger UI: http://localhost:8080/swagger-ui/index.html  
OpenAPI JSON: http://localhost:8080/v3/api-docs

Остановка:

```powershell
docker compose down
```

Удаление базы данных вместе с данными:

```powershell
docker compose down -v
```

## Локальный запуск без Docker

Создайте базу PostgreSQL и задайте переменные при необходимости:

```powershell
$env:SPRING_DATASOURCE_URL="jdbc:postgresql://localhost:5432/media_portal"
$env:SPRING_DATASOURCE_USERNAME="media"
$env:SPRING_DATASOURCE_PASSWORD="media"
.\mvnw.cmd spring-boot:run
```

## Тесты

```powershell
.\mvnw.cmd test
```

## Демонстрационный сценарий

1. Откройте Swagger UI.
2. Выполните `POST /api/articles`, передав заголовок, текст, автора и дату публикации.
3. Выполните `GET /api/articles` и найдите созданную статью.
4. Выполните `PUT /api/articles/{id}` для изменения статьи.
5. Повторите сценарий для `POST /api/videos` и `POST /api/podcasts`.
6. Проверьте удаление через `DELETE /api/articles/{id}`, `DELETE /api/videos/{id}` и `DELETE /api/podcasts/{id}`.
7. Проверьте валидацию, отправив пустой обязательный параметр: API вернет `400` и список полей с ошибками.
8. Запросите отсутствующий объект: API вернет `404` в едином формате ошибки.

Коллекция Postman находится в файле `postman_collection.json`.