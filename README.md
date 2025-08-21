# Employee Management System

A robust RESTful API for managing employees and departments, built with Spring Boot and modern Java technologies.

## 📋 Table of Contents
- [Introduction](#introduction)
- [Technologies Used](#technologies-used)
- [Features](#features)
- [Architecture & Design Decisions](#architecture--design-decisions)
- [Getting Started](#getting-started)

## 🚀 Introduction

This is a comprehensive RESTful API built with Spring Boot that manages employee and department data. The application is designed to be scalable, secure, and performant, following modern backend development best practices. It includes features like JWT authentication, pagination, caching, and robust exception handling.

## 🛠 Technologies Used

- **Java 21**: The core language for the application
- **Spring Boot 3.x**: Framework for rapid application development
- **MySQL**: Relational database for data persistence
- **Spring Data JPA**: For database operations and ORM
- **Spring Security**: For securing API endpoints
- **JWT (JSON Web Tokens)**: For stateless authentication
- **Spring Cache**: For caching frequently accessed data
- **Flyway**: For database schema migrations
- **Lombok**: For reducing boilerplate code
- **Maven**: For dependency management and building
- **Postman**: For API testing and documentation

## ✨ Features

- **Authentication & Authorization**: Secure user registration, login, and access control using JWT
- **Employee Management**: Full CRUD operations for employees
- **Department Management**: Manage organizational departments
- **Pagination**: Efficient data retrieval with paginated results
- **API Caching**: Performance optimization using Spring's caching mechanism
- **Robust Exception Handling**: Custom global exception handling with consistent error responses
- **Data Validation**: Comprehensive input validation with custom error messages

## 🏗 Architecture & Design Decisions

### Database Management with Flyway
Flyway handles database migrations, ensuring schema versioning and reproducibility. Just create an empty database, and Flyway will automatically apply all schema changes and seed data on application startup.

### Entity Relationships
The application implements a one-to-many relationship between Department and Employee, reflecting a practical real-world scenario where a single department can have multiple employees.

### Security with JWT
The application uses JWT for stateless authentication. Access tokens are short-lived for security, while refresh tokens allow for seamless user experience without frequent re-authentication.

### API Response Standardization
All API responses follow a consistent format with proper HTTP status codes, success messages, and error handling.

## 🚦 Getting Started

### Prerequisites
- Java 21 JDK
- MySQL Database (version 8.0 or later)
- Maven (optional, as the project includes Maven Wrapper)
- Postman (for API testing)

### Installation & Setup

1. **Clone the repository**:
   ```bash
   git clone https://github.com/your-username/employee-management-system.git
   cd employee-management-system

2. **Database Setup**:
- Create a new empty MySQL database named employee_db
- Update the database credentials in src/main/resources/application.properties:
    ```bash
    spring.datasource.url=jdbc:mysql://localhost:3306/employee_db
    spring.datasource.username=your_username
    spring.datasource.password=your_password

3. **Run the Application**:
    ```bash
    ./mvnw spring-boot:run

