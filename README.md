**Purchase Processing**

**Overview**
This service is a backend REST API application built using:
•	Java 
•	Spring Boot
•	Maven
Allows users to:
1.	Create purchases in USD
2.	Retrieve purchase details
3.	Convert purchase amount into supported currencies using historical Treasury exchange rates
   
**Functional Requirements:**
Create Purchase
•	Accept purchase details
•	Store purchase in repository
•	Generate unique UUID
Retrieve Purchase
•	Fetch purchase using UUID
•	Convert amount using exchange rate
•	Return converted amount
Currency Conversion
•	Use historical exchange rates
•	Select nearest valid previous rate

**Non-Functional Requirements**
Requirement	Solution
Scalability	Stateless Spring Boot APIs
Maintainability	Layered architecture
Thread Safety	ConcurrentHashMap
Extensibility	Interface-driven design
Testability	Dependency Injection
Fault Tolerance	Global Exception Handling
Performance	Repository implementation
Readability	DTO separation

**Design**

Controller Layer
PurchaseController
Responsibilities
•	Expose REST endpoints
•	Accept HTTP requests
•	Validate request mappings
APIs
Method	Endpoint	Description
POST	/api/v1/purchases	Create purchase
GET	/api/v1/purchases/{id}	Fetch purchase

 Service Layer
PurchaseService
Responsibilities
•	Business logic processing
•	Purchase creation
•	Currency conversion
•	Purchase retrieval
Methods
UUID createPurchase(PurchaseRequest request)
PurchaseResponseObject getPurchase(UUID id, CurrencyCode code)

ExchangeRateService
Responsibilities
•	Fetch exchange rates
•	Determine nearest historical rate
•	Currency conversion support
Methods
BigDecimal getRate(CurrencyCode code, LocalDate purchaseDate)

Repository Layer
PurchaseRepository
Responsibilities
•	Abstract persistence layer
•	CRUD operations
Methods
void save(Purchase purchase)
Optional<Purchase> findById(UUID id)

Repository Implementation
Responsibilities
•	Store purchases in memory
•	Thread-safe access
Storage
ConcurrentHashMap<UUID, Purchase>

Client Layer
TreasuryRateClient
Responsibilities
•	Abstract Treasury integration
•	Provide exchange rate retrieval interface
Methods
List<ExchangeRate> getRates(CurrencyCode code)

DTO Design
PurchaseRequest
Purpose
Request object for purchase creation.
description
transactionDate
amount

PurchaseResponse
Purpose
Return generated purchase UUID.
id

PurchaseResponseObject
Purpose
Detailed purchase response.
id
description
transactionDate
originalAmount
currency
exchangeRate
convertedAmount

Model Design
Purchase
Represents
Purchase domain entity.
Fields
Field	Type
id	UUID
description	String
transactionDate	LocalDate
amount	BigDecimal

ExchangeRate
Represents
Historical Treasury exchange rate.
Fields
Field	Type
date	LocalDate
currencyCode	CurrencyCode
rate	BigDecimal

Enums - CurrencyCode
EUR
GBP
INR


API Example
Create Purchase
Request
POST /api/v1/purchases
{
  "description": "Hotel",
  "transactionDate": "2024-12-15",
  "amount": 100
}

Response
{
  "id": "uuid-value"
}

Get Purchase
Request
GET /api/v1/purchases/{id}?code=EUR

Response
{
  "id": "uuid",
  "description": "Hotel",
  "transactionDate": "2024-12-15",
  "originalAmount": 100,
  "currency": "EUR",
  "exchangeRate": 0.92,
  "convertedAmount": 92
}
<img width="468" height="639" alt="image" src="https://github.com/user-attachments/assets/f5b23776-8da2-486d-889a-415095b1b213" />
