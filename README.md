# rimfrost-service-team

Quarkus-based service that implements the Team API defined in `rimfrost-service-team-openapi`.
Exposes endpoints for retrieving team membership and an individual's team affiliations.

## Requirements

See [krav.md](krav.md) for functional requirements.

## Build

```bash
./mvnw test-compile -q    # compile check
./mvnw spotless:apply     # format
./mvnw test               # unit tests
```
