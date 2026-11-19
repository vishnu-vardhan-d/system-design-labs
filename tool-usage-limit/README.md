# Tool Usage Limit

Tracks and enforces per-session limits on tool usage (for example `web_search` at most 3 times per session), plus a typed Java client.
In-memory, so it needs no database.

```
mvnw.cmd -pl tool-usage-limit test
mvnw.cmd -pl tool-usage-limit spring-boot:run
```

Runs on http://localhost:8082.
