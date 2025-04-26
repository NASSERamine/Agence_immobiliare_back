# Backend Agence Immobilière

This is the backend application for the Real Estate Agency Management System, built with Spring Boot and Spring Security.

## Features

- User authentication and authorization with JWT
- Role-based access control (Admin, Agent, User)
- CRUD operations for properties
- Property search by various criteria
- Image management for properties
- Centralized exception handling
- Logging system
- Input validation
- CORS configuration

## Technologies Used

- Spring Boot 3.2.3
- Spring Security
- Spring Data JPA
- PostgreSQL
- JWT (JSON Web Tokens)
- MapStruct
- Lombok
- Maven

## Prerequisites

- Java 17 or higher
- Maven 3.6 or higher
- PostgreSQL 12 or higher

## Installation

1. Clone the repository
2. Create a PostgreSQL database named `agence_immobiliere`
3. Update the database configuration in `application.properties` if needed
4. Run the application using Maven:
   ```bash
   mvn spring-boot:run
   ```

## API Endpoints

### Authentication

- `POST /api/auth/signin` - User login
- `POST /api/auth/signup` - User registration

### Users

- `GET /api/users` - Get all users (Admin only)
- `GET /api/users/{id}` - Get user by ID
- `POST /api/users` - Create new user (Admin only)
- `PUT /api/users/{id}` - Update user
- `DELETE /api/users/{id}` - Delete user (Admin only)

### Properties

- `GET /api/properties` - Get all properties
- `GET /api/properties/{id}` - Get property by ID
- `POST /api/properties` - Create new property (Agent/Admin only)
- `PUT /api/properties/{id}` - Update property (Agent/Admin only)
- `DELETE /api/properties/{id}` - Delete property (Agent/Admin only)
- `GET /api/properties/type/{type}` - Get properties by type
- `GET /api/properties/status/{status}` - Get properties by status
- `GET /api/properties/city/{city}` - Get properties by city
- `GET /api/properties/price-range` - Get properties by price range
- `GET /api/properties/bedrooms/{bedrooms}` - Get properties by number of bedrooms
- `GET /api/properties/user/{userId}` - Get properties by user ID (Agent/Admin only)

## Security

The application uses JWT for authentication. To access protected endpoints, include the JWT token in the Authorization header:

```
Authorization: Bearer <token>
```

## Error Handling

The application uses a centralized exception handling mechanism. All errors are returned in a consistent format:

```json
{
    "status": <http_status_code>,
    "message": "<error_message>"
}
```

## Logging

The application uses SLF4J for logging. Logs are configured to show:
- Security-related events
- Application errors
- Database operations

## Contributing

1. Fork the repository
2. Create your feature branch
3. Commit your changes
4. Push to the branch
5. Create a new Pull Request

## License

This project is licensed under the MIT License. 