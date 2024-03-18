# Java Simulation Project
This repository is for Java Simulation Test Case purposes only.
Developed using:
- Java JDK 21.0.2 2024-01-16 LTS
- Apache Maven 3.9.6
- Eclipse IDE Version: 2024-03 (4.31.0)
> [!NOTE]
> For api documentation, click the following link [Api Documentation](https://documenter.getpostman.com/view/21590878/2sA2xpS9K7)

### Project Test Task list
- [x] Eclipse Jetty as REST-API Server
- [x] JAX-RS REST-API handler
- [x] MAVEN build System
- [x] MariaDB
- [ ] GET API Request (success returns data but returns the `node` on every object in JSON)
- [x] POST API Request
- [x] DELETE API Request
- [x] Github
- [ ] ~~H2 Embedded Database~~
- [ ] ~~Database specific configuration file~~
- [ ] ~~Unit Testing JUnit Jupiter~~
- [ ] ~~Regression Integration Python~~
- [ ] ~~Docker~~

### How to Run
> [!WARNING]
> This is the way i run the programs on EclipseIDE
> 
To run the project, follow the steps below:
- After pulling the repository, type `mvn clean install` in the terminal to clean and install needed dependencies.
- Then `Right Click on the project` from Project explorer on Eclipse -> `Run As` -> `Run with Jetty`.

### Database
Import the database from `javaDB.sql`, and it should look like image below: 
![image](https://github.com/rafireyhan/java-jaxrs-restapi/assets/67086197/64a75626-4b77-4fbf-b51b-4637a7336a86)
