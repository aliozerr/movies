# Movie Review Application

## Overview
This Spring Boot application manages movies and their reviews, providing a RESTful API for creating, reading, updating, and deleting movies and reviews.

## Features
- Manage movies (create, read, delete)
- Manage reviews (create, read, update, delete)
- RESTful API endpoints

## API Endpoints

### Movies
- `GET /api/v1/movies`: Fetch all movies
- `GET /api/v1/movies/{imdbId}`: Get a specific movie by IMDB ID
- `POST /api/v1/movies`: Create a new movie
- `DELETE /api/v1/movies/{imdbId}`: Delete a movie by IMDB ID

### Reviews
- `POST /api/v1/reviews`: Create a new review
- `PUT /api/v1/reviews/{imdbId}`: Update an existing review
- `DELETE /api/v1/reviews/{imdbId}/{reviewId}`: Delete a review

## Usage

### Creating a Movie
To create a new movie, send a Post request to `/api/v1/movies` with the following JSON body :

```json
{
    "imdbId": " ",
    "title": " ",
    "releaseDate": " ",
    "trailerLink": " ",
    "poster": " ",
    "genres": [],
    "backdrops": [],
    "reviews": []
}
```

### Creating a Review
To create a new review, send a POST request to `/api/v1/reviews` with the following JSON body:

```json
{
  "reviewBody": " ",
  "imdbId": " "
}
```

### Updating a Review
To update an existing review, send a PUT request to `/api/v1/reviews/{imdbId}` with the following JSON body:

```json
{
  "reviewId": " ",
  "updatedReviewBody": " "
}
```

## Setup and Running

1. Prerequisites:
   - Java JDK 21
   - Maven
   - MongoDB Atlas 

2. Clone the repository:
   ```
   git clone https://github.com/yourusername/movie-review-app.git
   ```

3. Navigate to the project directory:
   ```
   cd movie-review-app
   ```

4. Set up MongoDB Configuration:
   - Create a `.env` file in the project root directory
   - Add the following environment variables to the `.env` file:
     ```
     MONGO_USER=your_mongodb_username
     MONGO_PASSWORD=your_mongodb_password
     MONGO_CLUSTER=your_mongodb_cluster_address
     ```
   - In your `application.properties` or `application.yml` file, add:
     ```
     spring.data.mongodb.uri=mongodb+srv://${env.MONGO_USER}:${env.MONGO_PASSWORD}@${env.MONGO_CLUSTER}
     ```

5. Build the project:
   ```
   mvn clean install
   ```

6. Run the application:
   ```
   mvn spring-boot:run
   ```

## Environment Variables

The application uses the following environment variables for MongoDB configuration:

- `MONGO_USER`: Your MongoDB username
- `MONGO_PASSWORD`: Your MongoDB password
- `MONGO_CLUSTER`: Your MongoDB cluster address

Ensure these variables are set correctly in your `.env` file or your system's environment variables.







