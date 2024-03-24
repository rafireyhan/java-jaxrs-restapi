# Java Simulation Project
This repository is for Java Simulation Test Case purposes only.
Developed using:
- Java JDK 21.0.2 2024-01-16 LTS
- Apache Maven 3.9.6
- Eclipse IDE Version: 2024-03 (4.31.0)
- Python 3.12.1
- pip 23.2.1
> [!NOTE]
> For api documentation, click the following link [Api Documentation](https://documenter.getpostman.com/view/21590878/2sA2xpS9K7)

### Project Test Task list
1. [x] Develop Rest API using:
    - [X] Eclipse Jetty as REST-API Handler
    - [X] JAX-RS REST-API handler
    - [X] EclipseLink as Java Persistence Architecture (for H2 and MariaDB)
    - [X] MAVEN Build System
2. [X] Database Connection configuration (for H2 and MariaDB)
3. [X] Unit Testing using JUnit Jupiter
4. [X] Regression Integration Test using Python
5. [X] Docker Repository
6. [X] Github Repository

# How To Run
### How to Run Docker Image
> [!NOTE]
> For Docker Image Repository, click the following link [Docker Repository](https://hub.docker.com/r/redan215/java-simulation-project)
1. Pull Image
   ```bash
   docker pull redan215/java-simulation-project
2. Run Container
   ```bash
   docker run -d -p 8080:8080 redan215/java-simulation-project
4. URL for the api
   ```bash
   http://localhost:8080

### How to Run The Project via Eclipse Terminal
1. Clone Repository
   ```bash
   git clone https://github.com/rafireyhan/java-jaxrs-restapi.git
3. Go to project directory
   ```bash
   cd java-jaxrs-restapi
5. Build Project
   ```bash
   mvn clean package
7. Run the project
   ```bash
   mvn jetty:run
  
### How to Run The JUnit Jupiter Testing
1. After cloning, type these in terminal
   ```bash
   mvn test
2. Result Example

![image](https://github.com/rafireyhan/java-jaxrs-restapi/assets/67086197/93c38038-c202-46aa-ba39-fc423091cf6c)

### How to Run The Regression Integration Test
>[!NOTE]
>Make sure already installed `python` and `python pip` before!
1. Run the project first
   ```bash
   mvn jetty:run
2. Open any Text Editor (example: Visual Studio Code), and open the `python-regression-test` folder
3. Install dependencies
   ```bash
   pip install requests
4. Run the file test
   ```bash
   python TestSensorApi.py
5. Output Example

![image](https://github.com/rafireyhan/java-jaxrs-restapi/assets/67086197/376e9286-d3ad-4a9d-b209-f75beb100a2f)

   
