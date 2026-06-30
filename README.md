# Ratevolut

Currency conversion service.

## Running locally
```bash
docker compose up -d
```
## Running in production

### 1. Build the image

The active Spring profile is baked in at build time via the `SPRING_PROFILES_ACTIVE` build argument.

```bash
docker build --build-arg SPRING_PROFILES_ACTIVE=prod -t ratevolut .
```

### 2. Run the container

```bash
docker run -d \
  -p 8080:8080 \
  -e SPRING_DATASOURCE_URL=jdbc:postgresql://<host>:<port>/<db> \
  -e SPRING_DATASOURCE_USERNAME=<username> \
  -e SPRING_DATASOURCE_PASSWORD=<password> \
  -e APP_FRANKFURTER_BASE_URL=https://api.frankfurter.dev/v2 \
  ratevolut
```
You could validate it by running db at same docker network as app and you will notice no empty tables provided: 
```
docker run -d \
-p 8080:8080 \
--network ratevolut_default \
-e SPRING_DATASOURCE_URL=jdbc:postgresql://db:5432/ratevolut \
-e SPRING_DATASOURCE_USERNAME=postgres \
-e SPRING_DATASOURCE_PASSWORD=postgres \
-e APP_FRANKFURTER_BASE_URL=https://api.frankfurter.dev/v2 \
-e SPRING_PROFILES_ACTIVE=prod \
ratevolut
```
### Notes
- I am using UUID as a user_id and everytime db is generating different. So I can't provide user_id out of the box. You could connect db and get it by:   
```URL: jdbc:postgresql://localhost:5432/ratevolut```
```USERNAME:postgres ```
```PASSWORD:postgres ```
- The database schema is applied automatically via Liquibase on startup. No manual migration step is required.
- The Spring profile (`prod`/`dev`) is set at build time via `--build-arg SPRING_PROFILES_ACTIVE`. The `dev` profile seeds test data; `prod` does not.
- The service runs as a non-root user inside the container.
- Port `8080` is the only port exposed.

## Idempotency

`POST /conversions` supports an optional `Idempotency-Key` header. If the same key is sent more than once within the persistence window no second debit occurs.

**How it works:**

1. The key is stored as a unique column on the `conversions` row at the time of the first successful request.
2. On replay, the service looks up the existing row by key and returns it immediately — the balance update and Frankfurter API call are skipped entirely.
3. If no key is provided the request is treated as a regular (non-idempotent) call.

## Caching
According frankfurter's docs(https://frankfurter.dev/v1/) updates new rates **once a day, around 16:00 CET**
![img.png](img.png)

We handle this using **2-Hour Fixed TTL:**. However, a strict 24-hour cache window risks serving outdated.