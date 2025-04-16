# Loan Payment Application

## Building and Running the Application

### Prerequisites

* Java 17 or later
* Maven
* Spring Boot

### Building the Application

To build the application, navigate to the project root directory and run the following command:

mvn clean package

### Running the Application

To run the application, navigate to the project root directory and run the following command:

mvn spring-boot:run

You can also run the application using the generated JAR file:

java -jar target/loan-payment-application.jar

### Accessing the Application

Once the application is running, you can access it at http://localhost:8080. You can use a tool like Postman or cURL to test the API endpoints.

Swagger UI

The application includes Swagger UI, which provides a graphical interface for testing the API endpoints. You can access Swagger UI at http://localhost:8080/v2/api-docs.


Example Request s

Loan
{
  "loanId": 1,
  "loanAmount": 1000.00,
  "term": 12,
  "status": "ACTIVE"
}

Payment
{
  "paymentId": 1,
  "loanId": 1,
  "paymentAmount": 500.00
}

Example Loan Response
{
	"loanId": 1,
	"loanAmount": 0,
	"term": 12,
	"status": "SETTLED"
}

Troubleshooting

If you encounter any issues while building or running the application, check the console output for error messages. You can also try cleaning the project directory and rebuilding the application.
