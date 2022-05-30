#  Bank Balance and Dispensing System - Backend Work

### Author: Arthur Gwatidzo
### Email:	arthur.gwatidzo@gmail.com

#### Mandatory Requirements
##### You will need java 8 installed on local machine
##### Setup and configure Lombok in your IDE - I used Eclipse IDE
##### Docker
##### Docker-compose
#### Docker and Docker-compose are not mandatory


### Getting Started

1. #Running DB service 

Ensure that you are on the root directory of the project, than execute the following command :
 
`docker-compose -f docker-compose.yml up -d`

The above command will startup the db service , which in this case is Postgresql. With the following set variables

Database Type: Postgres

username : sa
 
password: 
###Password is just blank



#Running the Application

These are the commands needed to run the project

mvn clean install

mvn spring-boot:run

### Upon succesful startup

navigate and open the application.properties file (using your ide or via command line)
`touch discovery_atm_app/src/main/resources/application.properties`
Within the properties file, look for a variable named `spring.datasource.initialization-mode` and change its value from ALWAYS to NEVER. This ensure that on your next startup, the db tables data is not reinitialized but maintains current state

### API listing
Application is configured to run on http://localhost:8989

### Swagger ui
Swagger provides documentation for all APIs in the application, as well as defines the request and response object for the API

We can also use it to interact with the application by simulating test cases against each listed API

To access swagger: http://localhost:8989/swagger-ui.html#





