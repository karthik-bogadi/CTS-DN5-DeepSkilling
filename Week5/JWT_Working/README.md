# JWT Authentication & Authorization — Complete Guide (Spring Boot + Spring Security)

A complete, beginner-to-advanced reference for building a JWT-secured REST API with **Spring Boot** and **Spring Security** — covering the fundamentals of application security, why JWT replaced sessions, the full JWT authentication flow, every core Spring Security component, role-based authorization, common errors, and interview questions.

> This document is a revision guide / textbook. It's meant to be read top-to-bottom once, then used as a lookup reference afterward.

---

## Table of Contents

**Part 1 — Security Fundamentals**
1. [Introduction to Security](#chapter-1-introduction-to-security)
2. [Why Security is Needed](#chapter-2-why-security-is-needed)
3. [Authentication vs Authorization](#chapter-3-authentication-vs-authorization)

**Part 2 — From Sessions to JWT**
4. [Session-Based Authentication](#chapter-4-session-based-authentication)
5. [Problems with Sessions](#chapter-5-problems-with-sessions)
6. [Introduction to JWT](#chapter-6-introduction-to-jwt)
7. [JWT Structure](#chapter-7-jwt-structure)
8. [JWT Authentication Flow](#chapter-8-jwt-authentication-flow)

**Part 3 — Building the Project**
9. [Project Architecture](#chapter-9-project-architecture)
10. [Project Structure](#chapter-10-project-structure)
11. [Dependencies](#chapter-11-dependencies)
12. [User Entity](#chapter-12-user-entity)
13. [User Repository](#chapter-13-user-repository)
14. [Register API](#chapter-14-register-api)
15. [Password Encoding](#chapter-15-password-encoding)
16. [UserDetailsService](#chapter-16-userdetailsservice)
17. [AuthenticationManager](#chapter-17-authenticationmanager)
18. [JWT Service](#chapter-18-jwt-service)
19. [Login API](#chapter-19-login-api)
20. [JWT Filter](#chapter-20-jwt-filter)
21. [SecurityContextHolder](#chapter-21-securitycontextholder)
22. [SecurityFilterChain](#chapter-22-securityfilterchain)

**Part 4 — Authorization & Production Concerns**
23. [Role-Based Authorization](#chapter-23-role-based-authorization)
24. [401 vs 403](#chapter-24-401-vs-403)
25. [Access Token vs Refresh Token](#chapter-25-access-token-vs-refresh-token)
26. [@PreAuthorize](#chapter-26-preauthorize)
27. [Common Errors](#chapter-27-common-errors)

**Part 5 — Wrap-Up**
28. [JWT Interview Questions](#chapter-28-jwt-interview-questions)
29. [Complete Request Flow](#chapter-29-complete-request-flow)
30. [Summary](#chapter-30-summary)

---

## Chapter 1: Introduction to Security

### What is Security?

Security is the process of protecting applications, systems, and data from unauthorized access, modification, or destruction. In software applications, security ensures that only legitimate users can access resources and perform allowed actions.

### Why Security Matters

Imagine a **Student Management System** with no security at all:

| Request | Without Security |
|---|---|
| `GET /students` | Anyone can access student records |
| `DELETE /students/1` | Anyone can delete data |
| `POST /students` | Anyone can create fake records |

This is dangerous because sensitive information becomes publicly accessible.

### Goals of Application Security

**1. Confidentiality** — Only authorized users should access data.
*Examples:* student marks, bank account details, medical records — these should not be visible to everyone.

**2. Integrity** — Data should not be modified by unauthorized users.
*Example:* a student should not be able to change their own marks from `50` to `100`.

**3. Availability** — Authorized users should be able to access the system whenever required.
*Example:* an online banking application — users should be able to access their accounts whenever needed.

### Security in Modern Applications

Most modern applications (Google, Facebook, Instagram, Netflix, Amazon, banking apps) combine **Authentication + Authorization** to secure APIs and resources before allowing access.

### Security Layers

```
User → Authentication → Authorization → Application Resources
```

Without passing these security checks, access is denied.

---

## Chapter 2: Why Security is Needed

### Problem Without Security

Without security, `GET /students` can be hit by any person, any browser, any script, or any application — none of them need to prove who they are.

**Example 1 — Student Management System:** `DELETE /students/1` → anyone can delete student records.

**Example 2 — Banking Application:** `POST /transfer` → anyone could transfer money.

**Example 3 — E-Commerce Application:** `DELETE /products/10` → any user could remove products.

### Security Challenges

Every application must be able to answer three questions:

1. **Who is making the request?** (e.g., Karthik, Admin, Employee, Customer)
2. **Is the user genuine?** (correct username + correct password)
3. **What is the user allowed to do?**

| Role | View | Create | Delete |
|---|---|---|---|
| USER | ✅ | ❌ | ❌ |
| ADMIN | ✅ | ✅ | ✅ |

### Solution

```
Authentication → Authorization
```

Applications implement both to solve these problems.

---

## Chapter 3: Authentication vs Authorization

*This is one of the most important interview questions.*

### Authentication — "Who are you?"

The application verifies the identity of a user.

```
Username: karthik
Password: ********

Database → Valid User? → Yes / No
```

**Examples:**
- Logging in to Gmail — Google verifies your identity.
- Logging in to a banking app — the bank verifies who you are.

### Authorization — "What are you allowed to do?"

After identity is verified, permissions are checked.

| User | Permissions |
|---|---|
| ADMIN | View Students, Add Students, Delete Students |
| USER | View Students only |

### Difference

| **Authentication** | **Authorization** |
|---|---|
| Who are you? | What can you do? |
| Identity verification | Permission verification |
| Happens first | Happens after authentication |
| Login | Access control |

### Real-World Analogy — Airport

- **Authentication:** Show your passport → verifies identity.
- **Authorization:** Check your boarding pass → verifies where you're allowed to go.

---

## Chapter 4: Session-Based Authentication

Before JWT, most applications used **Sessions**.

### Login Process

1. User sends `username` + `password` to the server.
2. Server verifies credentials.
3. If valid, a **session is created** and stored server-side.

```
Session ID: ABC123

Server memory:
ABC123 → karthik → ADMIN
```

4. Server sends the Session ID back to the browser.

### Future Requests

The client sends the session ID with every request:

```
GET /students
Cookie: JSESSIONID=ABC123
```

The server checks `ABC123 → Find User → Allow Access`.

### Full Session Flow

```
Login (username + password)
   ↓
Server creates a session
   ↓
Session ID returned
   ↓
Browser stores the session ID
   ↓
Future requests send the session ID
   ↓
Server verifies the session
   ↓
Access granted
```

### Advantages

- **Easy to implement** — simple to understand.
- **Built into many frameworks** — Spring Security, Servlets, PHP, ASP.NET.
- **Secure** — session information stays on the server.

---

## Chapter 5: Problems with Sessions

Although sessions work, they have several limitations that pushed the industry toward token-based auth.

**Problem 1 — Server Memory Usage:** the server stores every user's session. Thousands of users mean thousands of sessions, so memory consumption keeps increasing.

**Problem 2 — Scalability Issues:** if Server 1 holds session `ABC123` but a load balancer routes the next request to Server 2, Server 2 doesn't have that session — the user becomes unauthenticated.

**Problem 3 — Distributed Systems:** microservices often run across multiple servers (Auth Service, Student Service, Course Service, Payment Service). Sharing sessions across all of them is complex.

**Problem 4 — Session Replication:** to solve scalability, sessions must be copied across servers (`Server 1 → Server 2 → Server 3`). This increases complexity, network usage, and infrastructure cost.

**Problem 5 — Not Ideal for Modern APIs:** modern systems (mobile apps, SPAs, microservices, cloud applications) prefer **stateless** communication, but sessions are inherently **stateful**.

### Need for a Better Solution

Because of these limitations, modern applications moved toward **stateless authentication** using **JWT (JSON Web Token)**.

---

## Chapter 6: Introduction to JWT

### What is JWT?

**JWT = JSON Web Token.** It's a compact, secure, self-contained token used for authentication and authorization. Instead of storing session information on the server, JWT stores user information *inside the token itself*.

### Why JWT Was Introduced

Sessions suffer from server memory usage, scalability issues, session replication overhead, and microservices complexity. JWT solves all of these by making authentication **stateless**.

### Session vs JWT — Flow Comparison

**Session-based:**
```
Login → Server creates session → Server stores session
      → Client stores session ID → Future requests
      → Server checks session
```

**JWT-based:**
```
Login → Server generates JWT → Client stores JWT
      → Future requests → Server validates JWT
```

### Key Difference

| Session | JWT |
|---|---|
| Stateful — server stores user information | Stateless — server stores nothing; all info travels inside the token |

### Benefits of JWT

- **Stateless** — no session storage needed.
- **Scalable** — works easily with microservices, cloud applications, and distributed systems.
- **Lightweight** — a JWT is just a string, e.g. `eyJhbGciOiJIUzI1NiJ9...`
- **Secure** — uses digital signatures to prevent tampering.

### Real-World Applications

Google APIs, GitHub APIs, banking APIs, cloud services, and microservices — most modern REST APIs use JWT.

---

## Chapter 7: JWT Structure

A JWT consists of **three parts** separated by dots:

```
Header . Payload . Signature
xxxxx.yyyyy.zzzzz
```

### Part 1 — Header

Contains metadata about the token:

```json
{
  "alg": "HS256",
  "typ": "JWT"
}
```

- `alg` — the algorithm used for signing (e.g. `HS256`)
- `typ` — the token type (`JWT`)

The header answers: **"How was this token created?"**

### Part 2 — Payload

Contains the actual user information (claims):

```json
{
  "sub": "karthik",
  "role": "ADMIN",
  "iat": 1711111111,
  "exp": 1711121111
}
```

**Common claims:**

| Claim | Meaning | Example |
|---|---|---|
| `sub` | Subject — usually the username | `karthik` |
| `role` | User role | `ADMIN`, `USER` |
| `iat` | Issued At — when the token was created | timestamp |
| `exp` | Expiration — when the token becomes invalid | timestamp |

The payload answers: *Who is the user? What role do they have? When does the token expire?*

> ⚠️ **Important:** The payload is **NOT encrypted** — anyone can decode it. So:
> - ❌ Don't store passwords
> - ❌ Don't store sensitive data
> - ✅ Store only username, role, and non-sensitive claims

### Part 3 — Signature

The most important part — it protects the token from modification.

Suppose the payload contains `"role": "USER"`. An attacker might try to change it to `"role": "ADMIN"`. Without protection, this would be dangerous. JWT prevents it with a **signature**, generated from:

```
Header + Payload + Secret Key → Hash Function → Signature
```

The secret key exists **only on the server** — clients never receive it.

**During a request:** the server recalculates the signature from the incoming header + payload + secret key, then compares it to the JWT's signature:

- If equal → **token valid**
- If different → **token was modified → reject request**

The signature answers: **"Has the token been modified?"**

### JWT Structure Summary

| Part | Answers |
|---|---|
| Header | How was this token created? |
| Payload | Who is the user? What's their role? When does it expire? |
| Signature | Proves the token wasn't modified |

---

## Chapter 8: JWT Authentication Flow

The complete, step-by-step process:

**Step 1 — Login request**
```json
POST /login
{
  "username": "karthik",
  "password": "12345"
}
```

**Step 2 — Spring Security verifies credentials**
```
AuthController → AuthenticationManager → UserDetailsService → Repository → Database
```

**Step 3 — Password verification**
```
Raw Password → PasswordEncoder.matches() → Database Password → Valid? YES
```

**Step 4 — JWT generated:** `eyJhbGciOi...`

**Step 5 — Server returns the JWT**
```json
{ "token": "eyJhbGciOi..." }
```

**Step 6 — Client stores the JWT** (browser, mobile app, frontend application)

**Step 7 — Client calls a protected API**
```
GET /students
Authorization: Bearer eyJhbGciOi...
```

**Step 8 — `JwtAuthenticationFilter` executes** — reads the `Authorization` header and extracts the JWT token.

**Step 9 — Validate the token** — checks signature → username → expiration. Valid? YES.

**Step 10 — Authentication stored** in `SecurityContextHolder`.

**Step 11 — Role verification** (`ADMIN`? `USER`?)

**Step 12 — Controller executes**
```
StudentController → StudentService → Repository → Database
```

### Complete Flow Diagram

```
POST /login → AuthenticationManager → Database → JWT Generated → Client Stores JWT
─────────────────────────────────────────────────────────────────────────────────
GET /students → Bearer Token → JwtAuthenticationFilter → Validate Token
             → SecurityContextHolder → Role Check → Controller → Database
```

---

## Chapter 9: Project Architecture

**Login / token issuance path:**
```
Client → AuthController → AuthenticationManager → UserDetailsService
       → UserRepository → Database → JwtService → Client Receives Token
```

**Protected API request path:**
```
Protected API Request → JwtAuthenticationFilter → SecurityContextHolder
                       → SecurityFilterChain → Controller → Service
                       → Repository → Database
```

### Major Components

| Component | Responsibility |
|---|---|
| `AuthController` | Handles `/login` and `/register` |
| `AuthenticationManager` | Verifies credentials |
| `UserDetailsService` | Loads user from database |
| `JwtService` | Creates and validates JWTs |
| `JwtAuthenticationFilter` | Intercepts every request, extracts and validates JWT |
| `SecurityConfig` | Defines public APIs, protected APIs, and role rules |

---

## Chapter 10: Project Structure

```
src/main/java
│
├── controller
│   ├── AuthController
│   └── StudentController
│
├── dto
│   ├── AuthRequest
│   ├── AuthResponse
│   └── RegisterRequest
│
├── model
│   └── User
│
├── repository
│   └── UserRepository
│
├── service
│   ├── UserService
│   └── CustomUserDetailsService
│
├── security
│   ├── JwtService
│   ├── JwtAuthenticationFilter
│   └── SecurityConfig
│
└── JwtWorkingApplication
```

### Layer Responsibilities

| Layer | Responsibility |
|---|---|
| Controller | Receives HTTP requests |
| DTO | Transfers request/response data |
| Model | Represents database entities |
| Repository | Communicates with the database |
| Service | Contains business logic |
| Security | Contains JWT and Spring Security configuration |
| Application | Main Spring Boot entry point |

---

## Chapter 11: Dependencies

Before writing JWT code, add the required dependencies. Each one provides a specific feature.

| Dependency (Maven artifact) | Provides | Purpose |
|---|---|---|
| `spring-boot-starter-web` | `@RestController`, `@GetMapping`, `@PostMapping`, `@RequestBody` | Build REST APIs |
| `spring-boot-starter-security` | `SecurityFilterChain`, `AuthenticationManager`, `PasswordEncoder`, `UserDetailsService` | Authentication & Authorization |
| `spring-boot-starter-data-jpa` | `@Entity`, `JpaRepository` | Database operations |
| `mysql-connector-j` | Connection between Spring Boot and MySQL | Database driver |
| `jjwt-api`, `jjwt-impl`, `jjwt-jackson` | `Jwts.builder()`, `Jwts.parser()`, `Keys.hmacShaKeyFor()` | Generate & validate JWT |
| Lombok | `@Getter`, `@Setter`, `@NoArgsConstructor`, `@AllArgsConstructor` | Reduce boilerplate code |
| Validation (`spring-boot-starter-validation`) | `@NotBlank`, `@Email`, `@Valid` | Input validation |

These seven dependencies are enough for a complete JWT project.

---

## Chapter 12: User Entity

The `User` entity represents users stored in the database. When users register, their username, password, and role must be stored permanently — so we model them with an entity class.

```java
@Entity
public class User {

    @Id
    private int id;

    private String username;
    private String password;
    private String role;
}
```

- **`@Entity`** — tells JPA this class should become a table (`User` → `user` table).
- **`@Id`** / `private int id;` — represents the primary key that uniquely identifies a user.
- **`username`** — used during login.
- **`password`** — must store a **BCrypt hash**, never plain text.
    - ❌ Wrong: `12345`
    - ✅ Correct: `$2a$10$gHk...`
- **`role`** — stores `ADMIN` or `USER`, used for role-based authorization.

### Example Database Table

| id | username | password | role |
|---|---|---|---|
| 1 | karthik | BCrypt Hash | ADMIN |
| 2 | raju | BCrypt Hash | USER |

Later, calls like `.hasRole("ADMIN")` check the current user's stored role to decide access.

---

## Chapter 13: User Repository

The repository layer communicates with the database. Without it, there's no database access. It handles: save, find, delete, and update operations on users.

```java
public interface UserRepository extends JpaRepository<User, Integer> {
}
```

`JpaRepository` provides built-in methods — no SQL required: `save()`, `findAll()`, `findById()`, `deleteById()` are already available.

### Custom Method

```java
Optional<User> findByUsername(String username);
```

Spring automatically generates the query for this. Equivalent SQL:

```sql
SELECT * FROM user WHERE username = ?
```

### Why We Need `findByUsername()`

During login: `Username → Find User → Verify Password`. The `AuthenticationManager` depends on this query.

**Example:** user enters `karthik` → repository executes `findByUsername("karthik")` → database returns the `User` object.

---

## Chapter 14: Register API

Before logging in, users must register.

**Endpoint:** `POST /register`

**Request body:**
```json
{
  "username": "karthik",
  "password": "12345",
  "role": "ADMIN"
}
```

### `RegisterRequest` DTO

Purpose: receive registration data.

```java
public class RegisterRequest {
    private String username;
    private String password;
    private String role;
}
```

### Registration Flow

```
Client → RegisterRequest → Controller → UserService → Repository → Database
```

### Controller

```java
@PostMapping("/register")
public String register(@RequestBody RegisterRequest request) {
    User user = new User();
    user.setUsername(request.getUsername());
    user.setPassword(request.getPassword());
    user.setRole(request.getRole());

    userService.register(user);
    return "Registered";
}
```

### Registration Summary

```
POST /register → Create User Object → Encode Password → Save User → Database
```

---

## Chapter 15: Password Encoding

One of the most important security concepts.

### Problem

Suppose we store the raw password `12345` directly in the database:

| username | password |
|---|---|
| karthik | 12345 |

If the database leaks, everyone can see all the passwords. Very dangerous.

### Solution — Hash Passwords

Never store the original password. Spring Security provides a `PasswordEncoder` interface, implemented by `BCryptPasswordEncoder`.

```java
@Bean
public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
}
```

### Encoding Before Saving

```java
user.setPassword(passwordEncoder.encode(user.getPassword()));
```

**Example:** input `12345` → stored as `$2a$10$J3gk...`

| username | password |
|---|---|
| karthik | `$2a$10$J3gk...` |

### During Login

`AuthenticationManager` automatically calls:

```java
passwordEncoder.matches(rawPassword, encodedPassword);
// e.g. passwordEncoder.matches("12345", "$2a$10$J3gk...") → true
```

If it returns `true`, authentication succeeds.

### Why BCrypt?

- One-way hash (can't be reversed)
- Salt included automatically
- Industry standard
- Secure against rainbow-table attacks

### Password Encoding Summary

```
User Password → PasswordEncoder.encode() → BCrypt Hash → Database
──────────────────────────────────────────────────────────────────
Login → Raw Password → PasswordEncoder.matches() → Authentication Success
```

---

## Chapter 16: UserDetailsService

### What is `UserDetailsService`?

A Spring Security interface used to load user information from a data source. Spring Security doesn't know *where* your users are stored — you tell it (e.g., "users are stored in MySQL") by implementing this interface.

```java
public interface UserDetailsService {
    UserDetails loadUserByUsername(String username);
}
```

### Why Do We Need It?

When a user enters a username, Spring must find their password, role, and authorities from the database. `UserDetailsService` performs this lookup.

### Implementation

```java
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = repository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("User Not Found"));

        return org.springframework.security.core.userdetails.User
            .builder()
            .username(user.getUsername())
            .password(user.getPassword())   // BCrypt hash from the database
            .roles(user.getRole())          // e.g. "ADMIN" → becomes "ROLE_ADMIN"
            .build();
    }
}
```

Spring automatically calls `loadUserByUsername()` during authentication.

- **If the user exists** → the `User` object is returned and converted into `UserDetails`.
- **If the user doesn't exist** → `UsernameNotFoundException` is thrown → response is `401 Unauthorized`.

> Spring Security expects a `UserDetails` object, not your own entity — so you must convert it, as shown above. Note that `.roles("ADMIN")` is automatically converted to `ROLE_ADMIN` internally.

### Complete Flow

```
Username → UserDetailsService → Repository → Database
         → User → UserDetails → AuthenticationManager
```

---

## Chapter 17: AuthenticationManager

### What is `AuthenticationManager`?

The component responsible for verifying user credentials — it answers: *"Is this username/password correct?"*

### Authentication Flow

```
Username + Password → AuthenticationManager → UserDetailsService
                     → Database → Password Check → Success / Failure
```

### Bean Configuration

```java
@Bean
public AuthenticationManager authenticationManager(AuthenticationConfiguration config)
        throws Exception {
    return config.getAuthenticationManager();
}
```

Without this bean, `@Autowired AuthenticationManager` will fail.

### Triggering Authentication

```java
authenticationManager.authenticate(
    new UsernamePasswordAuthenticationToken(username, password)
);
```

`UsernamePasswordAuthenticationToken` represents the login credentials (username + password) — e.g.:

```java
new UsernamePasswordAuthenticationToken("karthik", "12345")
```

### Internal Flow

```
AuthenticationManager → UserDetailsService → Database
                       → PasswordEncoder.matches() → Success
```

- **Success:** no exception thrown — authentication continues.
- **Failure:** `BadCredentialsException` is thrown → response is `401 Unauthorized`.

> `AuthenticationManager` is the login verification engine.

---

## Chapter 18: JWT Service

### What is `JwtService`?

Responsible for three things: generating tokens, extracting the username, and validating tokens.

Keeping this logic in a dedicated `JwtService` (rather than inside a controller) is good design.

### Method 1 — Generate Token

```java
public String generateToken(String username) {
    return Jwts.builder()
        .subject(username)                    // stores "karthik" in the payload
        .issuedAt(new Date())                  // stores creation time
        .expiration(new Date(System.currentTimeMillis() + 3600_000)) // 1 hour lifetime
        .signWith(secretKey)                   // creates the digital signature
        .compact();                            // returns the final JWT string
}
```

*Input:* `karthik` → *Output:* `eyJhbGc...`

| Builder step | Purpose |
|---|---|
| `Jwts.builder()` | Starts token creation |
| `.subject(username)` | Stores the username in the payload |
| `.issuedAt(new Date())` | Stores the creation time |
| `.expiration(...)` | Defines the token lifetime |
| `.signWith(secretKey)` | Creates the digital signature |
| `.compact()` | Returns the final JWT string |

### Method 2 — Extract Username

*Input:* JWT → *Output:* `karthik`. Used inside the JWT filter.

### Method 3 — Validate Token

Checks: username, expiration, signature → returns `true` or `false`.

### `JwtService` Summary

```
Generate JWT: Store Username → Sign JWT → Return JWT
──────────────────────────────────────────────────────
Validate JWT: Receive JWT → Extract Username → Validate JWT → Return true/false
```

---

## Chapter 19: Login API

**Endpoint:** `POST /login` — authenticate the user and generate a JWT.

**Request:**
```json
{
  "username": "karthik",
  "password": "12345"
}
```

**DTO:** `AuthRequest` — contains `username` and `password`.

### Controller Flow

```
Request → AuthenticationManager → JWT Service → Response
```

**Step 1 — Authenticate the user**
```java
authenticationManager.authenticate(...);
```

**Step 2 — Generate the JWT**
```java
jwtService.generateToken(username);
```

**Step 3 — Create the response**
```java
new AuthResponse(token);
```

**Response:**
```json
{ "token": "eyJhbGc..." }
```

### Login Summary

```
Username + Password → AuthenticationManager → Valid User → JWT Generated → Return JWT
```

---

## Chapter 20: JWT Filter

### What is the JWT Filter?

`JwtAuthenticationFilter` intercepts **every request** before it reaches the controller.

### Why Do We Need It?

When a request like `GET /students` arrives with `Authorization: Bearer <JWT>`, something has to read that header — that's the job of `JwtAuthenticationFilter`.

```
Request → JWT Filter → Controller
```

### Steps Inside the Filter

**1. Read the header**
```java
request.getHeader("Authorization");   // e.g. "Bearer eyJhbGc..."
```

**2. Extract the token**
```java
authHeader.substring(7);   // removes "Bearer " and keeps the raw JWT
```

**3. Extract the username**
```java
jwtService.extractUsername(token);   // returns "karthik"
```

**4. Load the user**
```java
userDetailsService.loadUserByUsername(username);
```

**5. Validate the token**
```java
jwtService.isTokenValid(...);   // checks signature, username, expiration
```

**6. Create the authentication object** — a `UsernamePasswordAuthenticationToken` containing the user, authorities, and roles.

**7. Store it in the security context**
```java
SecurityContextHolder.getContext().setAuthentication(...);
```

After this, Spring Security knows the current user and current role.

**8. Continue the request**
```java
filterChain.doFilter(request, response);
```

### Flow

```
JWT Filter → Validate Token → SecurityContextHolder → Controller
```

---

## Chapter 21: SecurityContextHolder

### What is `SecurityContextHolder`?

A Spring Security class that stores the currently authenticated user for the current request — think of it as **"current logged-in user storage."**

### Why Do We Need It?

Once the JWT is validated, Spring knows `Username = karthik`, `Role = ADMIN`. That information needs somewhere to live for the rest of the request — that's `SecurityContextHolder`.

### Flow

```
JWT Filter → Validate Token → Load User → SecurityContextHolder → Controller
```

### Setting Authentication (inside the JWT filter)

```java
SecurityContextHolder.getContext().setAuthentication(authentication);
```

After this: `Current User → karthik`, `Current Role → ROLE_ADMIN`.

### Accessing the Current User (anywhere in the app)

```java
Authentication auth = SecurityContextHolder.getContext().getAuthentication();
String username = auth.getName();   // "karthik"
```

### Summary

```
JWT Valid → Authentication Object → SecurityContextHolder → Current User Available
```

---

## Chapter 22: SecurityFilterChain

### What is `SecurityFilterChain`?

The heart of Spring Security — it defines public APIs, protected APIs, role rules, and filters. Without it, every API defaults to public with no security at all.

### Configuration

```java
@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        .csrf(csrf -> csrf.disable())
        // JWT APIs are stateless, so CSRF protection isn't needed

        .sessionManagement(session ->
            session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        )
        // No session, no JSESSIONID — JWT only

        .authorizeHttpRequests(auth -> auth
            .requestMatchers("/login", "/register").permitAll()   // public APIs
            .anyRequest().authenticated()                          // JWT required
        )

        .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

    return http.build();
}
```

| Config line | Meaning |
|---|---|
| `.csrf(csrf -> csrf.disable())` | JWT APIs are stateless → no CSRF needed |
| `.sessionManagement(... STATELESS)` | No session, no JSESSIONID — JWT only |
| `.requestMatchers("/login","/register").permitAll()` | Anyone can access these |
| `.anyRequest().authenticated()` | JWT required for everything else |
| `.addFilterBefore(jwtFilter, ...)` | Runs the JWT filter before Spring's own auth filter |

### Request Flow With This Configuration

```
Request → JWT Filter → Spring Security → Controller
```

### Summary

```
SecurityFilterChain → Security Rules → JWT Filter → Role Rules → Controller Access
```

---

## Chapter 23: Role-Based Authorization

- Authentication answers: **Who are you?**
- Authorization answers: **What are you allowed to do?**

### Roles

| Username | Role |
|---|---|
| karthik | ADMIN |
| raju | USER |

### Security Rules

```java
.requestMatchers(HttpMethod.DELETE, "/students/**")
.hasRole("ADMIN")
```

This means: only `ADMIN` can hit `DELETE /students/**`.

| Role | Action | Result |
|---|---|---|
| ADMIN | `DELETE /students/1` | ✅ Allowed |
| USER | `DELETE /students/1` | ❌ Denied → `403 Forbidden` |

### `hasAnyRole()`

```java
.hasAnyRole("ADMIN", "USER")
```

Meaning: `ADMIN` **or** `USER` can access.

### `ROLE_ADMIN` vs `ADMIN`

Store `ADMIN` in the database — Spring Security automatically converts `.hasRole("ADMIN")` into `ROLE_ADMIN` internally. Don't store `ROLE_ADMIN` yourself.

### Summary

```
Authentication → Who Are You? → Authorization → What Can You Access?
```

---

## Chapter 24: 401 vs 403

*One of the most common interview questions.*

### 401 Unauthorized — Authentication Failed

**Examples:**
- No JWT: `GET /students` with no token → `401 Unauthorized`
- Invalid JWT: modified token, expired token, or wrong signature → `401 Unauthorized`

### 403 Forbidden — Authenticated, but Not Allowed

**Example:** a `ROLE_USER` tries `DELETE /students/1`, which requires `ROLE_ADMIN` → `403 Forbidden`.

### Interview Answer

```
401 → Authentication Problem
─────────────────────────────
403 → Authorization Problem
```

### Quick Memory Trick

```
401 → "Who Are You?"
──────────────────────
403 → "You Can't Do That"
```

---

## Chapter 25: Access Token vs Refresh Token

The project so far uses an **access token only**.

### Problem

If the token expires after 1 hour, the user must log in again after that hour — bad user experience.

### Solution — Access Token + Refresh Token

| | **Access Token** | **Refresh Token** |
|---|---|---|
| Purpose | Access APIs | Generate a new access token |
| Lifetime | Short (15 min – 1 hr) | Long (7–30 days) |

### Flow

```
Login → Access Token (15 min) → Refresh Token (7 days)
     → Access Token Expired → Send Refresh Token
     → Generate New Access Token → Continue Using Application
```

### Benefits

- **Better security** — short-lived access tokens limit exposure if leaked.
- **Better user experience** — no frequent logins.
- **Industry standard** — used by Google, Microsoft, GitHub, Facebook.

### Summary

| Access Token | Refresh Token |
|---|---|
| Used to access APIs | Used to generate a new access token |
| Short life | Long life |
| Sent frequently | Sent rarely |
| More exposure | More protection |

---

## Chapter 26: @PreAuthorize

### What is `@PreAuthorize`?

A Spring Security annotation used to perform authorization **at the method level**, instead of writing every rule inside `SecurityConfig`.

### Why Use It?

Without it, every rule lives in `SecurityConfig`:

```java
.requestMatchers("/students/**").hasRole("ADMIN")
```

As a project grows (more APIs, more roles, more rules), `SecurityConfig` becomes huge. The solution is to move authorization closer to the methods it protects.

```java
@GetMapping("/students")
@PreAuthorize("hasRole('ADMIN')")
public String getStudents() {
    return "Student List";
}
```

### Flow

```
Request → JWT Valid → @PreAuthorize → Role Check → Method Executes
```

### Examples

**ADMIN only:**
```java
@PreAuthorize("hasRole('ADMIN')")
```
`ADMIN` → allowed. `USER` → denied.

**Multiple roles:**
```java
@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
```
`ADMIN` or `USER` can access.

**Authority-based:**
```java
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
```
Checks the authority directly.

### Enable Method Security

Spring doesn't enable `@PreAuthorize` automatically — add this to `SecurityConfig`:

```java
@EnableMethodSecurity
```

### Summary

```
SecurityConfig     → URL-Level Security
------------------------------------------
@PreAuthorize      → Method-Level Security
```

---

## Chapter 27: Common Errors

While learning JWT, developers frequently hit the same errors. Understanding them saves hours of debugging.

### Error 1 — 401 Unauthorized

**Reason:** no JWT, invalid JWT, or expired JWT.

```
GET /students
# Expected header: Authorization: Bearer <JWT>
# Response: 401 Unauthorized
```

### Error 2 — 403 Forbidden

**Reason:** authenticated, but no permission.

**Example:** role `USER` tries `DELETE /students/1`, which requires `ADMIN` → `403 Forbidden`.

### Error 3 — User Not Found

**Reason:** the username isn't in the database. Thrown by `UsernameNotFoundException`.

**Example:** database has `karthik`, login attempt is for `raju` → `401 Unauthorized`.

### Error 4 — Bad Credentials

**Reason:** wrong password. Database has `12345`, user enters `99999` → Spring throws `BadCredentialsException`.

### Error 5 — Invalid Role

**Example:** database stores `admin` (lowercase), but `SecurityConfig` uses `.hasRole("ADMIN")`. Spring creates `ROLE_admin` but expects `ROLE_ADMIN` → `403 Forbidden`.

**Solution:** always store roles in uppercase (e.g., `ADMIN`).

### Error 6 — Password Not Encoded

```java
// ❌ Wrong
user.setPassword("12345");

// ✅ Correct
user.setPassword(passwordEncoder.encode(password));
```

If the raw password is stored directly, authentication fails because Spring expects a BCrypt hash.

### Error 7 — JWT Expired

```json
{ "exp": 1711111111 }
```

The current time exceeds the expiration time → `401 Unauthorized`.

---

## Chapter 28: JWT Interview Questions

**Q: What is JWT?**
JSON Web Token — used for stateless authentication.

**Q: Why JWT?**
It avoids sessions, server memory usage, and scalability problems.

**Q: What are the three parts of a JWT?**
Header, Payload, Signature.

**Q: What is stored in the payload?**
Username, role, claims, expiration time.

**Q: Why is the signature important?**
It prevents token tampering.

**Q: What is a Bearer token?**
`Authorization: Bearer <JWT>` — the token sent with each request.

**Q: What is Authentication?**
"Who are you?" — identity verification.

**Q: What is Authorization?**
"What can you do?" — permission verification.

**Q: Difference between Session and JWT?**

| Session | JWT |
|---|---|
| Stateful | Stateless |
| Server stores data | Client stores token |
| Session ID | JWT token |

**Q: What is `UserDetailsService`?**
Loads user information from the database.

**Q: What is `AuthenticationManager`?**
Verifies username and password.

**Q: What is `PasswordEncoder`?**
Hashes passwords using BCrypt.

**Q: What is `JwtAuthenticationFilter`?**
Reads the JWT from requests and validates it.

**Q: What is `SecurityContextHolder`?**
Stores authenticated user information.

**Q: Difference between 401 and 403?**

```
401 → Authentication Problem
403 → Authorization Problem
```

**Q: Why store roles?**
To implement role-based access control (`ADMIN`, `USER`).

---

## Chapter 29: Complete Request Flow

*The most important chapter — memorize this flow.*

### Application Startup

```
Spring Boot Start → SecurityConfig → Build SecurityFilterChain
                  → Register JwtAuthenticationFilter → Application Ready
```

### Registration — `POST /register`

```
Controller → UserService → PasswordEncoder → Repository → Database
```

### Login — `POST /login`

```
AuthController → AuthenticationManager → UserDetailsService
              → Repository → Database → PasswordEncoder.matches()
              → Success → JwtService → Generate Token → Return JWT
```

### Protected Request — `GET /students` (`Authorization: Bearer <JWT>`)

```
SecurityFilterChain → JwtAuthenticationFilter → Extract JWT
                    → Extract Username → Load User → Validate JWT
                    → SecurityContextHolder → Authorization Rules
                    → Controller → Service → Repository → Database
```

### Role Verification

```
Current User → ROLE_ADMIN → hasRole("ADMIN") → Allowed
                                       or
Current User → ROLE_USER  → hasRole("ADMIN") → 403 Forbidden
```

---

## Chapter 30: Summary

### The Security Journey

We started with **public APIs**, where anyone could access resources. We then learned:

1. **Authentication** — to verify identity.
2. **Authorization** — to control permissions.
3. **Session-based authentication** — and its limitations.
4. **JWT authentication** — which is stateless, scalable, and secure.

### Core Components Learned

- User Entity
- User Repository
- Register API
- PasswordEncoder
- UserDetailsService
- AuthenticationManager
- JwtService
- JwtAuthenticationFilter
- SecurityContextHolder
- SecurityFilterChain
- Role-Based Authorization

### Security Flow (End to End)

```
Login → Verify Credentials → Generate JWT → Client Stores JWT
     → Send JWT → Validate JWT → Role Verification
     → Access Protected Resources
```

### Final Outcome

By the end of this guide, you've built a JWT-secured REST API with:

- ✅ User Registration
- ✅ User Login
- ✅ Password Encryption
- ✅ JWT Generation
- ✅ JWT Validation
- ✅ Protected APIs
- ✅ Role-Based Authorization
- ✅ Spring Security Integration

This completes the JWT learning journey — from fundamentals to practical implementation — and serves as a complete revision guide for future reference. 🚀