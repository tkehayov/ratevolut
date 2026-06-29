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

- The database schema is applied automatically via Liquibase on startup. No manual migration step is required.
- The Spring profile (`prod`/`dev`) is set at build time via `--build-arg SPRING_PROFILES_ACTIVE`. The `dev` profile seeds test data; `prod` does not.
- The service runs as a non-root user inside the container.
- Port `8080` is the only port exposed.
