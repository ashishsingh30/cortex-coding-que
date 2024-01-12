# cortex-coding-que

**Overview**
This project integrates a Spring Boot application with Salesforce, providing synchronization of customer data between a SQL Server database and Salesforce. It includes secure API endpoints, JWT-based authentication, and scheduled data synchronization.

**Features**
RESTful API endpoints for customer management.
Integration with Salesforce for data synchronization.
JWT-based authentication and role-based access control.
Scheduled and manual triggers for data synchronization.

**Configuration**

**Salesforce Integration**
Create a Connected App in Salesforce for API integration.
Obtain Consumer Key and Consumer Secret for OAuth authentication.
Use Salesforce REST API for data access.

**Security**
Secure API endpoints using Spring Security.
Implement JWT for authentication and authorization.
Admin role with full access, User role with restricted access.

**Dependencies**
Spring Boot Starter Web
Spring Boot Starter Security
JSON Web Token (JWT)
Salesforce SDK or REST API client

**Setup**
Add Dependencies: Include necessary dependencies in pom.xml or build.gradle.
Configure Application Properties: Store Salesforce credentials in application.properties.
Implement UserDetailsService: Load user-specific data for authentication.
Configure Security: Set up SecurityConfig for JWT and role-based access control.
Salesforce OAuth Client: Implement a client for Salesforce OAuth authentication.
Data Synchronization Service: Create a service to synchronize data between Salesforce and the SQL Server database.

**API Endpoints**
POST /api/customers: Create a new customer.
GET /api/customers/{id}: Get a customer by ID.
GET /api/customers: Get all customers.
PUT /api/customers/{id}: Update a customer by ID.
DELETE /api/customers/{id}: Delete a customer by ID.
POST /sync: Manually trigger data synchronization (Admin only).

**Scheduling Data Synchronization**
Use @Scheduled annotation to set up automatic synchronization at regular intervals.

**Security Configuration**
Restrict /sync endpoint to Admin role.
Configure JWT filter for secure API access.

**Running the Application**
Build and run the Spring Boot application.
Use Swagger UI for API documentation and testing.
