
# Hello-Shoe-BackEnd

## Overview

Hello-Shoe-Front-End is a robust and scalable web application built using the Spring Boot framework. It is designed for managing shoe-related data and operations with a focus on maintainability and extensibility. The application leverages a layered architecture, promoting separation of concerns and modularity.

### Key Features:

- **Data Persistence:** Utilizes Spring Data JPA for seamless integration with relational databases, ensuring efficient data management and retrieval.
- **Security:** Implements Spring Security to provide comprehensive security measures, including authentication and authorization.
- **Validation:** Incorporates robust validation mechanisms using Spring Boot's validation framework to ensure data integrity and consistency.
- **Email Support:** Integrates email functionalities to enable communication features such as user notifications and password resets.
- **JWT Authentication:** Employs JSON Web Tokens (JWT) for secure and stateless authentication.
- **Object Mapping:** Uses ModelMapper for efficient and easy object mapping between different layers.
- **Utility Functions:** Leverages Apache Commons for various utility operations, enhancing the overall functionality.

### Layered Architecture:

The application is structured into distinct layers, each responsible for a specific aspect of the system:

1. **Presentation Layer:** Handles the user interface and user experience, managing incoming requests and responses.
2. **Service Layer:** Contains the business logic and service implementations, processing user requests and coordinating between the presentation and data layers.
3. **Data Access Layer:** Manages database interactions, including CRUD operations and queries, ensuring secure and efficient data handling.
4. **Domain Layer:** Consists of the core business entities and models, defining the essential data structures used across the application.

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 8 or higher
- Maven
- MySQL database

### Installation

1. **Clone the repository:**

    ```sh
    git clone https://github.com/yourusername/hello-shoe-front-end.git
    cd hello-shoe-front-end
    ```

2. **Configure the database:**

    Update the `application.properties` file with your MySQL database details:

    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/yourdbname
    spring.datasource.username=yourusername
    spring.datasource.password=yourpassword
    ```

3. **Build the project:**

    ```sh
    mvn clean install
    ```

4. **Run the application:**

    ```sh
    mvn spring-boot:run
    ```

## Usage

Once the application is running, you can access it at `http://localhost:8080`. The application provides various endpoints for managing shoe data.

## Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

## Contact

For any inquiries or issues, please contact `ujithamigara@gmail.com`
