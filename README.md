## Spring Security Implementation

This repository contains the source code for Spring Security, focusing on implementing authentication and authorization features.

### Overview

The project aims to provide a robust security layer for Java applications using Spring Security. It includes features such as user authentication, role-based access control, JWT token generation, and exception handling.

### Key Features

- **User Authentication**: Implements secure user authentication mechanisms.
- **Authorization**: Defines role-based access control to restrict access to resources.
- **JWT Token Generation**: Generates JSON Web Tokens (JWT) for secure communication.
- **Exception Handling**: Provides custom exception handling for various scenarios.
- **Cross-Origin Resource Sharing (CORS)**: Configures CORS settings for cross-origin requests.

### Modules

1. **Configuration**: Contains Spring Security configuration classes.
2. **Controllers**: Defines controllers for user-related operations.
3. **DTOs**: Data Transfer Objects for request and response models.
4. **Entities**: Entity classes representing user information.
5. **Repositories**: Interfaces for database interaction.
6. **Services**: Business logic implementations.
7. **Utilities**: Utility classes for JWT token handling and exception management.

### Usage

1. Clone the repository.
2. Configure database settings in `application.properties`.
3. Run the application.

### Endpoints

- Register a new user: `POST /user/register`
- Login: `POST /user/login`
- Retrieve all users: `GET /user/all`
- Reset user password: `POST /user/resetPassword/{userId}`
- Change user status: `POST /user/status/{userId}`

### Technologies Used

- Java
- Spring Boot
- Spring Security
- JSON Web Tokens (JWT)
- Hibernate
- MySQL

### Contributors

- [Md. Taslim Hosen Shanto] - [https://github.com/Shanto06]

### License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details.
