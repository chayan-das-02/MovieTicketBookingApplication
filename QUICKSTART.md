# Movie Ticket Booking System - Quick Start Guide

## Prerequisites
- Java 17 or higher
- Maven 3.8.1 or higher
- Git (optional)

## Step-by-Step Setup

### 1. Build the Project
```bash
cd d:\Code\Java\SpingBoot\MyFirstSpringBoot
mvn clean install
```

### 2. Run the Application
```bash
mvn spring-boot:run
```

The application will start on `http://localhost:8080`

### 3. Access H2 Console (Optional - for database management)
Visit: `http://localhost:8080/h2-console`
- Driver Class: `org.h2.Driver`
- JDBC URL: `jdbc:h2:mem:movieticketdb`
- User Name: `sa`
- Password: (leave empty)

## Sample Workflow

### Step 1: Register a User
```bash
curl -X POST http://localhost:8080/api/v1/users/register \
  -H "Content-Type: application/json" \
  -d '{
    "email": "john@example.com",
    "password": "pass123",
    "firstName": "John",
    "lastName": "Doe",
    "phoneNumber": "9876543210"
  }'
```
**Response (note the userId):**
```json
{
  "userId": 1,
  "email": "john@example.com",
  "firstName": "John",
  "lastName": "Doe",
  "phoneNumber": "9876543210"
}
```

### Step 2: Create a Theater
```bash
curl -X POST http://localhost:8080/api/v1/theaters \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Cineplex Theater",
    "city": "New York",
    "address": "123 Broadway",
    "phoneNumber": "2125551234",
    "email": "cineplex@example.com",
    "totalScreens": 3
  }'
```
**Note the theaterId** for next step.

### Step 3: Create a Screen
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
**Note the screenId** for next step.

### Step 4: Create a Movie
```bash
curl -X POST http://localhost:8080/api/v1/movies \
  -H "Content-Type: application/json" \
  -d '{
    "title": "The Matrix",
    "description": "A computer hacker learns from mysterious rebels about the true nature of his reality and his role in the war against its controllers.",
    "genre": "Science Fiction",
    "durationMinutes": 136,
    "language": "English",
    "rating": "R",
    "director": "The Wachowskis",
    "releaseDate": "2024-02-01T00:00:00"
  }'
```
**Note the movieId** for next step.

### Step 5: Create a Show
```bash
curl -X POST http://localhost:8080/api/v1/shows \
  -H "Content-Type: application/json" \
  -d '{
    "movieId": 1,
    "screenId": 1,
    "startTime": "2024-02-15T18:00:00",
    "endTime": "2024-02-15T19:56:00",
    "ticketPrice": 15.50
  }'
```
**Note the showId** for next step.

### Step 6: Initialize Seats for a Show
```bash
curl -X POST http://localhost:8080/api/v1/seats/show/1/initialize
```

### Step 7: Get Available Seats
```bash
curl -X GET http://localhost:8080/api/v1/seats/show/1/available
```

### Step 8: Book Tickets
```bash
curl -X POST http://localhost:8080/api/v1/bookings/users/1 \
  -H "Content-Type: application/json" \
  -d '{
    "showId": 1,
    "seatIds": [1, 2, 3]
  }'
```
**Response (note the bookingId):**
```json
{
  "bookingId": 1,
  "bookingRef": "BK1705324800123ABC",
  "userId": 1,
  "showId": 1,
  "numberOfSeats": 3,
  "totalPrice": 46.50,
  "status": "PENDING",
  "bookedAt": "2024-01-15T10:00:00"
}
```

### Step 9: Process Payment
```bash
curl -X POST http://localhost:8080/api/v1/payments/process \
  -H "Content-Type: application/json" \
  -d '{
    "bookingId": 1,
    "paymentMethod": "CREDIT_CARD"
  }'
```
**Note the paymentId** for next step.

### Step 10: Confirm Payment
```bash
curl -X PATCH http://localhost:8080/api/v1/payments/1/confirm
```

### Step 11: View Booking Details
```bash
curl -X GET http://localhost:8080/api/v1/bookings/1
```

## Key Endpoints Summary

| Action | Endpoint | Method |
|--------|----------|--------|
| Register User | `/api/v1/users/register` | POST |
| Create Theater | `/api/v1/theaters` | POST |
| Create Screen | `/api/v1/screens` | POST |
| Create Movie | `/api/v1/movies` | POST |
| Create Show | `/api/v1/shows` | POST |
| Initialize Seats | `/api/v1/seats/show/{showId}/initialize` | POST |
| Book Tickets | `/api/v1/bookings/users/{userId}` | POST |
| Get Available Seats | `/api/v1/seats/show/{showId}/available` | GET |
| Process Payment | `/api/v1/payments/process` | POST |
| Confirm Payment | `/api/v1/payments/{paymentId}/confirm` | PATCH |

## Troubleshooting

### Issue: Port 8080 is already in use
**Solution:** Change the port in `application.properties`:
```properties
server.port=8081
```

### Issue: Database connection error
**Solution:** 
1. Check if MySQL is running (if using MySQL configuration)
2. Verify database credentials in `application.properties`
3. Use H2 in-memory database (default) for development

### Issue: Build fails
**Solution:**
```bash
mvn clean
mvn -U clean install
```

## Project Structure Quick Reference

```
MyFirstSpringBoot/
├── pom.xml                          # Maven configuration
├── README.md                        # Full documentation
├── QUICKSTART.md                   # This file
├── .gitignore                       # Git ignore rules
└── src/
    ├── main/
    │   ├── java/
    │   │   └── com/movieticket/
    │   │       ├── entity/          # Database entities
    │   │       ├── dto/             # Data Transfer Objects
    │   │       ├── repository/      # Data access layer
    │   │       ├── service/         # Business logic
    │   │       ├── controller/      # REST controllers
    │   │       └── exception/       # Error handling
    │   └── resources/
    │       └── application.properties   # Configuration
    └── test/
        └── java/
            └── com/movieticket/     # Test classes
```

## Next Steps

1. Read the full [README.md](README.md) for comprehensive documentation
2. Explore the API endpoints using Postman or curl
3. Implement authentication and authorization
4. Add Swagger/OpenAPI documentation
5. Create a frontend application
6. Add more advanced features (coupons, loyalty points, etc.)

## Common HTTP Status Codes

- `200 OK`: Successful request
- `201 Created`: Resource created successfully
- `204 No Content`: Successful deletion
- `400 Bad Request`: Invalid request data
- `404 Not Found`: Resource not found
- `500 Internal Server Error`: Server error

## Need Help?

Refer to:
- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [Spring Data JPA Guide](https://spring.io/projects/spring-data-jpa)
- [REST API Best Practices](https://restfulapi.net/)

---

**Happy Movie Booking!** 🎬🎫
