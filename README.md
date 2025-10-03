# CRUD API with Spring Boot

This is a simple CRUD API for managing services, built with Spring Boot.

## Features

*   Create, Read, Update, and Delete services.
*   RESTful API with endpoints for each operation.
*   Uses Spring Data JPA for database interaction.
*   PostgreSQL as the database.

## Technologies Used

*   Java 25
*   Spring Boot 3.5.6
*   Spring Data JPA
*   PostgreSQL
*   Docker Compose

## API Endpoints

The following endpoints are available under the `/services` path:

| Method | Path      | Description                  |
| :----- | :-------- | :--------------------------- |
| `GET`    | `/`       | Get all services             |
| `GET`    | `/{id}`   | Get a service by ID          |
| `POST`   | `/`       | Create a new service         |
| `PUT`    | `/{id}`   | Update a service by ID       |
| `PATCH`  | `/{id}`   | Partially update a service   |
| `DELETE` | `/{id}`   | Delete a service by ID       |

## Getting Started

### Prerequisites

*   Java 25 or later
*   Docker and Docker Compose

### Running the Application

1.  **Clone the repository:**

    ```bash
    git clone https://github.com/andrelzinnn/services-crud.git
    cd services-crud
    ```

2.  **Start the database:**

    ```bash
    docker-compose up -d
    ```

3.  **Run the application:**

    ```bash
    ./gradlew bootRun
    ```

The application will be running on `http://localhost:8080`.