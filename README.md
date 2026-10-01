# Supermarket Checkout

A simplified supermarket checkout service built as a coding task for Haiilo Gmbh for Junior Full-Stack role. It totals a cart containing any combination of available products and automatically applies configured multi-buy offers.

## Requirements

- Java 21

## Run

```bash
./gradlew bootRun
```

The service starts on `http://localhost:8080`.

## Checkout API

`POST /checkout` accepts a JSON array. Each entry needs a product `name`; `quantity` is optional and defaults to 1.

```bash
curl -X POST http://localhost:8080/checkout \
  -H 'Content-Type: application/json' \
  -d '[{"name":"Apple","quantity":2},{"name":"Mango"}]'
```

The response is the total as a decimal value (`2.75` for this example).

Unknown product names and quantities below 1 return `400 Bad Request`.

## Products and offers

| Product | Unit price | Multi-buy offer |
| --- | ---: | --- |
| Apple | €0.30 | 2 for €0.45 |
| milk | €1.30 | None |
| Mango | €2.30 | 2 for €3.50 |
| Avocado | €1.00 | 3 for €2.00 |

Products and current offers are defined in `StoreConfig`.

## Tests

```bash
./gradlew test
```
