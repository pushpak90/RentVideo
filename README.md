# RentVideo

RentVideo is a RESTful API service built with Spring Boot, Java 21, Gradle, and MySQL to manage an online video rental system.

## Features

- User registration and login with BCrypt password hashing
- Basic Authentication for secure access
- Role-based authorization (CUSTOMER and ADMIN)
- Public endpoints: user registration and login
- Private endpoints: browse videos (CUSTOMER, ADMIN), manage videos (ADMIN only)
- Video management: create, update, delete, and view videos
- MySQL for data persistence

## Technologies

- Spring Boot 3.x
- Java 21
- Spring Data JPA
- Spring Security (Basic Auth)
- MySQL
- Gradle build tool

## Getting Started

### Prerequisites

- Java 21
- Gradle (use provided wrapper `./gradlew`)
- MySQL 8.x

### Installation

1. Clone the repo:
- git clone https://github.com/your-username/RentVideo.git
- cd RentVideo

## API Endpoints

### Public

- `POST /api/auth/register` — Register new user
- `POST /api/auth/login` — Login (uses Basic Auth headers)

### Private

- `GET /api/videos` — List all available videos (CUSTOMER & ADMIN)
- `POST /api/videos` — Create new video (ADMIN only)
- `PUT /api/videos/{id}` — Update video (ADMIN only)
- `DELETE /api/videos/{id}` — Delete video (ADMIN only)

## Authentication

- Use Basic Auth with your registered email as username and password.
- Passwords are securely stored using BCrypt hashing.

## Project Structure

