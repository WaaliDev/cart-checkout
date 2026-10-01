# Supermarket Checkout

A small Spring Boot checkout API built for a Junior Fullstack coding exercise at Haiilo GmbH. It calculates totals for carts containing any combination of available products and automatically applies multi-buy offers.

## Getting started

**Requirements:** Java 21

Start the application:

```bash
./gradlew bootRun
```

The API is available at `http://localhost:8080`.

Run the tests:

```bash
./gradlew test
```

## API

### List products

`GET /products` returns the available products and their prices.

```bash
curl http://localhost:8080/products
```

### Calculate a checkout total

`POST /checkout` accepts a JSON array of product names and optional quantities. A missing quantity means one item.

```bash
curl -X POST http://localhost:8080/checkout \
  -H 'Content-Type: application/json' \
  -d '[{"name":"Apple","quantity":2},{"name":"Mango"}]'
```

The response is the total as a JSON number. The example returns `2.75`: two apples cost €0.45 with the offer, plus one mango at €2.30. Unknown products and quantities below one are rejected with `400 Bad Request`.

## Products and offers

| Product | Unit price | Offer |
| --- | ---: | --- |
| Apple | €0.30 | 2 for €0.45 |
| milk | €1.30 | None |
| Mango | €2.30 | 2 for €3.50 |
| Avocado | €1.00 | 3 for €2.00 |

The catalog and offers are configured in memory in `StoreConfig`.

## Checkout rules

- Product names are case-sensitive and must match the catalog.
- Repeated entries for the same product are combined, regardless of their order in the request.
- Offers apply once for each complete qualifying group. Any remaining items use the regular unit price.
- A product can have at most one offer.

## Project structure

```text
src/
├── main/
│   ├── java/com/supermarket/cart_checkout/
│   │   ├── config/       # Product and offer configuration
│   │   ├── controller/   # REST API endpoints
│   │   ├── dto/          # Request DTOs
│   │   ├── model/        # Product, offer, item, and cart models
│   │   └── service/      # Checkout pricing logic
│   └── resources/        # Spring Boot configuration
└── test/java/com/supermarket/cart_checkout/
    ├── controller/       # API tests
    └── service/          # Pricing tests
```

## Design notes

- Pricing uses `BigDecimal` to avoid floating-point arithmetic for money.
- Product prices and offers are server-side; clients submit only names and quantities.
- Checkout calculations are kept in a service, separate from the REST controllers.
- The project has no database; product and offer data are defined in application configuration.