# Golf Club API

A Spring Boot REST API for managing golf club Members, Tournaments, and Tournament Participation.

This project demonstrates:

- Object-Relational Mapping using Spring Data JPA  
- Clean layered architecture (Entities, Repositories, Services, Controllers)  
- PostgreSQL database integration  
- Docker containerization (Dockerfile and docker-compose)  
- API testing using Postman  
- GitHub Action for automatically building and pushing Docker images

  ## Project Screenshots are in the Screenshots folder in project root.

---

## Project Structure

```text
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
```
Features
Members

    Create new members with auto-generated ID

    Email uniqueness enforcement

    Search capabilities:

        By name

        By membership type

        By phone number

        By membership start date

Tournaments

    Create tournaments with:

        Start date

        End date

        Location

        Entry fee

        Cash prize amount

    Search tournaments by:

        Start date

        Location

Member Participation

    Associate members with specific tournaments

    Retrieve all members participating in a tournament

Docker Integration

    Fully containerized application

    Includes:

        Application container (Spring Boot API)

        PostgreSQL database container

    Persistent PostgreSQL storage using Docker volumes

GitHub Actions / Cloud

    GitHub Action builds and pushes Docker image to Docker Hub on merges to main

    Spring Boot configuration supports Amazon RDS PostgreSQL

    Documentation for steps taken toward cloud deployment

Running the Project Locally
Requirements

    Java 21 or higher

    Maven

    PostgreSQL (only needed if not using Docker)

Steps

    Clone the repository

    Configure the database connection in:

src/main/resources/application.properties

Build the project:

mvn clean install

Run the application from IntelliJ or via command line:

mvn spring-boot:run

The API will be available at:

    http://localhost:8080

Running the Project with Docker
Requirements

    Docker Desktop installed

Build and Run

From the project root:

docker compose build
docker compose up

The following containers will launch:

    golfclub-api: Spring Boot application

    golfclub-db: PostgreSQL database

The API is accessible at:

http://localhost:8080

The application connects to the Postgres database inside Docker via:

jdbc:postgresql://db:5432/golfclubdb

API Documentation
Member Endpoints
Create Member

POST /api/members

Example JSON body:

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

Tournament Endpoints
Create Tournament

POST /api/tournaments

Get All Tournaments

GET /api/tournaments

Search Tournaments

GET /api/tournaments/search?startDate=2025-01-01
GET /api/tournaments/search?location=New York

Member-to-Tournament Endpoints
Add Member to Tournament

POST /api/tournaments/{tournamentId}/members/{memberId}

Get Members of a Tournament

GET /api/tournaments/{tournamentId}/members

Screenshots

Include the following screenshots for instructor review:

    Postman tests for all required endpoints

    Docker Desktop or terminal showing running containers

    GitHub Action build success (if used)

    AWS RDS setup steps (if attempted)

    Application successfully running against AWS RDS (optional)

GitHub Actions (Optional Feature)

Workflow file path:

.github/workflows/docker-publish.yml

The workflow:

    Runs on pushes to the main branch

    Builds Docker image using the project Dockerfile

    Pushes the image to Docker Hub using stored GitHub Secrets

Produces the image:

DOCKER_USERNAME/golf-club-api-app:latest

Required GitHub Secrets:

    DOCKERHUB_USERNAME

    DOCKERHUB_TOKEN

Troubleshooting
400 Bad Request

    The POST request body is missing required fields or has invalid JSON.

    Ensure the request uses:

Content-Type: application/json

404 Not Found

    Check that the URL:

        Does not contain trailing whitespace or newline characters

        Exactly matches the controller mapping

Docker API Cannot Reach Database

    When running in Docker, use the hostname db, not localhost.

Example JDBC URL:

jdbc:postgresql://db:5432/golfclubdb

GitHub Actions Authentication Failure

    Verify that the Docker Hub username and access token are correctly stored as GitHub Secrets.

License

This project was created for academic purposes as part of a Software Development program.
