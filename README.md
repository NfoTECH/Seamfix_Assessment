# Seamfix Assessment
This is a service to validate BVNs, preconfigured in memory.
Algorithm to get notification time.

## PREREQUISITES
1. MongoDb must be running locally
2. Request and response objects are stored in a MongoDb NoSql store,
   based on the configured properties.

### Technologies
- Java
- Maven
- Springboot
- JUnit
- MongoDB


### Requirements

You need the following to build and run the application:

- [JDK 17](https://www.oracle.com/java/technologies/javase-jdk17-downloads.html)
- [Maven 3.8.1](https://maven.apache.org) (Optional as code already contains maven wrapper)


## How to run
### step 1 - clone project with from [here](https://github.com/NfoTECH/Seamfix_Assessment.git)

```
git clone https://github.com/NfoTECH/Seamfix_Assessment.git
```


### step 2 - move into the project directory
```
cd seamfix-assessment/
```

### step 3 - Generate the .jar file
```
mvn clean install 
OR
./mvnw clean install
```

### step 4 - run the project
```
java -jar SeamFixAssessment-0.0.1-SNAPSHOT.jar
```

### NOTE :
* The application can also be run as a standalone application, by running as a springboot project with the "play" but on IntelliJ

### Testing the API endpoint with various bvn inputs
#### Endpoint
```
`http://localhost:9090/bv-service/svalidate/wrapper`
```

#### 1. Valid BVN
```
curl --location --request POST 'http://localhost:9090/bv-service/svalidate/wrapper' \
--header 'Content-Type: application/json' \
--data-raw '{
    "bvn" : "22284857889"
}'
```

#### Response

```
{
    "message": "SUCCESS",
    "code": "00",
    "bvn": "22284857889",
    "imageDetail": "\/9j\/4AAQSkZJRgABAQAAAQABAAD\/2wBDAAEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEB\\n==",
    "basicDetail": "\/9j\/4AAQSkZJRgABAQAAAQABAAD\/2wBDAAEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEB\\n=="
}
```
![This is an image](src/main/resources/static/validBVN.png)
---
#### 2. Invalid BVN (Contains non digits)
```
curl --location --request POST 'http://localhost:9090/bv-service/svalidate/wrapper' \
--header 'Content-Type: application/json' \
--data-raw '{
    "bvn" : "A2284847598"
}'
```
#### Response
```
{
    "message": "The searched BVN is invalid",
    "code": "400",
    "bvn": "A2284847598"
}
```

![This is an image](src/main/resources/static/containsAlpha.png)
---
#### 3. Invalid BVN (Contains less than 11 digits)
```
curl --location --request POST 'http://localhost:9090/bv-service/svalidate/wrapper' \
--header 'Content-Type: application/json' \
--data-raw '{
    "bvn" : "84847598"
}'
```
#### Response
```
{
    "message": "The searched BVN is invalid",
    "code": "02",
    "bvn": "84847598"
}
```
![This is an image](src/main/resources/static/lessThanNo.png)
---
#### 4. Invalid BVN
```
curl --location --request POST 'http://localhost:9090/bv-service/svalidate/wrapper' \
--header 'Content-Type: application/json' \
--data-raw '{
    "bvn" : "12284847598"
}'
```
#### Response
```
{
    "message": "The searched BVN does not exist",
    "code": "01",
    "bvn": "12284847598"
}
```
![This is an image](src/main/resources/static/invalidBVN.png)
---
#### 5. MongoDB Storage
![This is an image](src/main/resources/static/MongoDBSummary.png)
```


