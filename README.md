# Employee Management System

## Overview
A Spring Boot REST API for managing employee records.

## Features
- Create employee
- Get all employees
- Get employee by ID
- Update employee
- Delete employee
- Search employees
- Pagination and sorting

## Tech Stack
- Java
- Spring Boot
- Spring Data JPA
- MySQL
- Maven
- Swagger

## How to Run
1. Clone the repository
2. Configure MySQL database
3. Update application.properties
4. Run the Spring Boot application

## API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/employees` | Create a new employee |
| GET | `/employees` | Get all employees |
| GET | `/employees/{id}` | Get employee by ID |
| PUT | `/employees/{id}` | Update employee |
| DELETE | `/employees/{id}` | Delete employee |

## Implementation Details

- RESTful API architecture
- CRUD operations for employee management
- DTO pattern for data transfer
- Bean Validation for request validation
- Global Exception Handling
- Pagination and Sorting
- Search functionality
- ModelMapper for entity-DTO conversion
- Swagger/OpenAPI for API documentation

 ## Database Configuration

This project uses MySQL as the database.

1. Create a MySQL database.
2. Update the database credentials in `application.properties`.
3. Run the application. 
