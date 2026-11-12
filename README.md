# System Design Labs

Classic system design problems, built with Spring Boot 4 and Oracle AI Database 26ai Free.
Each system is a Maven module of this parent. All systems share one Oracle schema, `SYSTEMDESIGNLABS`.

## Run

```
docker compose up -d
mvnw.cmd -pl url-shortener spring-boot:run
```

Oracle: `localhost:1521`, service `systemdesignlabs`, user `systemdesignlabs` / `systemdesignlabs`.

## Systems

| System | Module | Port |
|---|---|---|
| URL Shortener | `url-shortener` | 8081 |
