# NASA Asteroid API

A Spring Boot REST service that wraps NASA's Asteroid API. It fetches asteroid feed and lookup data from NASA, maps it into a clean response model, and serves it over a small HTTP API with Redis-backed caching.

## Features

- **Feed endpoint** — list near earth objects approaching within a date range, sorted by closest miss distance.
- **Lookup endpoint** — fetch full detail (diameter, close approaches, hazard flags) for a single asteroid by NASA ID.
- **Redis caching** — feed and lookup results are cached to reduce calls to the NASA API.
- **Request validation** — date range and result limit are validated before any external call.

## Tech Stack

- Java 25
- Spring Boot 4.1.0
- Redis 7
- Lombok
- Gradle
- JDK 25
- Docker (for Redis via `docker-compose`), or a local Redis instance
- A NASA API key (optional — defaults to `DEMO_KEY`). Get one free at [api.nasa.gov](https://api.nasa.gov/).

## Configuration

Configuration is read from environment variables (or a local `.env` file, which is imported automatically). Copy the example file and adjust as needed:

```bash
cp .env.example .env
```

| Variable         | Description                          | Default                  |
|------------------|--------------------------------------|--------------------------|
| `NASA_API_KEY`   | Your NASA API key                    | `DEMO_KEY`               |
| `NASA_BASE_URL`  | NASA API base URL                    | `https://api.nasa.gov`   |
| `REDIS_HOST`     | Redis host                           | `localhost`              |
| `REDIS_PORT`     | Redis port                           | `6379`                   |

> Note: `DEMO_KEY` works for quick trials but is heavily rate-limited by NASA. Use your own key for anything beyond casual testing.

## Running the App

### 1. Start Redis

```bash
docker compose up -d redis
```

### 2. Run the application

```bash
./gradlew bootRun
```

The API starts on **http://localhost:8080**.


## API Reference

Base path: `/api/v1/asteroids`

### List asteroids (feed)

```
GET /api/v1/asteroids?startDate={ISO_DATE}&endDate={ISO_DATE}&limit={n}
```

| Query param | Required | Description                                              |
|-------------|----------|----------------------------------------------------------|
| `startDate` | yes      | ISO date (`YYYY-MM-DD`)                                  |
| `endDate`   | yes      | ISO date (`YYYY-MM-DD`)                                  |
| `limit`     | no       | Max results, `1`–`50` (defaults to `10`)                 |

Constraints:
- The date range must be **between 0 and 7 days** (NASA's feed limit). Larger ranges are rejected with a validation error.
- Results are sorted by miss distance (closest first); objects without a parseable distance are omitted.

Example:

```bash
curl "http://localhost:8080/api/v1/asteroids?startDate=1942-01-21&endDate=1942-01-28&limit=5"
```

Response:

```json
[
  {
    "id": "3313157",
    "name": "(2002 PV)",
    "nasaJplUrl": "https://ssd.jpl.nasa.gov/tools/sbdb_lookup.html#/?sstr=3313157",
    "absoluteMagnitude": 17.5,
    "isHazardous": true,
    "date": "1942-01-21",
    "distanceKm": 1500000.0,
    "orbitingBody": "Earth"
  }
]
```

### Get asteroid detail (lookup)

```
GET /api/v1/asteroids/{id}
```

| Path param | Description                  |
|------------|------------------------------|
| `id`       | NASA NEO reference ID        |

Example:

```bash
curl "http://localhost:8080/api/v1/asteroids/3313157"
```

Returns the full detail including estimated diameter (km/m/miles/feet), all close-approach events, and hazard/sentry flags.

## Project Structure

```
src/main/java/com/andry/nasa_asteroid_api/
├── client/        # NASA API HTTP clients (feed, lookup)
├── common/        # Shared exception handling and error responses
├── config/        # Cache, REST client, and NASA properties configuration
├── controller/    # REST endpoints
├── dto/           # Request, response, and NASA payload models
├── service/       # Business logic (mapping, sorting, filtering)
└── validation/    # Custom date-range validation
```