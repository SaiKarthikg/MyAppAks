# Banking Microservices

Multi-module Spring Boot microservices:
- **discovery-service** – Eureka server (port 8761)
- **api-gateway** – Spring Cloud Gateway (port 8080)
- **registration-service** – User registration & login (port 8081)
- **loan-service** – Loan application with up to 2 documents (port 8082)

## Prerequisites
- Java 17+
- Maven 3.9+

## Running locally
Open 4 terminals and run:

```bash
cd discovery-service && mvn spring-boot:run
cd api-gateway && mvn spring-boot:run
cd registration-service && mvn spring-boot:run
cd loan-service && mvn spring-boot:run
```

Eureka UI: http://localhost:8761

### Test via Gateway
Register:
```bash
curl -X POST http://localhost:8080/registration-service/api/v1/registration/register \
  -H "Content-Type: application/json" \
  -d '{"userName":"alice","organizationName":"Alpha Bank","emailId":"alice@example.com","password":"Password@123","confirmPassword":"Password@123"}'
```

Login:
```bash
curl -X POST http://localhost:8080/registration-service/api/v1/registration/login \
  -H "Content-Type: application/json" \
  -d '{"userName":"alice","password":"Password@123"}'
```

Apply Loan:
```bash
curl -X POST http://localhost:8080/loan-service/api/v1/loan/apply \
  -H "Content-Type: multipart/form-data" \
  -F "userName=alice" -F "loanType=HOME_LOAN" -F "requiredAmount=250000" \
  -F "loanPurpose=Home improvement" \
  -F "supportingDocs=@/path/to/doc1.pdf" -F "supportingDocs=@/path/to/doc2.jpg"
```

## Swagger
- Registration: http://localhost:8081/swagger-ui.html
- Loan: http://localhost:8082/swagger-ui.html

## Notes
- H2 in-memory DB with snake_case columns.
- Validation: email format, mandatory fields, password match, loan amount [10000..1000000], max 2 docs.
- Security: dev-friendly (permitAll). Replace with JWT for production.
