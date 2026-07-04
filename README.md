# Transaction Monitor — Fraud Detection Engine

A Spring Boot service that evaluates incoming transactions against a configurable set of fraud rules in real time, raises alerts, and automatically locks accounts when high-severity violations are detected.

## Overview

Every transaction submitted to the system is:

1. Saved as `PENDING`
2. Checked against the account's current status (rejected immediately if the account is already `LOCKED`)
3. Evaluated by a pluggable rule engine
4. Updated to `SUCCESS`, `FLAGGED`, or `REJECTED` based on the outcome
5. Logged as one or more `Alert` records if any rule is violated

Accounts with a `HIGH` severity violation are automatically locked, blocking all future transactions on that account until manually unlocked.

## Architecture

```
TransactionController
        │
        ▼
TransactionService
        │
        ├── TransactionRepository   (persist transaction)
        ├── AccountsRepository      (fetch / lock account)
        ├── RuleEngine              (evaluate fraud rules)
        │       │
        │       └── List<FraudRule> (auto-injected by Spring)
        │               ├── HighAmountRule
        │               └── ... (add more rules here)
        │
        └── AlertsRepository        (persist violations)
```

### Rule Engine

`RuleEngine` is a `@Component` that takes a `List<FraudRule>` in its constructor. Spring automatically collects every `@Component`-annotated `FraudRule` implementation in the application context and injects them — no manual wiring required. Adding a new fraud check is as simple as creating a new class that implements `FraudRule`.

```java
public interface FraudRule {
    RuleViolation check(Transaction txn, Accounts account);
}
```

`RuleEngine.evaluate(txn, account)` runs the transaction and account through every registered rule and collects all triggered violations into a `List<RuleViolation>`.

## Entities

| Entity | Table | Purpose |
|---|---|---|
| `Transaction` | `transaction` | A single debit/credit request, with a status lifecycle |
| `Accounts` | `accounts` | Account balance, holder info, and lock status |
| `Alerts` | `alerts` | One row per rule violation, linked to a transaction |

### Transaction status lifecycle

```
PENDING → SUCCESS              (no violations)
PENDING → FLAGGED              (violation(s), action = ALERT_ONLY)
PENDING → REJECTED             (violation with action = LOCK_ACCOUNT,
                                  OR account already LOCKED)
```

### Account status

```
ACTIVE → LOCKED   (triggered by any HIGH severity violation)
```

## Fraud Rules

| Rule | Trigger | Severity | Action |
|---|---|---|---|
| `HighAmountRule` | `amount > 50000` | `MEDIUM` | `ALERT_ONLY` |

> Add new rules by implementing `FraudRule` and annotating with `@Component`. The `RuleEngine` will pick them up automatically.

## API

### `POST /requestTxn`

Submits a new transaction for fraud evaluation.

**Request body**

```json
{
  "accountId": 1,
  "amount": 75000.00,
  "type": "DEBIT",
  "location": "Hyderabad"
}
```

**Response**

```json
{
  "id": 6,
  "accountId": 1,
  "amount": 75000.0,
  "type": "DEBIT",
  "location": "Hyderabad",
  "status": "FLAGGED",
  "flagged": true,
  "timestamp": "2026-06-21T23:49:13.7073483"
}
```

If the account is already `LOCKED`, the transaction is rejected immediately and no rules are evaluated:

```json
{
  "id": 7,
  "status": "REJECTED",
  "flagged": false
}
```

## Tech Stack

- Java + Spring Boot
- Spring Data JPA / Hibernate
- MySQL
- Lombok

## Getting Started

1. Configure your MySQL connection in `application.properties` (or `.yml`):

   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/transaction_monitor
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   spring.jpa.hibernate.ddl-auto=update
   ```

2. Build and run:

   ```bash
   ./mvnw spring-boot:run
   ```

3. Send a test transaction:

   ```bash
   curl -X POST http://localhost:8080/requestTxn \
     -H "Content-Type: application/json" \
     -d '{"accountId":1,"amount":75000.00,"type":"DEBIT","location":"Hyderabad"}'
   ```

## Roadmap

- [ ] Additional fraud rules (velocity checks, geo-mismatch, unusual time-of-day)
- [ ] Reconcile severity vs. action as the single source of truth for account locking
- [ ] Endpoint to manually unlock an account
- [ ] Endpoint to list / resolve alerts
- [ ] Authentication on `/requestTxn`

## Notes

This is a learning / portfolio project demonstrating a pluggable rule-engine pattern in Spring Boot — not a production-hardened fraud detection system. Account lookups happen server-side from `accountId` only; account state is never trusted from the request body.
