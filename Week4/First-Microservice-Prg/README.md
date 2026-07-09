# Microservices Learning Notes --- Student Service + Department Service

> A focused fresher-level guide based on my Microservices learning for
> Java Backend Development and Cognizant Deep Skilling preparation.

------------------------------------------------------------------------

## 1. Purpose of This Repository

This repository documents the Microservices concepts I learned step by
step using one consistent example:

-   **Student Service**
-   **Department Service**
-   **Eureka Server**

The goal is not to build a production-level distributed system. The goal
is to understand the foundation of Microservices within a short 3--4 day
learning scope.

### Topics intentionally skipped for now

The following are useful in advanced systems, but they are outside the
current fresher-level scope:

-   Kafka
-   Kubernetes
-   Saga Pattern
-   CQRS
-   Distributed Tracing
-   Advanced Security
-   Event-Driven Architecture
-   Advanced Load Balancing
-   Eureka Clusters
-   Production Deployment

------------------------------------------------------------------------

# 2. Short Learning Roadmap

  Topic                                         Status
  --------------------------------------------- -----------
  What Microservices Are                        Completed
  Monolith vs Microservices                     Completed
  Create Two Independent Spring Boot Services   Completed
  Service-to-Service Communication using REST   Completed
  Eureka Server and Service Discovery           Completed
  API Gateway Basics                            Completed
  Basic Fault Tolerance                         Learning
  Final Small Project Integration               Pending

------------------------------------------------------------------------

# 3. What Are Microservices?

## Simple Definition

A **microservice is a small, independently runnable application
responsible for a specific business area**.

A microservices-based system divides one large application into multiple
smaller applications that communicate with each other.

Example:

``` text
College Application

Student Service
    → manages students

Department Service
    → manages departments

Course Service
    → manages courses

Fee Service
    → manages payments
```

For this learning project, only two business services are used:

``` text
Student Service
Department Service
```

## The Most Important Word: Independent

A microservice is not just another Java class, package, controller, or
`@Service` class.

These are two separate applications:

``` text
student-service/
├── pom.xml
├── src/
└── application.properties
```

``` text
department-service/
├── pom.xml
├── src/
└── application.properties
```

Each application has:

-   Its own `pom.xml`
-   Its own Spring Boot main class
-   Its own configuration
-   Its own port
-   Its own REST APIs
-   Its own running process

Example:

``` text
Student Service     → localhost:8081
Department Service  → localhost:8082
```

If Department Service stops, Student Service can still remain running.
However, Student Service operations that depend on Department Service
may fail.

## Important: `@Service` Class Is Not a Microservice

This is only a Java class managed by Spring:

``` java
@Service
public class StudentService {
}
```

It is **not** a microservice.

A Student Microservice is the complete Spring Boot application:

``` text
Student Microservice
│
├── StudentController
├── StudentService
├── StudentRepository
├── Student model/entity
├── application.properties
├── pom.xml
└── StudentServiceApplication
```

### Key Difference

``` text
StudentService.java
        ↓
One Java class
        ↓
Usually contains business logic
```

``` text
student-service/
        ↓
Complete independent Spring Boot application
        ↓
Can contain controllers, services, repositories and configuration
```

------------------------------------------------------------------------

# 4. Monolith vs Microservices

## What Is a Monolith?

A monolithic application contains multiple business features inside one
application.

Example:

``` text
college-application/
│
├── StudentController
├── StudentService
├── StudentRepository
│
├── DepartmentController
├── DepartmentService
├── DepartmentRepository
│
├── CourseController
├── CourseService
├── CourseRepository
│
├── pom.xml
└── CollegeApplication.java
```

Everything runs together:

``` text
ONE Spring Boot project
ONE running application
ONE deployment unit
ONE port
```

Example:

``` text
localhost:8080/students
localhost:8080/departments
localhost:8080/courses
```

A monolith is not automatically bad. For small CRUD applications, it is
often simpler and more practical.

## The Same System as Microservices

``` text
Student Service       → localhost:8081
Department Service    → localhost:8082
Course Service        → localhost:8083
```

Each is an independent application.

## Main Differences

  Monolith                            Microservices
  ----------------------------------- -------------------------------------
  One application                     Multiple independent applications
  Features run together               Services run independently
  Usually one deployment unit         Services can be deployed separately
  Internal Java method calls          Network calls between services
  Commonly one application database   Each service should own its data
  Simpler initially                   More complex to manage

## Communication Difference

### In a Monolith

Classes are in the same application, so normal Java method calls can be
used:

``` java
departmentService.getDepartmentById(1);
```

### In Microservices

Student Service and Department Service are different applications.

Student Service cannot directly use the `DepartmentService` Java object.

Instead:

``` text
Student Service
        |
        | HTTP REST request
        v
Department Service
```

Example:

``` text
GET http://localhost:8082/departments/1
```

## Failure Difference

### Monolith

``` text
Whole application crashes
        ↓
Student feature unavailable
Department feature unavailable
Course feature unavailable
```

### Microservices

``` text
Student Service      → RUNNING
Department Service   → DOWN
```

Student Service may remain running, but any Student Service operation
that needs Department Service can still fail.

This is why fault tolerance is needed later.

## Database Ownership

A useful microservices principle is:

> A service should own its data.

Better design:

``` text
Student Service
        |
        | REST request
        v
Department Service
        |
        v
Department Database
```

Avoid this:

``` text
Student Service
        |
        | directly reads
        v
Department Database
```

For this learning project, database complexity is intentionally skipped
until the core communication concepts are clear.

------------------------------------------------------------------------

# 5. Creating Two Independent Spring Boot Services

Two separate Spring Boot projects were created.

## Student Service

``` text
Application name: student-service
Port: 8081
```

`application.properties`:

``` properties
spring.application.name=student-service
server.port=8081
```

Simple API:

``` java
@RestController
public class StudentController {

    @GetMapping("/students")
    public String getStudents() {
        return "Students from Student Service";
    }
}
```

Test:

``` text
GET http://localhost:8081/students
```

Expected response:

``` text
Students from Student Service
```

## Department Service

``` text
Application name: department-service
Port: 8082
```

`application.properties`:

``` properties
spring.application.name=department-service
server.port=8082
```

Simple API:

``` java
@RestController
public class DepartmentController {

    @GetMapping("/departments")
    public String getDepartments() {
        return "Departments from Department Service";
    }
}
```

Test:

``` text
GET http://localhost:8082/departments
```

Expected response:

``` text
Departments from Department Service
```

## What This Proved

``` text
Two separate Spring Boot applications
        +
Two separate ports
        +
Two separate REST APIs
        =
Foundation of independent services
```

At this stage, the services were independent but isolated.

``` text
Student Service        Department Service
     :8081                   :8082

             No communication
```

------------------------------------------------------------------------

# 6. Service-to-Service Communication Using REST

## The Requirement

Suppose Student Service knows:

``` text
Student
-------
id = 101
name = Karthik
departmentId = 1
```

Department Service knows:

``` text
Department
----------
id = 1
name = Information Technology
```

The required final response is:

``` json
{
  "id": 101,
  "name": "Karthik",
  "department": {
    "id": 1,
    "name": "Information Technology"
  }
}
```

Student Service knows the `departmentId`, but it does not own the
department name.

Therefore:

``` text
Client
   |
   | GET /students/101/details
   v
Student Service
   |
   | GET /departments/1
   v
Department Service
   |
   | Department data
   v
Student Service
   |
   | Combined response
   v
Client
```

This is **service-to-service communication**.

------------------------------------------------------------------------

# 7. Why Return Objects Instead of Plain Strings?

Returning this:

``` text
"Information Technology"
```

provides only one plain value.

Returning an object provides structured data:

``` json
{
  "id": 1,
  "name": "Information Technology"
}
```

Later, more fields can be added:

``` json
{
  "id": 1,
  "name": "Information Technology",
  "location": "Block A"
}
```

The main reason for returning an object is **structured API data**, not
merely future database integration.

------------------------------------------------------------------------

# 8. Serialization and Deserialization

## Serialization

When Department Service returns a Java object:

``` java
return new Department(1, "Information Technology");
```

Spring converts it:

``` text
Java Object
    ↓
JSON
    ↓
HTTP Response
```

This is called **serialization**.

Example:

``` java
new Department(1, "IT")
```

becomes:

``` json
{
  "id": 1,
  "name": "IT"
}
```

## Deserialization

Student Service receives JSON:

``` json
{
  "id": 1,
  "name": "Information Technology"
}
```

Spring converts it into a Java object:

``` text
JSON
    ↓
Department object
```

This is called **deserialization**.

Summary:

``` text
Serialization:
Java Object → JSON

Deserialization:
JSON → Java Object
```

------------------------------------------------------------------------

# 9. Department Service API

A simple Department class:

``` java
public class Department {

    private int id;
    private String name;

    public Department(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
```

Controller:

``` java
@RestController
public class DepartmentController {

    @GetMapping("/departments/{id}")
    public Department getDepartmentById(@PathVariable int id) {

        return new Department(
                id,
                "Information Technology"
        );
    }
}
```

Test:

``` text
GET http://localhost:8082/departments/1
```

Response:

``` json
{
  "id": 1,
  "name": "Information Technology"
}
```

------------------------------------------------------------------------

# 10. What Is `RestTemplate`?

`RestTemplate` is a Spring class used to send HTTP requests from one
application to another.

Example 1:

``` text
Student Service
    |
    | RestTemplate
    | GET /departments/1
    v
Department Service
```

Example 2:

``` text
Order Service
    |
    | RestTemplate
    | GET /products/50
    v
Product Service
```

For this focused learning path, `RestTemplate` is used to understand the
basic communication flow.

Other clients such as WebClient or OpenFeign are intentionally skipped
for now.

------------------------------------------------------------------------

# 11. Creating a `RestTemplate` Bean

Inside Student Service:

``` java
@SpringBootApplication
public class StudentServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudentServiceApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
```

## Important Java Structure

A method cannot be declared inside another method.

Wrong:

``` java
public static void main(String[] args) {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
```

Correct:

``` java
public class Example {

    public static void main(String[] args) {
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
```

## Why `@Bean`?

``` text
@Bean method
      ↓
Spring creates the RestTemplate object
      ↓
Stores it in the IoC container
      ↓
Injects it where required
```

Constructor injection:

``` java
private final RestTemplate restTemplate;

public StudentController(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
}
```

This uses the same Spring Dependency Injection concept learned earlier.

------------------------------------------------------------------------

# 12. Why Does Student Service Also Need a Department Class?

Department Service has its own `Department` class.

Student Service is a separate application and cannot directly use a Java
object from another running application.

Department Service sends JSON:

``` json
{
  "id": 1,
  "name": "Information Technology"
}
```

Student Service needs a local class to hold that response:

``` java
public class Department {

    private int id;
    private String name;

    public Department() {
    }

    public Department(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
```

Conceptually:

``` text
Department Service:
Department object → JSON

Student Service:
JSON → local Department object
```

The two applications remain independent.

------------------------------------------------------------------------

# 13. First REST Call Between Services

Inside Student Service:

``` java
Department department =
        restTemplate.getForObject(
                "http://localhost:8082/departments/"
                        + student.getDepartmentId(),
                Department.class
        );
```

## Understanding `getForObject()`

``` java
restTemplate.getForObject(
    "http://localhost:8082/departments/1",
    Department.class
);
```

First argument:

``` text
http://localhost:8082/departments/1
```

means:

> Where should the GET request be sent?

Second argument:

``` java
Department.class
```

means:

> Into which Java type should the response JSON be converted?

Flow:

``` text
Send GET request
        ↓
Receive response JSON
        ↓
Convert JSON
        ↓
Store as Department object
```

Two examples:

``` java
restTemplate.getForObject(url, Department.class);
```

Response becomes a `Department`.

``` java
restTemplate.getForObject(url, Student.class);
```

Response becomes a `Student`.

------------------------------------------------------------------------

# 14. Combining Student and Department Data

A response DTO was created:

``` java
public class StudentDetailsResponse {

    private int id;
    private String name;
    private Department department;

    public StudentDetailsResponse(
            int id,
            String name,
            Department department
    ) {
        this.id = id;
        this.name = name;
        this.department = department;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Department getDepartment() {
        return department;
    }
}
```

This class is **not an entity**.

Do not add:

``` java
@Entity
```

Its job is only to shape the API response.

Example 1:

``` text
Student + Department
        ↓
StudentDetailsResponse
```

Example 2:

``` text
Order + Product
        ↓
OrderDetailsResponse
```

This type of class is commonly called a **DTO** or **response DTO**.

## Final Endpoint Before Eureka

``` java
@GetMapping("/students/{id}/details")
public StudentDetailsResponse getStudentDetails(
        @PathVariable int id
) {

    Student student = new Student(
            id,
            "Karthik",
            1
    );

    Department department =
            restTemplate.getForObject(
                    "http://localhost:8082/departments/"
                            + student.getDepartmentId(),
                    Department.class
            );

    return new StudentDetailsResponse(
            student.getId(),
            student.getName(),
            department
    );
}
```

Final response:

``` json
{
  "id": 101,
  "name": "Karthik",
  "department": {
    "id": 1,
    "name": "Information Technology"
  }
}
```

------------------------------------------------------------------------

# 15. Problem With Hardcoded Service Locations

The previous code contains:

``` text
http://localhost:8082
```

This means Student Service must know:

-   The exact host
-   The exact port

Problems appear if the location changes.

Example 1:

``` text
Yesterday:
Department Service → 8082

Today:
Department Service → 9090
```

Student Service still calls `8082`, so the request fails.

Example 2:

``` text
Department Service Instance 1 → 8082
Department Service Instance 2 → 8085
```

A single hardcoded URL does not represent service discovery or multiple
instances.

This leads to:

``` text
How can Student Service find Department Service
without hardcoding its exact location?
```

Answer:

``` text
Service Discovery
        ↓
Eureka Server
```

------------------------------------------------------------------------

# 16. What Is Service Discovery?

Service discovery allows one microservice to find another microservice
using a **service name** instead of a hardcoded host and port.

Without service discovery:

``` text
http://localhost:8082/departments/1
```

With service discovery:

``` text
http://DEPARTMENT-SERVICE/departments/1
```

Example 1:

``` text
Student Service
    ↓
DEPARTMENT-SERVICE
```

Example 2:

``` text
Order Service
    ↓
PRODUCT-SERVICE
```

The caller knows the logical service name rather than the exact physical
location.

------------------------------------------------------------------------

# 17. What Is Eureka Server?

Eureka Server is a **service registry**.

Think of it as a directory:

``` text
EUREKA SERVER

Service Name          Location
----------------------------------------
student-service       localhost:8081
department-service    localhost:8082
```

Eureka helps services find each other.

Important distinction:

``` text
Eureka
    → helps FIND the service

RestTemplate
    → CALLS the service
```

Eureka does not replace REST communication.

------------------------------------------------------------------------

# 18. Registration vs Discovery

## Registration

A service tells Eureka:

> I am available.

Example:

``` text
Department Service
        |
        | registers
        v
Eureka Server
```

## Discovery

A service asks:

> Where is another service?

Example:

``` text
Student Service
        |
        | find DEPARTMENT-SERVICE
        v
Eureka Server
```

Summary:

``` text
Registration:
Service → Eureka

Discovery:
Service asks for another service's available instance
```

------------------------------------------------------------------------

# 19. Eureka Architecture Used in This Project

``` text
                  Eureka Server
                      :8761
                    /       \
                   /         \
          registers           registers
             /                   \
            v                     v

 Student Service          Department Service
      :8081                    :8082
         |                        ^
         |                        |
         |   REST using name      |
         +------------------------+
              DEPARTMENT-SERVICE
```

Three applications are involved:

``` text
1. eureka-server
2. student-service
3. department-service
```

Student Service and Department Service are business applications.

Eureka Server is infrastructure:

``` text
Student Service
    → manages student-related work

Department Service
    → manages department-related work

Eureka Server
    → maintains service registration information
```

------------------------------------------------------------------------

# 20. Does Eureka Have to Run on Port 8761?

No.

Eureka can run on any free port.

Example 1:

``` properties
server.port=9999
```

Clients must then use:

``` properties
eureka.client.service-url.defaultZone=http://localhost:9999/eureka/
```

Example 2:

``` properties
server.port=8085
```

Clients must then use:

``` properties
eureka.client.service-url.defaultZone=http://localhost:8085/eureka/
```

Port `8761` is commonly used by convention.

For this project:

``` properties
server.port=8761
```

Changing the port provides no extra learning benefit, so the
conventional port is kept.

------------------------------------------------------------------------

# 21. Eureka Server Configuration

Main class:

``` java
@SpringBootApplication
@EnableEurekaServer
public class EurekaServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(
                EurekaServerApplication.class,
                args
        );
    }
}
```

## `@EnableEurekaServer`

``` java
@EnableEurekaServer
```

means:

> Start this application as a Eureka service registry.

Without it:

``` text
Normal Spring Boot application
```

With it:

``` text
Eureka Service Registry
```

## `application.properties`

``` properties
spring.application.name=eureka-server

server.port=8761

eureka.client.register-with-eureka=false

eureka.client.fetch-registry=false
```

### `register-with-eureka=false`

Normal services register with Eureka.

For this simple setup, Eureka Server does not register with itself.

``` text
Student Service     → registers ✓
Department Service  → registers ✓
Eureka Server       → registers with itself ✗
```

### `fetch-registry=false`

Normal clients fetch registry information.

This single Eureka Server does not need to fetch another Eureka
registry.

Advanced Eureka clusters are intentionally skipped.

------------------------------------------------------------------------

# 22. Adding Eureka Client to Existing Services

There is no need to recreate an existing Spring Boot project.

Add the dependency directly to `pom.xml`:

``` xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
</dependency>
```

Two valid approaches:

``` text
Method 1:
Spring Initializr
→ Best while creating a new project
```

``` text
Method 2:
Edit pom.xml
→ Best for an existing project
```

After editing the POM:

-   Save the file
-   Reload Maven
-   Wait for dependencies to download

------------------------------------------------------------------------

# 23. Spring Cloud BOM

The project uses Spring Boot `4.1.0`.

Spring Cloud dependencies are managed using a BOM.

BOM means:

> Bill of Materials

The practical idea:

``` text
Spring Boot version
       ↓
Spring Cloud BOM
       ↓
Compatible Spring Cloud dependency versions
```

Properties:

``` xml
<properties>
    <java.version>17</java.version>
    <spring-cloud.version>2025.1.2</spring-cloud.version>
</properties>
```

Dependency management:

``` xml
<dependencyManagement>
    <dependencies>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-dependencies</artifactId>
            <version>${spring-cloud.version}</version>
            <type>pom</type>
            <scope>import</scope>
        </dependency>
    </dependencies>
</dependencyManagement>
```

Then Spring Cloud dependencies can be added without manually specifying
a version:

``` xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
</dependency>
```

The BOM manages the compatible version.

## Important POM Structure

``` text
<project>
│
├── <parent>
│
├── <properties>
│
├── <dependencyManagement>
│
├── <dependencies>
│
└── <build>
```

Do not accidentally place `<dependencyManagement>` inside
`<dependencies>`.

------------------------------------------------------------------------

# 24. Registering Department Service

Department Service configuration:

``` properties
spring.application.name=department-service

server.port=8082

eureka.client.service-url.defaultZone=http://localhost:8761/eureka/
```

The new property tells Department Service where Eureka Server is
running.

Flow:

``` text
Department Service
        |
        | registers
        v
Eureka Server :8761
```

------------------------------------------------------------------------

# 25. Registering Student Service

Student Service configuration:

``` properties
spring.application.name=student-service

server.port=8081

eureka.client.service-url.defaultZone=http://localhost:8761/eureka/
```

Flow:

``` text
Student Service
        |
        | registers
        v
Eureka Server :8761
```

Recommended startup order for easier debugging:

``` text
1. Start Eureka Server
2. Start Department Service
3. Start Student Service
```

Eureka dashboard:

``` text
http://localhost:8761
```

Expected registered applications:

``` text
DEPARTMENT-SERVICE → UP
STUDENT-SERVICE    → UP
```

Service names may appear in uppercase. That is normal.

------------------------------------------------------------------------

# 26. Eureka Self-Preservation Warning

In a small local setup, the Eureka dashboard may show a large red
warning similar to:

``` text
EMERGENCY! EUREKA MAY BE INCORRECTLY CLAIMING INSTANCES ARE UP...
```

For this learning project, this is not a blocker when both services
appear as `UP`.

Eureka uses renewal/heartbeat information and a self-preservation
mechanism. Small local setups can trigger the warning because the number
of instances and renewals is low.

Current learning decision:

``` text
Services show UP
        ↓
Continue learning
        ↓
Do not waste time tuning production Eureka behavior
```

------------------------------------------------------------------------

# 27. Problem: Registration Alone Is Not Enough

Even after registering both services, this code still bypasses Eureka:

``` java
"http://localhost:8082/departments/1"
```

So:

``` text
Eureka knows Department Service ✓

Student Service still uses hardcoded location ✗
```

The goal is:

``` java
"http://DEPARTMENT-SERVICE/departments/1"
```

But a normal `RestTemplate` does not automatically understand Eureka
service names.

This leads to `@LoadBalanced`.

------------------------------------------------------------------------

# 28. What Does `@LoadBalanced` Do?

Change the bean from:

``` java
@Bean
public RestTemplate restTemplate() {
    return new RestTemplate();
}
```

to:

``` java
@Bean
@LoadBalanced
public RestTemplate restTemplate() {
    return new RestTemplate();
}
```

Import:

``` java
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
```

Simple meaning:

> `@LoadBalanced` makes this `RestTemplate` capable of calling services
> using registered service names.

Without it:

``` text
http://DEPARTMENT-SERVICE/...
        ↓
Normal RestTemplate does not know this logical name
```

With it:

``` text
http://DEPARTMENT-SERVICE/...
        ↓
Resolve service name
        ↓
Find available instance
        ↓
Send request
```

------------------------------------------------------------------------

# 29. Why Is It Called `@LoadBalanced`?

1.  How @LoadBalanced relates to Horizontal Scaling Yes, @LoadBalanced
    is the tool that makes horizontal scaling actually work in your
    code. When you add more instances of a service to handle more
    traffic, you are horizontally scaling that service. However, your
    other microservices need a way to find and talk to those new
    instances. @LoadBalanced (which uses a library called Spring Cloud
    LoadBalancer) talks to the Eureka Server to get a list of all
    available instances. It then uses a strategy (like Round Robin) to
    distribute incoming traffic evenly among them so no single instance
    gets overwhelmed.

2.  Running on Ports 8081 and 8082 = 2 Instances? Yes, your
    understanding is 100% correct. If you run the exact same Java/Spring
    Boot code twice on your machine---one configured to run on port 8081
    and the other on 8082---you have successfully scaled horizontally to
    2 instances. When both of these applications start up, they will
    both register themselves with the Eureka Server using the exact same
    application name (for example, PRODUCT-SERVICE).

More about this:

Currently there is one Department Service instance:

``` text
DEPARTMENT-SERVICE
        ↓
localhost:8082
```

Imagine two instances:

``` text
DEPARTMENT-SERVICE

Instance 1 → localhost:8082
Instance 2 → localhost:8085
```

A load balancer can choose an available instance.

Example 1:

``` text
Request 1 → Instance on 8082
```

Example 2:

``` text
Request 2 → Instance on 8085
```

This is the basic idea of client-side load balancing.

Advanced load-balancing algorithms are outside the current scope.

------------------------------------------------------------------------

# 30. LoadBalancer Dependency

Student Service needs:

``` xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-loadbalancer</artifactId>
</dependency>
```

Relevant Spring Cloud dependencies in Student Service:

``` xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
</dependency>

<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-loadbalancer</artifactId>
</dependency>
```

Responsibilities:

``` text
Eureka:
Which instances of DEPARTMENT-SERVICE exist?
```

``` text
LoadBalancer:
Which available instance should be used for this request?
```

With one instance, the selection is simple, but the mechanism is the
same.

------------------------------------------------------------------------

# 31. Replacing the Hardcoded URL

Before Eureka-based discovery:

``` java
Department department =
        restTemplate.getForObject(
                "http://localhost:8082/departments/"
                        + student.getDepartmentId(),
                Department.class
        );
```

After Eureka + LoadBalancer:

``` java
Department department =
        restTemplate.getForObject(
                "http://DEPARTMENT-SERVICE/departments/"
                        + student.getDepartmentId(),
                Department.class
        );
```

The important change:

``` text
Before:
localhost:8082

After:
DEPARTMENT-SERVICE
```

------------------------------------------------------------------------

# 32. Complete Request Flow After Eureka

Client request:

``` text
GET http://localhost:8081/students/101/details
```

Flow:

``` text
1. Postman
      |
      | GET /students/101/details
      v

2. Student Service :8081
      |
      | Uses:
      | http://DEPARTMENT-SERVICE/departments/1
      v

3. Load-balanced RestTemplate
      |
      | Resolves DEPARTMENT-SERVICE
      v

4. Available Department Service instance
      |
      | localhost:8082
      v

5. Department Service
      |
      | Returns department JSON
      v

6. Student Service
      |
      | Combines Student + Department
      v

7. Client receives final response
```

Final JSON:

``` json
{
  "id": 101,
  "name": "Karthik",
  "department": {
    "id": 1,
    "name": "Information Technology"
  }
}
```

------------------------------------------------------------------------

# 33. Four Things That Must Not Be Mixed Up

## 1. Application Name

``` properties
spring.application.name=department-service
```

Purpose:

``` text
Gives the service its logical identity
```

## 2. Eureka Server Location

``` properties
eureka.client.service-url.defaultZone=http://localhost:8761/eureka/
```

Purpose:

``` text
Tells the client where Eureka Server is
```

## 3. `@LoadBalanced`

``` java
@LoadBalanced
```

Purpose:

``` text
Allows RestTemplate to resolve logical service names
and use available service instances
```

## 4. Service-Name URL

``` java
"http://DEPARTMENT-SERVICE/departments/1"
```

Purpose:

``` text
Calls the registered service by logical name
instead of hardcoded host and port
```

------------------------------------------------------------------------

# 34. Current Project Architecture

``` text
                         Eureka Server
                            :8761
                         /           \
                        /             \
               registration       registration
                    /                   \
                   v                     v

        Student Service          Department Service
             :8081                    :8082
                |
                | RestTemplate
                | @LoadBalanced
                |
                | http://DEPARTMENT-SERVICE/...
                +--------------------------->

Client/Postman
      |
      | GET /students/101/details
      v
Student Service
      |
      | discovers and calls Department Service
      v
Department Service
      |
      | returns department data
      v
Student Service
      |
      | combines student + department
      v
Client/Postman
```

------------------------------------------------------------------------

# 35. Key Concepts Learned So Far

## Microservice

A complete, independently runnable application responsible for a focused
business area.

## Monolith

One application containing multiple business features that run and
deploy together.

## Service-to-Service Communication

One microservice calls another over the network, here using HTTP REST.

## `RestTemplate`

A Spring HTTP client used here to send REST requests from Student
Service to Department Service.

## Serialization

``` text
Java Object → JSON
```

## Deserialization

``` text
JSON → Java Object
```

## Response DTO

A class used to shape the final API response by combining required data.

## Service Registry

A directory of registered service instances.

## Eureka Server

The service registry used in this project.

## Registration

A service announces itself to Eureka.

## Discovery

A service finds another service through registered information.

## `@LoadBalanced`

Allows `RestTemplate` to use logical service names and select an
available instance.

## Spring Cloud BOM

Manages compatible versions of Spring Cloud dependencies.

------------------------------------------------------------------------

# 36. What I Should Be Able to Explain in an Interview

### Question 1: What is a microservice?

A microservice is an independently runnable application responsible for
a specific business capability. Multiple microservices communicate over
the network to form a complete system.

### Question 2: What is the difference between a monolith and microservices?

A monolith contains multiple features in one application and deployment
unit. Microservices divide the system into independently runnable
services that communicate through network calls.

### Question 3: How did Student Service communicate with Department Service?

Student Service used `RestTemplate` to send an HTTP GET request to
Department Service and converted the returned JSON into a local
`Department` object.

### Question 4: Why was Eureka needed?

Without Eureka, Student Service used a hardcoded host and port such as
`localhost:8082`. Eureka allows services to register and be discovered
using logical service names.

### Question 5: What is the difference between Eureka and RestTemplate?

Eureka helps find available service instances. RestTemplate sends the
actual HTTP request.

### Question 6: Why use `@LoadBalanced`?

It allows `RestTemplate` to resolve a logical service name such as
`DEPARTMENT-SERVICE` and select an available service instance.

### Question 7: Is a Spring `@Service` class a microservice?

No. `@Service` marks a Java class as a Spring-managed service component.
A microservice is an entire independently runnable application.

### Question 8: Does Eureka have to use port 8761?

No. Any free port can be used, but `8761` is a common convention.

------------------------------------------------------------------------

------------------------------------------------------------------------

# 37. API Gateway Basics

## Why an API Gateway is needed

Before the gateway, the client had to know every service location:

``` text
Student Service     → localhost:8081
Department Service  → localhost:8082
```

If more services are added, the client must know more hosts and ports.
An **API Gateway** solves this by becoming the single entry point.

``` text
Without Gateway:

Client ─────→ Student Service :8081
   └────────→ Department Service :8082
```

``` text
With Gateway:

Client
   ↓
API Gateway :8080
   ├────────→ Student Service :8081
   └────────→ Department Service :8082
```

> An API Gateway is a single entry point that receives client requests
> and routes them to the correct microservice.

Two examples:

``` text
/students/**     → STUDENT-SERVICE
/departments/**  → DEPARTMENT-SERVICE
```

``` text
/orders/**       → ORDER-SERVICE
/products/**     → PRODUCT-SERVICE
```

## Gateway vs Eureka vs RestTemplate

``` text
Eureka
→ Where is a service?

API Gateway
→ Which service should receive this client request?

RestTemplate
→ Send an HTTP request from one service to another.
```

These are different responsibilities.

``` text
Client → Gateway → Student Service
```

is client-to-service routing.

``` text
Student Service → RestTemplate → Department Service
```

is service-to-service communication.

------------------------------------------------------------------------

# 38. Gateway Routes

A **route** is a rule:

> If a request matches this condition, send it to this destination.

A basic route contains:

``` text
1. ID
2. URI
3. Predicate
```

Student route:

``` text
ID:        student-service-route
Predicate: Path=/students/**
URI:       lb://STUDENT-SERVICE
```

Department route:

``` text
ID:        department-service-route
Predicate: Path=/departments/**
URI:       lb://DEPARTMENT-SERVICE
```

## Predicate

A predicate is a condition.

``` yaml
predicates:
  - Path=/students/**
```

means:

``` text
Does the request path start with /students/ ?
```

Matches:

``` text
/students/101
/students/101/details
```

Does not match:

``` text
/departments/1
/products/10
```

The `**` means anything after `/students/`.

## What `lb://` means

``` yaml
uri: lb://STUDENT-SERVICE
```

`lb` means **Load Balancer**.

``` text
lb://STUDENT-SERVICE
        ↓
Find an available STUDENT-SERVICE instance
        ↓
Route the request to it
```

Two examples:

``` text
lb://STUDENT-SERVICE
        ↓
localhost:8081
```

``` text
lb://DEPARTMENT-SERVICE
        ↓
localhost:8082
```

------------------------------------------------------------------------

# 39. API Gateway Application and Configuration

The system now has four applications:

``` text
1. eureka-server
2. student-service
3. department-service
4. api-gateway
```

Relevant Gateway dependencies:

``` xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-gateway-server-webflux</artifactId>
</dependency>

<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
</dependency>

<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-loadbalancer</artifactId>
</dependency>
```

`application.yml`:

``` yaml
spring:
  application:
    name: api-gateway

  cloud:
    gateway:
      server:
        webflux:
          routes:
            - id: student-service-route
              uri: lb://STUDENT-SERVICE
              predicates:
                - Path=/students/**

            - id: department-service-route
              uri: lb://DEPARTMENT-SERVICE
              predicates:
                - Path=/departments/**

server:
  port: 8080

eureka:
  client:
    service-url:
      defaultZone: http://localhost:8761/eureka/
```

Current ports:

``` text
API Gateway        → 8080
Student Service    → 8081
Department Service → 8082
Eureka Server      → 8761
```

Gateway request flow:

``` text
GET localhost:8080/students/101/details
        ↓
API Gateway checks predicates
        ↓
/students/** matches
        ↓
lb://STUDENT-SERVICE
        ↓
Student Service
        ↓
Student Service calls DEPARTMENT-SERVICE
        ↓
Combined response
        ↓
Gateway
        ↓
Client
```

Test URLs:

``` text
GET http://localhost:8080/students/101/details
GET http://localhost:8080/departments/1
```

Expected Eureka registrations:

``` text
API-GATEWAY        → UP
STUDENT-SERVICE    → UP
DEPARTMENT-SERVICE → UP
```

Recommended startup order:

``` text
1. Eureka Server
2. Department Service
3. Student Service
4. API Gateway
```

------------------------------------------------------------------------

# 40. Why Fault Tolerance Is Needed

The current flow is:

``` text
Client
   ↓
API Gateway
   ↓
Student Service
   ↓
Department Service
```

If Department Service is down:

``` text
Student Service     → UP
Student data        → available
Department Service  → DOWN
```

the remote call can fail and the complete student-details request can
fail.

This is a **partial failure**:

> One part of a distributed system fails while other parts may still be
> running.

------------------------------------------------------------------------

# 41. What Is Fault Tolerance?

> Fault tolerance means designing the system so that a failure in one
> service does not unnecessarily destroy the entire request or spread
> failure to other parts of the system.

Example 1:

``` text
Student data available
        +
Department Service unavailable
        ↓
Return student data with a controlled fallback
```

Possible fallback:

``` json
{
  "id": 101,
  "name": "Karthik",
  "department": {
    "id": 0,
    "name": "Department temporarily unavailable"
  }
}
```

Example 2:

``` text
Product data available
        +
Recommendation Service down
        ↓
Show product details without recommendations
```

Fault tolerance does not recreate missing data. It controls how the
system behaves when failure happens.

------------------------------------------------------------------------

# 42. Fallback vs Circuit Breaker

These concepts are related, but they are not the same.

## Fallback

A fallback answers:

> What should be returned when the normal operation fails?

Examples:

``` text
Normal:
Information Technology

Fallback:
Department temporarily unavailable
```

``` text
Normal:
Live recommendations

Fallback:
Recommendations temporarily unavailable
```

## Circuit Breaker

A Circuit Breaker answers:

> Should the application keep calling an operation that is repeatedly
> failing?

Without Circuit Breaker:

``` text
Request 1 → failed service → fail
Request 2 → failed service → fail
Request 3 → failed service → fail
Request 4 → failed service → fail
```

With Circuit Breaker:

``` text
Repeated failures
        ↓
Circuit opens
        ↓
Stop calling temporarily
        ↓
Use fallback
```

Summary:

``` text
Fallback
→ alternative response

Circuit Breaker
→ controls repeated calls to a failing operation
```

------------------------------------------------------------------------

# 43. Circuit Breaker States

## CLOSED

``` text
CLOSED
→ requests are allowed
```

Normal flow:

``` text
Student Service
      ↓
Department Service
```

## OPEN

``` text
OPEN
→ requests are temporarily blocked
```

After repeated failures:

``` text
Failures: ✗ ✗ ✗ ✗
        ↓
Circuit opens
        ↓
Avoid remote call temporarily
        ↓
Use fallback
```

## HALF_OPEN

After waiting:

``` text
OPEN
  ↓
wait
  ↓
HALF_OPEN
```

A small number of test calls are allowed.

``` text
Test succeeds → CLOSED
Test fails    → OPEN
```

Full flow:

``` text
             repeated failures
CLOSED  ─────────────────────────→  OPEN
  ↑                                  |
  |                                  | wait
  |                                  ↓
  └──────── success ─────────── HALF_OPEN
                                     |
                                     | failure
                                     ↓
                                    OPEN
```

For this fresher-level scope, advanced thresholds and sliding-window
configuration are skipped.

------------------------------------------------------------------------

# 44. Spring Framework → Spring Boot → Spring Cloud

This order is important.

## Spring Framework

Spring manages Java objects and provides features such as Dependency
Injection.

Two examples:

``` text
@Component, @Service, @Repository
→ Spring manages objects
```

``` text
Constructor Injection
→ Spring provides one object to another
```

## Spring Boot

Spring Boot makes it easier to create standalone Spring applications.

It provides:

``` text
Starter dependencies
Auto-configuration
Embedded server
application.properties / application.yml
```

Two examples:

``` text
spring-boot-starter-webmvc
→ build REST APIs
```

``` text
spring-boot-starter-data-jpa
→ work with databases
```

Student Service and Department Service are Spring Boot applications.

## Spring Cloud

When multiple Spring Boot applications work together, new
distributed-system problems appear.

``` text
How does one service find another?
→ Eureka
```

``` text
How does the client use one entry point?
→ API Gateway
```

``` text
Which available instance should be used?
→ Load Balancer
```

``` text
What happens when a remote service repeatedly fails?
→ Circuit Breaker support
```

> Spring Cloud is a collection of tools and integrations that helps
> solve common problems when multiple Spring Boot applications work
> together as distributed systems or microservices.

Most important comparison:

``` text
Spring Boot
→ helps BUILD individual applications
```

``` text
Spring Cloud
→ helps separate applications WORK TOGETHER
```

------------------------------------------------------------------------

# 45. Spring Cloud Is an Ecosystem

Spring Cloud is not one single feature.

``` text
Spring Cloud
│
├── Service Discovery
│     └── Eureka integration
│
├── API Gateway
│     └── Spring Cloud Gateway
│
├── Load Balancing
│     └── Spring Cloud LoadBalancer
│
└── Circuit Breaker abstraction
      └── can use Resilience4j
```

Two examples already used:

``` xml
spring-cloud-starter-netflix-eureka-client
```

solves service discovery.

``` xml
spring-cloud-starter-gateway-server-webflux
```

provides gateway routing.

------------------------------------------------------------------------

# 46. What Is the Circuit Breaker Pattern?

Before thinking about a Java library, understand the idea.

Suppose Department Service is down:

``` text
Student Service → call 1 → Department Service ✗
Student Service → call 2 → Department Service ✗
Student Service → call 3 → Department Service ✗
```

The application keeps calling an operation that is repeatedly failing.

The Circuit Breaker pattern says:

> If an operation keeps failing, temporarily stop calling it and use
> controlled alternative behavior such as a fallback.

Two examples:

``` text
Department Service repeatedly fails
        ↓
Temporarily stop calls
        ↓
Return "Department temporarily unavailable"
```

``` text
Recommendation Service repeatedly fails
        ↓
Temporarily stop calls
        ↓
Show product page without recommendations
```

Important:

``` text
Circuit Breaker
=
pattern / idea
```

It is not one specific Java library.

------------------------------------------------------------------------

# 47. What Is Resilience4j?

> Resilience4j is a Java fault-tolerance library that provides features
> including Circuit Breaker.

Remember the name as:

``` text
Resilience
→ ability to handle failures in a controlled way

4j
→ for Java
```

Resilience4j provides several features:

``` text
Resilience4j
│
├── Circuit Breaker  ← learn now
├── Retry            ← skip for now
├── Rate Limiter     ← skip for now
├── Bulkhead         ← skip for now
└── Time Limiter     ← skip for now
```

## Pattern vs Implementation

``` text
Circuit Breaker
=
pattern / concept
```

``` text
Resilience4j
=
Java library that implements Circuit Breaker behavior
```

Two comparisons:

``` text
List
→ interface/concept

ArrayList
→ implementation
```

``` text
Circuit Breaker
→ pattern

Resilience4j
→ implementation library
```

Second comparison:

``` text
Database
→ general concept

MySQL
→ concrete product
```

``` text
Circuit Breaker
→ general pattern

Resilience4j
→ concrete Java library
```

------------------------------------------------------------------------

# 48. Hystrix vs Resilience4j

Hystrix appears in many older microservices tutorials.

Keep the layers separate:

``` text
Concept:
Fault Tolerance

Pattern:
Circuit Breaker

Failure response:
Fallback

Older implementation/library:
Hystrix

Implementation/library used in this project:
Resilience4j
```

For this modern project, learning both implementations would waste time.
Know Hystrix for theory unless a specific assessment explicitly requires
old Hystrix code.

------------------------------------------------------------------------

# 49. Spring Cloud + Resilience4j

The relationship is:

``` text
Student Service
        ↓
Spring Boot application
        ↓
Spring Cloud Circuit Breaker API
        ↓
Resilience4j implementation
```

Dependency:

``` xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-circuitbreaker-resilience4j</artifactId>
</dependency>
```

Read the name:

``` text
spring-cloud
→ Spring-friendly abstraction/integration

circuitbreaker
→ pattern being used

resilience4j
→ actual implementation library
```

------------------------------------------------------------------------

# 50. What Is `CircuitBreakerFactory`?

`CircuitBreakerFactory` comes from the Spring Cloud Circuit Breaker
abstraction.

Break the name:

``` text
CircuitBreaker
→ protects a risky operation

Factory
→ creates or provides circuit breaker objects
```

Example:

``` java
circuitBreakerFactory.create("departmentService")
```

means:

``` text
Create/get a circuit breaker
named "departmentService"
```

Two examples:

``` java
circuitBreakerFactory.create("departmentService")
```

can protect Department Service calls.

``` java
circuitBreakerFactory.create("paymentService")
```

could protect Payment Service calls.

The name is a logical circuit-breaker name. It is not automatically the
Eureka service name.

------------------------------------------------------------------------

# 51. Planned Basic Circuit Breaker Implementation

Add to Student Service:

``` xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-circuitbreaker-resilience4j</artifactId>
</dependency>
```

Inject:

``` java
private final CircuitBreakerFactory circuitBreakerFactory;
```

Constructor:

``` java
public StudentController(
        RestTemplate restTemplate,
        CircuitBreakerFactory circuitBreakerFactory
) {
    this.restTemplate = restTemplate;
    this.circuitBreakerFactory = circuitBreakerFactory;
}
```

Protected call:

``` java
Department department =
        circuitBreakerFactory
                .create("departmentService")
                .run(
                        () -> restTemplate.getForObject(
                                "http://DEPARTMENT-SERVICE/departments/"
                                        + student.getDepartmentId(),
                                Department.class
                        ),

                        throwable -> new Department(
                                0,
                                "Department temporarily unavailable"
                        )
                );
```

Read it in English:

``` text
Create/get the departmentService circuit breaker.

Run the normal Department Service call.

If the protected operation fails,
return the fallback Department object.
```

Three parts:

``` text
create("departmentService")
→ create/get the circuit breaker
```

``` text
() -> restTemplate.getForObject(...)
→ normal operation
```

``` text
throwable -> new Department(...)
→ fallback operation
```

Expected success response:

``` json
{
  "id": 101,
  "name": "Karthik",
  "department": {
    "id": 1,
    "name": "Information Technology"
  }
}
```

Expected failure response:

``` json
{
  "id": 101,
  "name": "Karthik",
  "department": {
    "id": 0,
    "name": "Department temporarily unavailable"
  }
}
```

Important limitation:

``` text
Student Service remains useful        ✓
Student data can still be returned     ✓
Real department data is unavailable   ✗
Fallback data is returned              ✓
```

Fault tolerance controls failure behavior. It does not recreate missing
data.

------------------------------------------------------------------------

# 52. Complete Architecture So Far

``` text
                              Client / Postman
                                     |
                                     | :8080
                                     v
                                API Gateway
                               /           \
                              v             v

                  Student Service      Department Service
                       :8081                 :8082
                          |
                          | @LoadBalanced RestTemplate
                          | DEPARTMENT-SERVICE
                          |
                          | protected by
                          | Circuit Breaker
                          +------------------------->

                  Services register with Eureka

                              Eureka Server
                                  :8761
```

Normal case:

``` text
Client
  ↓
Gateway
  ↓
Student Service
  ↓
Circuit Breaker allows normal call
  ↓
Department Service
  ↓
Real combined response
```

Failure case:

``` text
Client
  ↓
Gateway
  ↓
Student Service
  ↓
Protected remote operation fails
  ↓
Fallback response
```

------------------------------------------------------------------------

# 53. Full Concept Chain

``` text
SPRING FRAMEWORK
    ↓
Manages Java objects and Dependency Injection

SPRING BOOT
    ↓
Builds each standalone application

MICROSERVICES
    ↓
Multiple independent applications

REST COMMUNICATION
    ↓
Services exchange data over HTTP

PROBLEM: HARDCODED LOCATIONS
    ↓
EUREKA SERVER
    ↓
Service registration and discovery

PROBLEM: MULTIPLE SERVICE INSTANCES
    ↓
LOAD BALANCER
    ↓
Selects an available instance

PROBLEM: CLIENT KNOWS EVERY SERVICE
    ↓
API GATEWAY
    ↓
Single entry point and routing

PROBLEM: REMOTE SERVICE CAN FAIL
    ↓
FAULT TOLERANCE
    ↓
CIRCUIT BREAKER PATTERN
    ↓
RESILIENCE4J IMPLEMENTATION
    ↓
SPRING CLOUD CIRCUITBREAKERFACTORY
    ↓
Normal operation or fallback
```

------------------------------------------------------------------------

# 54. Interview Revision Questions

### What is a microservice?

An independently runnable application responsible for a focused business
capability.

### Is a Spring `@Service` class a microservice?

No. It is one Spring-managed Java class. A microservice is the complete
independently runnable application.

### Monolith vs microservices?

A monolith keeps multiple business features in one application.
Microservices split them into independently runnable applications that
communicate over the network.

### How did Student Service call Department Service?

Using an HTTP REST call through `RestTemplate`.

### Why Eureka?

To discover services using logical names instead of depending on
hardcoded service hosts and ports.

### Eureka vs RestTemplate?

Eureka helps find a service instance. RestTemplate sends the HTTP
request.

### Why `@LoadBalanced`?

It allows RestTemplate to use logical service names and select an
available service instance.

### Why API Gateway?

To provide one client entry point and route requests to the correct
service.

### What is a gateway predicate?

A condition that decides whether a request matches a route.

### What does `lb://STUDENT-SERVICE` mean?

Use load balancing and service discovery to route to an available
`STUDENT-SERVICE` instance.

### Spring Boot vs Spring Cloud?

Spring Boot helps build individual applications. Spring Cloud helps
distributed applications work together.

### What is fault tolerance?

Controlled behavior when part of a distributed system fails.

### Fallback vs Circuit Breaker?

Fallback is the alternative response. Circuit Breaker controls repeated
calls to a failing operation.

### What is Resilience4j?

A Java fault-tolerance library that provides Circuit Breaker and other
resilience features.

### Circuit Breaker vs Resilience4j?

Circuit Breaker is the pattern. Resilience4j is a library that
implements the pattern.

### What is `CircuitBreakerFactory`?

A Spring Cloud abstraction used to create or obtain circuit breakers for
risky operations.

### Circuit Breaker states?

`CLOSED`, `OPEN`, and `HALF_OPEN`.

### Hystrix vs Resilience4j?

Both are associated with circuit-breaker implementations. Hystrix is
common in older tutorials; this project uses Resilience4j.

------------------------------------------------------------------------

# 55. Current Status

Completed:

``` text
✓ What Microservices Are
✓ Monolith vs Microservices
✓ Two Independent Spring Boot Services
✓ REST Communication Between Services
✓ Combining Data from Multiple Services
✓ Eureka Server
✓ Service Registration and Discovery
✓ @LoadBalanced RestTemplate
✓ Service-Name-Based Communication
✓ API Gateway Basics
✓ Gateway Routing
✓ Spring Cloud Big Picture
✓ Fault-Tolerance Theory
✓ Fallback vs Circuit Breaker
✓ Circuit Breaker States
✓ Resilience4j Concept
✓ CircuitBreakerFactory Concept
```

Current implementation step:

``` text
Implement and test the basic Resilience4j Circuit Breaker
in Student Service.
```

Remaining final step:

``` text
Review and clean the complete mini-project for GitHub.
```

------------------------------------------------------------------------

# 56. Final Revision Map

``` text
Large application
        ↓
Split by business responsibility
        ↓
Independent microservices
        ↓
REST communication
        ↓
Hardcoded location problem
        ↓
Eureka service registry
        ↓
Service registration and discovery
        ↓
@LoadBalanced RestTemplate
        ↓
Service-name-based communication
        ↓
Client still knows individual services
        ↓
API Gateway
        ↓
Single entry point and routing
        ↓
Remote service can fail
        ↓
Fault tolerance
        ↓
Circuit Breaker pattern
        ↓
Resilience4j implementation
        ↓
CircuitBreakerFactory
        ↓
Normal response or fallback
```

------------------------------------------------------------------------

## Project Learning Scope

This repository intentionally focuses on the foundation required for a
fresher Java Backend Developer and short Cognizant Deep Skilling
preparation.

The goal is to understand **why each Microservices component exists and
which problem it solves**.

Advanced topics remain:

``` text
Kafka
Kubernetes
Saga
CQRS
Distributed Tracing
Advanced Security
Advanced Resilience4j configuration
Retry / Bulkhead / Rate Limiter
Production Eureka clusters
```
