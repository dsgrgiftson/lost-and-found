# lost-and-found

## Overview
The `lost-and-found` project is a Spring Boot application designed to manage lost and found items. It provides functionalities to upload PDF files containing lost items data, extract the information, and store it in a database.

## Features
- Upload PDF files containing lost items data.
- Extract and parse lost items information from PDF files.
- Store and manage lost items data in a database.
- RESTful API endpoints for interacting with the lost and found data.
- Swagger integration for API documentation.

## Technologies Used
- Java
- Spring Boot
- Maven
- Apache PDFBox
- Lombok
- H2 Database
- Spring Data JPA
- Springdoc OpenAPI

## Prerequisites
- Java 11 or higher
- Maven 3.6.0 or higher

## Setup and Installation
1. **Clone the repository:**
   ```sh
   git clone https://github.com/your-username/lost-and-found.git
   cd lost-and-found
   ```
2. **Build the project:**
   ```sh
   mvn clean install -DskipTests
   ```
3. **Run the application:**
   ```sh
   mvn spring-boot:run
    ```
4. **Access the application:**
5. Open a web browser and go to `http://localhost:8080/swagger-ui.html` to view the Swagger UI documentation.
6. Use the Swagger UI to interact with the API endpoints.
7. You can also use tools like Postman to interact with the API endpoints. A sample collection is added to the relative path from the project root `test-data/lost-and-found.postman_collection.json`.
8. To stop the application, press `Ctrl + C` in the terminal.

## Areas of improvement
- Implement a more robust PDF parsing mechanism.
- Add authentication and authorization mechanisms.
- Implement a frontend application for better user interaction.
- Add proper unit and integration tests. And if possible functional tests.
- Add more features like search, filtering, and sorting of lost items data.
- Improve error handling and validation.