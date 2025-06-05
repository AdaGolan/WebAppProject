# Photo Gallery Application

A Spring Boot-based web application for sharing and managing photos with social features.

## Features

- **User Management**
  - User registration and authentication
  - JWT-based security
  - User profiles

- **Photo Management**
  - Photo upload and storage
  - Photo metadata management
  - Photo categories and tags

- **Social Features**
  - Photo ratings (1-5 stars)
  - Comments on photos
  - Notifications for interactions

- **Category Management**
  - Create and manage photo categories
  - Assign photos to categories
  - Browse photos by category

## Technical Stack

- **Backend**
  - Spring Boot 2.7.4
  - Spring Security with JWT
  - Spring Data JPA
  - H2 Database (in-memory)

- **Frontend**
  - RESTful API endpoints
  - JSON data format

## Getting Started

### Prerequisites

- Java 17 or higher
- Maven 3.6 or higher

### Installation

1. Clone the repository:
```bash
git clone [repository-url]
```

2. Navigate to the project directory:
```bash
cd wap-initial-project
```

3. Build the project:
```bash
mvn clean install
```

4. Run the application:
```bash
mvn spring-boot:run
```

The application will start on `http://localhost:8080`

### Database Access

The H2 console is available at `http://localhost:8080/h2-console` with the following credentials:
- JDBC URL: `jdbc:h2:mem:photodb`
- Username: `sa`
- Password: (empty)

## API Endpoints

### Authentication
- `POST /api/auth/register` - Register a new user
- `POST /api/auth/login` - Login and get JWT token

### Photos
- `POST /api/photos` - Upload a new photo
- `GET /api/photos` - Get all photos
- `GET /api/photos/{id}` - Get photo by ID
- `DELETE /api/photos/{id}` - Delete a photo

### Categories
- `POST /api/categories` - Create a new category
- `GET /api/categories` - Get all categories
- `GET /api/categories/{id}` - Get category by ID
- `GET /api/categories/{id}/photos` - Get photos in a category

### Comments
- `POST /api/photos/{photoId}/comments` - Add a comment to a photo
- `GET /api/photos/{photoId}/comments` - Get all comments for a photo
- `DELETE /api/comments/{id}` - Delete a comment

### Ratings
- `POST /api/photos/{photoId}/ratings` - Rate a photo
- `GET /api/photos/{photoId}/ratings` - Get all ratings for a photo
- `GET /api/photos/{photoId}/ratings/average` - Get average rating for a photo
- `GET /api/photos/{photoId}/ratings/count` - Get rating count for a photo
- `DELETE /api/photos/ratings/{ratingId}` - Remove a rating

### Notifications
- `GET /api/notifications` - Get user notifications
- `PUT /api/notifications/{id}/read` - Mark notification as read
- `DELETE /api/notifications/{id}` - Delete a notification

## Request/Response Examples

### Register User
```json
POST /api/auth/register
{
    "username": "user123",
    "email": "user@example.com",
    "password": "password123"
}
```

### Upload Photo
```json
POST /api/photos
{
    "title": "My Photo",
    "description": "A beautiful sunset",
    "categoryId": 1
}
```

### Add Comment
```json
POST /api/photos/1/comments
{
    "content": "Great photo!"
}
```

### Rate Photo
```json
POST /api/photos/1/ratings
{
    "userId": 1,
    "rating": 5
}
```

## Security

The application uses JWT (JSON Web Tokens) for authentication. Include the JWT token in the Authorization header for protected endpoints:
```
Authorization: Bearer <your-jwt-token>
```

## Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## License

This project is licensed under the MIT License - see the LICENSE file for details. 