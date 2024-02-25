## PolyGroceManager Spring Security

This repository contains the source code for the PolyGroceManager application, focusing on implementing Spring Security for authentication and authorization.

### Features

- **User Authentication**: Implements user authentication using Spring Security.
- **Role-based Access Control**: Defines roles for users (ADMIN, STAFF) and restricts access to certain endpoints based on these roles.
- **JWT Token Generation**: Generates JWT tokens upon user authentication for secure communication between client and server.
- **Exception Handling**: Provides custom exception handling for various scenarios such as user not found, invalid credentials, etc.
- **Cross-Origin Resource Sharing (CORS)**: Configures CORS settings to allow cross-origin requests.

### Modules

1. **Spring Security Configuration**: Configures Spring Security settings for authentication, authorization, and session management.
2. **Controllers**: Contains controllers for user-related operations such as registration, login, password reset, and status change.
3. **DTOs**: Data Transfer Objects representing request and response models for user authentication and management.
4. **Entities**: Defines JPA entities representing user information in the system.
5. **Repositories**: Repository interfaces for interacting with the database.
6. **Services**: Service interfaces and implementations for managing user-related operations.
7. **Utils**: Utility classes for JWT token handling, role definition, and global exception handling.

### Setup

1. Clone the repository to your local machine.
2. Configure your database settings in `application.properties`.
3. Run the application.

### Usage

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
