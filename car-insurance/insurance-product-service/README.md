# Insurance Product Service (Spring Boot MVC)

A minimal Spring Boot Web MVC application exposing an endpoint to get all insurance products.

Features:
- GET /products returns a list of products with fields: productId, productName, price, benefits.

Quick start (requires Java 17+ and Maven):

1. Build

```
mvn -q -f ./pom.xml -DskipTests package
```

2. Run

```
mvn -q -f ./pom.xml spring-boot:run
```

3. Try it

```
curl http://localhost:8080/products
```

Project structure:
- src/main/java/com/example/insurance/product
  - InsuranceProductServiceApplication.java
  - controller/ProductController.java
  - model/Product.java
  - service/ProductService.java
- src/test/java/com/example/insurance/product/ProductControllerTest.java

Notes:
- Uses in-memory data store seeded in ProductService.
- JSON responses provided by Spring MVC with Jackson.

