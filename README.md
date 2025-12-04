<span style="color:#2c3e50;">Golf Club API</span>

A Spring Boot REST API for managing golf club Members, Tournaments, and Tournament Participation.

This project demonstrates:

Object-Relational Mapping using Spring Data JPA

Clean layered architecture (Entities, Repositories, Services, Controllers)

PostgreSQL database integration

Docker containerization with Dockerfile and docker-compose

API testing using Postman

GitHub Action for automated Docker image builds and pushes

<span style="color:#8e44ad;">Project Structure</span>
golf-club-api/
 ├── src/main/java/com/chrisking/golfclub/
 │    ├── entity/
 │    ├── repository/
 │    ├── service/
 │    ├── controller/
 │    └── GolfClubApiApplication.java
 ├── src/main/resources/
 │    └── application.properties
 ├── Dockerfile
 ├── docker-compose.yml
 └── README.md

<span style="color:#2980b9;">Features</span>
Members

Auto-generated ID

Email uniqueness enforcement

Search by name, membership type, phone number, or membership start date

Tournaments

Create tournaments with start date, end date, location, entry fee, and prize amount

Search tournaments by start date or location

Member Participation

Add members to tournaments

Retrieve all members in a specific tournament

Docker Integration

Fully containerized API + PostgreSQL database

Persistent storage using Docker volumes

GitHub Actions

Builds and pushes Docker image to Docker Hub on merges to main

<span style="color:#16a085;">Running the Project Locally</span>
Requirements

Java 21+

Maven

PostgreSQL (only if not using Docker)

Steps

Clone the repository

Configure database in src/main/resources/application.properties

Build the project:

mvn clean install


Run the application:

mvn spring-boot:run


API will run at:

http://localhost:8080

<span style="color:#c0392b;">Running with Docker</span>
Requirements

Docker Desktop installed.

Build & Run
docker compose build
docker compose up


Containers created:

golfclub-api (Spring Boot)

golfclub-db (PostgreSQL)

API accessible at:

http://localhost:8080


Database connection inside Docker:

jdbc:postgresql://db:5432/golfclubdb

<span style="color:#d35400;">API Documentation</span>
<span style="color:#e67e22;">Member Endpoints</span>
Create Member
POST /api/members


Example JSON:

{
  "memberName": "Chris King",
  "address": "123 Paradise St",
  "email": "CK101@example.com",
  "phoneNumber": "222-1234",
  "membershipStartDate": "2025-01-01",
  "membershipDurationMonths": 12,
  "membershipType": "GOLD"
}

Get All Members
GET /api/members

Search Members
GET /api/members/search?name=John
GET /api/members/search?phone=555
GET /api/members/search?type=GOLD
GET /api/members/search?startDate=2025-01-01

<span style="color:#e67e22;">Tournament Endpoints</span>
Create Tournament
POST /api/tournaments

Get All Tournaments
GET /api/tournaments

Search Tournaments
GET /api/tournaments/search?startDate=2025-01-01
GET /api/tournaments/search?location=New York

<span style="color:#e67e22;">Member-to-Tournament</span>
Add Member to Tournament
POST /api/tournaments/{tournamentId}/members/{memberId}

Get Members of Tournament
GET /api/tournaments/{tournamentId}/members

<span style="color:#7f8c8d;">Screenshots Required</span>

Include the following in your repository:

Postman API test screenshots

Docker Desktop or terminal showing containers running

GitHub Action build success (if used)

AWS RDS setup steps (if attempted)

API running successfully against AWS RDS (optional)

<span style="color:#8e44ad;">GitHub Actions (Optional CI/CD)</span>

Workflow file:

.github/workflows/docker-publish.yml


The workflow:

Runs on pushes to main

Builds Docker image

Pushes to Docker Hub

Docker image name:

DOCKER_USERNAME/golf-club-api-app:latest


Required GitHub Secrets:

DOCKERHUB_USERNAME

DOCKERHUB_TOKEN

<span style="color:#2c3e50;">Troubleshooting</span>
400 Bad Request

Invalid JSON or missing required fields. Ensure Content-Type: application/json.

404 Not Found

Check URL for trailing spaces or newline characters.

Docker API cannot reach database

Use hostname db, not localhost.

Example:

jdbc:postgresql://db:5432/golfclubdb

GitHub Actions authentication failure

Verify Docker Hub credentials in GitHub Secrets.

<span style="color:#34495e;">License</span>

This project was created for academic purposes as part of a Software Development program.
