# Rate CS Teaching Staff

A Spring Boot CRUD web application that allows users to rate, review, and edit profiles for Computer Science teaching staff. Built with Java, Spring Boot, Thymeleaf, and PostgreSQL.

## Live Deployment
The application is deployed and live on Render: 
https://rate-cs-teaching-staff-rn58.onrender.com

## Features (CRUD Capabilities)
* **Create:** Add new staff members with their role, email, and specific rating scores (Clarity, Niceness, Knowledgeable).
* **Read:** View a complete list of all staff ratings, and click into individual profiles for detailed views and overall average calculations.
* **Update:** Edit existing staff information and ratings. Form validation ensures data integrity, and values are preserved if validation fails.
* **Delete:** Permanently remove a staff member's rating from the database with a built-in confirmation safeguard.

## How to Run Locally

1. **Clone the repository:**
   `git clone <https://github.com/nguyeenxquoocsanh/rate-cs-teaching-staff>`
2. **Navigate to the project directory:**
   `cd rate-cs-teaching-staff`
3. **Run the application using Maven:**
   * On Mac/Linux: `./mvnw spring-boot:run`
   * On Windows: `mvnw.cmd spring-boot:run`
4. **Access the application:**
   Open your web browser and navigate to `http://localhost:8080/users/view`

## Known Issues / Future Work (Iteration 1)
* **Aggregated Averages:** Currently, each entry acts as an isolated rating. In a future iteration, the database schema should be normalized to allow multiple users to submit individual ratings for a single instructor, with the application calculate the average score from all users. Something similar to ratemyprof
* **Authentication:** There is no user login authentication, anyone with the repository can perform all the actions