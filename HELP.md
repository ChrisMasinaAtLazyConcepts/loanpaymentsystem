# 🏦 Loan Payment Application

A modern, secure loan payment processing system built with Spring Boot.

## 📋 Table of Contents

- [Prerequisites](#prerequisites)
- [Building](#building)
- [Running](#running)
- [API Documentation](#api-documentation)
- [Examples](#examples)
- [Troubleshooting](#troubleshooting)

## 🛠 Prerequisites

Before you begin, ensure you have the following installed:

- ☕ Java 1.8 or later
- 📦 Maven
- 🌱 Spring Boot

## 🏗 Building

Navigate to the project root directory and run:

```bash
mvn clean package
```

## 🚀 Running

### Option 1: Using Maven

```bash
mvn spring-boot:run
```

### Option 2: Using JAR

```bash
java -jar target/loan-payment-application.jar
```

The application will be available at:
> 🌐 [http://localhost:8080](http://localhost:8080)

## 📚 API Documentation

### Swagger UI

Explore and test the API using Swagger UI:
> 📘 [http://localhost:8080/v2/api-docs](http://localhost:8080/v2/api-docs)

## 💡 Examples

### Loan Request

```json
{
  "loanId": 1,
  "loanAmount": 1000.00,
  "term": 12,
  "status": "ACTIVE"
}
```

### Payment Request

```json
{
  "paymentId": 1,
  "loanId": 1,
  "paymentAmount": 500.00
}
```

### Loan Response

```json
{
  "loanId": 1,
  "loanAmount": 0,
  "term": 12,
  "status": "SETTLED"
}
```

## ⚠️ Troubleshooting

If you encounter issues:

1. Check console output for error messages
2. Verify all prerequisites are installed correctly
3. Try cleaning the project:
   ```bash
   mvn clean
   ```
4. Rebuild the application:
   ```bash
   mvn clean package
   ```

## 🔒 Security

- All endpoints DONT require authentication
- 
## 📊 Monitoring

The application includes:
- Health checks at `/actuator/health`
- Metrics at `/actuator/metrics`
