# URL Shortener

> [Leia este README em português](README.md)

## Description
This project is a URL shortening service developed in Java using Spring Boot. It allows you to create short URLs that redirect to long addresses, as well as provide access statistics and management of shortened URLs.

## Features
- Create a short URL from a long URL
- Redirect to the original URL using the short code
- Retrieve information and statistics about a shortened URL
- Update the original URL of an existing short code
- Delete a shortened URL

## Project Structure
- **Controller:** exposes the REST API for URL management
- **Service:** contains the business logic for shortening, redirecting, and statistics
- **Entity:** defines the URL data model
- **DTOs:** data transfer objects for requests and responses
- **Repository:** database access

## API Endpoints

- `POST /shorten` — Creates a new short URL
  - Body: `{ "url": "https://example.com" }`
  - Response: shortened URL data

- `GET /shorten/{code}` — Retrieves information about a shortened URL
- `PUT /shorten/{code}` — Updates the original URL of a short code
  - Body: `{ "url": "https://newurl.com" }`
- `DELETE /shorten/{code}` — Deletes a shortened URL
- `GET /shorten/{code}/stats` — Retrieves access statistics
- `GET /shorten/{code}/redirect` — Redirects to the original URL

## Data Model

### Entity `Url`
- `id`: unique identifier
- `url`: original URL
- `shortCode`: generated short code
- `createdAt`: creation date
- `updatedAt`: last update date
- `accessCount`: number of accesses

### DTOs
- **UrlRequest:** `{ "url": "https://example.com" }`
- **UrlResponse:** `{ "id": 1, "url": "https://example.com", "shortCode": "abc123", "createdAt": "2024-07-01T12:00:00Z", "updatedAt": "2024-07-01T12:00:00Z", "accessCount": 10 }`

## Technologies Used
- Java 17
- Spring Boot 3
- Spring Data JPA
- H2 Database (default, in-memory)
- MySQL (optional)
- Lombok
- Maven

## Setup and Execution

1. **Prerequisites:**
   - Java 17+
   - Maven

2. **Database Configuration:**
   - By default, uses in-memory H2. To use MySQL, adjust `application.properties`.

3. **Running the Project:**
   - Via terminal: `./mvnw spring-boot:run` (Linux/Mac) or `mvnw.cmd spring-boot:run` (Windows)

4. **Accessing the H2 Console:**
   - Available at `/h2` while running

## Tests
- Tests are located in `src/test/java/com/nullifidianz/urlShortener/`
