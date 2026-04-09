# Movie Ticket Booking System - Spring Boot

A comprehensive Spring Boot application for managing movie ticket bookings. This system allows users to browse movies, select shows, book seats, and process payments.

## Features

### Core Features
- **User Management**: User registration, profile management, and authentication
- **Movie Management**: Create and manage movies with details like genre, language, rating, and duration
- **Theater Management**: Manage theaters across different cities with multiple screens
- **Screen Management**: Configure screens with seat layouts of different types (Standard, IMAX, Dolby)
- **Show Management**: Schedule movie shows with different time slots and ticket prices
- **Seat Management**: Manage seat availability, types, and booking status
- **Booking Management**: Book seats for shows with automatic seat allocation and confirmation
- **Payment Processing**: Process payments with support for multiple payment methods
- **Inventory Management**: Track available and booked seats in real-time

## Technology Stack

- **Java 17**: Latest Java LTS version
- **Spring Boot 3.2.0**: Latest Spring Boot framework
- **Spring Data JPA**: ORM and database access
- **MySQL 8.0**: Primary database (H2 for development)
- **Lombok**: Reduce boilerplate code
- **MapStruct**: DTO mapping
- **Validation**: Input validation with Jakarta Validation

## Project Structure

```
src/
├── main/
│   ├── java/com/movieticket/
│   │   ├── entity/              # JPA entities
│   │   │   ├── User.java
│   │   │   ├── Movie.java
│   │   │   ├── Theater.java
│   │   │   ├── Screen.java
│   │   │   ├── Show.java
│   │   │   ├── Seat.java
│   │   │   ├── Booking.java
│   │   │   ├── BookingDetail.java
│   │   │   └── Payment.java
│   │   ├── dto/                 # Data Transfer Objects
│   │   │   ├── UserDTO.java
│   │   │   ├── MovieDTO.java
│   │   │   ├── TheaterDTO.java
│   │   │   ├── ScreenDTO.java
│   │   │   ├── ShowDTO.java
│   │   │   ├── SeatDTO.java
│   │   │   ├── BookingDTO.java
│   │   │   ├── BookingRequest.java
│   │   │   ├── PaymentDTO.java
│   │   │   └── PaymentRequest.java
│   │   ├── repository/          # Spring Data JPA repositories
│   │   │   ├── UserRepository.java
│   │   │   ├── MovieRepository.java
│   │   │   ├── TheaterRepository.java
│   │   │   ├── ScreenRepository.java
│   │   │   ├── ShowRepository.java
│   │   │   ├── SeatRepository.java
│   │   │   ├── BookingRepository.java
│   │   │   ├── BookingDetailRepository.java
│   │   │   └── PaymentRepository.java
│   │   ├── service/             # Business logic
│   │   │   ├── UserService.java
│   │   │   ├── MovieService.java
│   │   │   ├── TheaterService.java
│   │   │   ├── ScreenService.java
│   │   │   ├── ShowService.java
│   │   │   ├── SeatService.java
│   │   │   ├── BookingService.java
│   │   │   └── PaymentService.java
│   │   ├── controller/          # REST endpoints
│   │   │   ├── UserController.java
│   │   │   ├── MovieController.java
│   │   │   ├── TheaterController.java
│   │   │   ├── ScreenController.java
│   │   │   ├── ShowController.java
│   │   │   ├── SeatController.java
│   │   │   ├── BookingController.java
│   │   │   └── PaymentController.java
│   │   ├── exception/           # Exception handling
│   │   │   └── GlobalExceptionHandler.java
│   │   └── MovieTicketBookingApplication.java  # Main application class
│   └── resources/
│       └── application.properties  # Application configuration
```

## API Endpoints

### User Management
```
POST   /api/v1/users/register           - Register a new user
GET    /api/v1/users/{userId}           - Get user by ID
GET    /api/v1/users/email/{email}      - Get user by email
GET    /api/v1/users                    - Get all users
PUT    /api/v1/users/{userId}           - Update user
DELETE /api/v1/users/{userId}           - Delete user
```

### Movie Management
```
POST   /api/v1/movies                   - Create a movie
GET    /api/v1/movies/{movieId}         - Get movie by ID
GET    /api/v1/movies                   - Get all movies
GET    /api/v1/movies/by-genre/{genre}  - Get movies by genre
GET    /api/v1/movies/by-language/{language} - Get movies by language
PUT    /api/v1/movies/{movieId}         - Update movie
DELETE /api/v1/movies/{movieId}         - Delete movie
```

### Theater Management
```
POST   /api/v1/theaters                 - Create theater
GET    /api/v1/theaters/{theaterId}     - Get theater by ID
GET    /api/v1/theaters                 - Get all theaters
GET    /api/v1/theaters/by-city/{city}  - Get theaters by city
PUT    /api/v1/theaters/{theaterId}     - Update theater
DELETE /api/v1/theaters/{theaterId}     - Delete theater
```

### Screen Management
```
POST   /api/v1/screens                  - Create screen
GET    /api/v1/screens/{screenId}       - Get screen by ID
GET    /api/v1/screens                  - Get all screens
GET    /api/v1/screens/theater/{theaterId} - Get screens by theater
PUT    /api/v1/screens/{screenId}       - Update screen
DELETE /api/v1/screens/{screenId}       - Delete screen
```

### Show Management
```
POST   /api/v1/shows                    - Create show
GET    /api/v1/shows/{showId}           - Get show by ID
GET    /api/v1/shows                    - Get all shows
GET    /api/v1/shows/movie/{movieId}    - Get shows by movie
GET    /api/v1/shows/movie/{movieId}/upcoming - Get upcoming shows
GET    /api/v1/shows/status/{status}    - Get shows by status
PATCH  /api/v1/shows/{showId}/status    - Update show status
DELETE /api/v1/shows/{showId}           - Delete show
```

### Seat Management
```
POST   /api/v1/seats/show/{showId}/initialize - Initialize seats for show
GET    /api/v1/seats/{seatId}           - Get seat by ID
GET    /api/v1/seats/show/{showId}      - Get all seats for show
GET    /api/v1/seats/show/{showId}/available - Get available seats
GET    /api/v1/seats/show/{showId}/status/{status} - Get seats by status
GET    /api/v1/seats/show/{showId}/booked-count - Count booked seats
```

### Booking Management
```
POST   /api/v1/bookings/users/{userId}  - Create booking
GET    /api/v1/bookings/{bookingId}     - Get booking by ID
GET    /api/v1/bookings/ref/{bookingRef} - Get booking by reference
GET    /api/v1/bookings/user/{userId}   - Get user bookings
GET    /api/v1/bookings/status/{status} - Get bookings by status
PATCH  /api/v1/bookings/{bookingId}/confirm - Confirm booking
PATCH  /api/v1/bookings/{bookingId}/cancel - Cancel booking
```

### Payment Management
```
POST   /api/v1/payments/process         - Process payment
GET    /api/v1/payments/{paymentId}     - Get payment by ID
GET    /api/v1/payments/transaction/{transactionId} - Get payment by transaction
GET    /api/v1/payments/booking/{bookingId} - Get payment by booking
PATCH  /api/v1/payments/{paymentId}/confirm - Confirm payment
PATCH  /api/v1/payments/{paymentId}/fail - Fail payment
```

## Getting Started

### Prerequisites
- Java 17 or higher
- Maven 3.8.1 or higher
- MySQL 8.0 (optional, H2 is used by default for development)

### Installation

1. **Clone the repository**
```bash
git clone <repository-url>
cd MyFirstSpringBoot
```

2. **Build the project**
```bash
mvn clean install
```

3. **Run the application**
```bash
mvn spring-boot:run
```

The application will start on `http://localhost:8080`

### Database Configuration

**For Development (H2 Database - Default):**
- No additional setup needed
- Access H2 Console at: `http://localhost:8080/h2-console`

**For Production (MySQL):**
Edit `application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/movieticketdb
spring.datasource.username=root
spring.datasource.password=your_password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
```

## Usage Examples

### 1. Register a User
```bash
curl -X POST http://localhost:8080/api/v1/users/register \
  -H "Content-Type: application/json" \
  -d '{
    "email": "user@example.com",
    "password": "password123",
    "firstName": "John",
    "lastName": "Doe",
    "phoneNumber": "1234567890"
  }'
```

### 2. Create a Movie
```bash
curl -X POST http://localhost:8080/api/v1/movies \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Inception",
    "description": "A mind-bending thriller",
    "genre": "Science Fiction",
    "durationMinutes": 148,
    "language": "English",
    "rating": "PG13",
    "director": "Christopher Nolan",
    "releaseDate": "2024-01-01T00:00:00"
  }'
```

### 3. Create a Theater
```bash
curl -X POST http://localhost:8080/api/v1/theaters \
  -H "Content-Type: application/json" \
  -d '{
    "name": "PVR Cinemas",
    "city": "New York",
    "address": "123 Main Street",
    "phoneNumber": "5551234567",
    "email": "pvr@example.com",
    "totalScreens": 5
  }'
```

### 4. Create a Screen
```bash
curl -X POST http://localhost:8080/api/v1/screens \
  -H "Content-Type: application/json" \
  -d '{
    "theaterId": 1,
    "screenName": "Screen 1",
    "totalSeats": 100,
    "totalRows": 10,
    "seatsPerRow": 10,
    "screenType": "STANDARD"
  }'
```

### 5. Create a Show
```bash
curl -X POST http://localhost:8080/api/v1/shows \
  -H "Content-Type: application/json" \
  -d '{
    "movieId": 1,
    "screenId": 1,
    "startTime": "2024-01-15T18:00:00",
    "endTime": "2024-01-15T20:28:00",
    "ticketPrice": 12.99
  }'
```

### 6. Initialize Seats for Show
```bash
curl -X POST http://localhost:8080/api/v1/seats/show/1/initialize
```

### 7. Book Tickets
```bash
curl -X POST http://localhost:8080/api/v1/bookings/users/1 \
  -H "Content-Type: application/json" \
  -d '{
    "showId": 1,
    "seatIds": [1, 2, 3]
  }'
```

### 8. Process Payment
```bash
curl -X POST http://localhost:8080/api/v1/payments/process \
  -H "Content-Type: application/json" \
  -d '{
    "bookingId": 1,
    "paymentMethod": "CREDIT_CARD"
  }'
```

## Configuration

### Logging
The application is configured with DEBUG logging for the movieticket package. Adjust in `application.properties`:
```properties
logging.level.com.movieticket=DEBUG
```

### Database
- **DDL Strategy**: `create-drop` (creates tables on startup, drops on shutdown for development)
- **SQL Formatting**: Enabled for better readability
- **Batch Size**: 20 (for performance optimization)

## Error Handling

The application includes comprehensive error handling with detailed error messages:

```json
{
  "timestamp": "2024-01-15T10:30:00",
  "status": 400,
  "error": "Bad Request",
  "message": "Email already registered",
  "path": "/api/v1/users/register"
}
```

## Transaction Management

All service methods are marked with `@Transactional` to ensure data consistency. This is crucial for operations like booking where multiple entities need to be updated atomically.

## Future Enhancements

1. **Authentication & Authorization**
   - JWT-based authentication
   - Role-based access control (Admin, User, Operator)

2. **Advanced Features**
   - Seat selection with interactive layout
   - Booking confirmation emails
   - Refund processing
   - Loyalty points system
   - Promotional discounts and coupons

3. **Performance**
   - Caching with Redis
   - Query optimization and indexing
   - Rate limiting

4. **Frontend Integration**
   - RESTful API documentation with Swagger/OpenAPI
   - Angular or React UI

5. **Testing**
   - Unit tests with JUnit 5
   - Integration tests with TestContainers
   - Load testing

## Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## License

This project is licensed under the MIT License - see the LICENSE file for details.

## Support

For issues, questions, or suggestions, please create an issue in the repository.

## Contact

For more information, please contact the development team.

---

**Happy Coding!** 🎬🎫
