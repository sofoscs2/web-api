# Restful-api

>Back-end application is created by using:
- Maven framework (https://maven.apache.org/)
- Spring Boot (https://projects.spring.io/spring-boot/)
- IntelliJ IDEA (https://www.jetbrains.com/idea/)
- PostgreSQL (https://www.postgresql.org/download/)

## Build Setup

``` bash
# install dependencies
mvn clean install 

# run server will load at localhost:8080
mvn spring-boot:run

# working build
mvn package
```

In order to build and run project requires
- Install Java SE Development Kit 17 (https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- Install Maven (https://maven.apache.org/download.cgi)


## Curls for Match Requests: 
## Get all matches : 
``` bash 
curl --location 'http://localhost:8080/api/matches'
```     
## Get match by Id : 
``` bash 
curl --location 'http://localhost:8080/api/matches/{id}'
```     
## Create match : 
``` bash 
curl --location --request POST 'http://localhost:8080/api/matches' \
--header 'Content-Type: application/json' \
--data '{
    "description": "String",
    "matchDate": "Date yyyy-mm-dd",
    "matchTime": "Time hh:mm:ss",
    "teamA": "String",
    "teamB": "String",
    "sport": "String Football/Basketball/Cricket/Tennis/Other"
}'
```        
## Update match by Id : 
``` bash 
curl --location --request PUT 'http://localhost:8080/api/matches/{id}' \
--header 'Content-Type: application/json' \
--data '{
    "description": "String",
    "matchDate": "Date yyyy-mm-dd",
    "matchTime": "Time hh:mm:ss",
    "teamA": "String",
    "teamB": "String",
    "sport": "String Football/Basketball/Cricket/Tennis/Other"
}'
 ```                            
## Delete match by Id : 
``` bash 
curl --location --request DELETE 'http://localhost:8080/api/matches/{id}'
```     

## Curls for Match Odds Requests:
## Get all match odds:
``` bash 
curl --location 'http://localhost:8080/api/match-odds'
```     
## Get match odd by Id :
``` bash 
curl --location 'http://localhost:8080/api/match-odds/{id}'
```     
## Create match odd:
``` bash 
curl --location --request POST 'http://localhost:8080/api/match-odds' \
--header 'Content-Type: application/json' \
--data '{
    "matchId": Long,
    "specifier": "String",
    "odd": Double
}'
```        
## Update match odd by Id :
``` bash 
curl --location --request PUT 'http://localhost:8080/api/match-odds/{id}' \
--header 'Content-Type: application/json' \
--data '{
    "matchId": Long,
    "specifier": "String",
    "odd": Double
}'
 ```                            
## Delete match odd by Id :
``` bash 
curl --location --request DELETE 'http://localhost:8080/api/match-odds/{id}'
```   

## Docker Setup

``` bash
# Build the Docker Image
docker build -t webapi:latest .

# Run images
docker images

# Run Docker Image in a Container
docker run -d -p 8080:8080 webapi
```
## JMeter Setup

- File : WebApiServices.jmx
- Number of threads (Users) : 100
- Ramp up period (seconds) : 60
- Loop count : 100
- Repeat process : 700