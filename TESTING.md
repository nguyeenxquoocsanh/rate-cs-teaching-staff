# Testing: Rate CS Teaching Staff

## Why this testing strategy was chosen
I picked **Integration Testing** via the `@SpringBootTest` and `@AutoConfigureMockMvc` annotations to ensure the web layer, validation logic, and database persistence all work together correctly.
Rather than writing separate unit tests for the web layer (`@WebMvcTest`) and the database layer (`@DataJpaTest`), I chose a full Spring Boot application test. 

## What is tested
The test suite (`UsersControllerIntegrationTest.java`) covers all rubric requirements:
1. **Web/Controller Routing:** Ensures GET requests successfully return a `200 OK` status and load the correct Thymeleaf templates and models.
2. **Validation:** Ensures POST requests with invalid data (e.g., malformed emails, out-of-range scores) are intercepted, return a `400 Bad Request`, and reload the form with the appropriate error messages.
3. **Persistence:** Ensures the application can successfully save a new `Staff` entity to the database, retrieve it by its generated ID, and successfully delete it.

## How to run the tests
Ensure you are in the root directory of the project, then run the following Maven command:

* On Mac/Linux: `./mvnw test`
* On Windows: `mvnw.cmd test`