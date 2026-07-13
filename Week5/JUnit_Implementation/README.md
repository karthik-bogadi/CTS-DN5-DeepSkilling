# JUnit 5 Complete Learning Handbook (Fresher Edition)

> A practical reference for Java & Spring Boot developers.

------------------------------------------------------------------------

# Table of Contents

1.  Introduction
2.  Why Testing?
3.  JUnit Overview
4.  Maven Setup
5.  Project Structure
6.  @Test
7.  Assertions
8.  AAA Pattern
9.  Test Lifecycle
10. @DisplayName
11. @Disabled
12. Exception & Timeout Testing
13. Parameterized Tests
14. Best Practices
15. Common Mistakes
16. Interview Questions
17. Quick Revision

------------------------------------------------------------------------

# 1. Introduction

## What is JUnit?

JUnit is a Java testing framework used to write and execute automated
unit tests.

Instead of checking output manually using `System.out.println()`, JUnit
automatically verifies whether the expected result matches the actual
result.

Flow:

    Write Test
        ↓
    Run Test
        ↓
    JUnit Executes
        ↓
    Compare Expected vs Actual
        ↓
    PASS / FAIL

------------------------------------------------------------------------

# Why Unit Testing?

Benefits

-   Detect bugs early
-   Prevent regressions
-   Increase confidence while changing code
-   Easy refactoring
-   Required in almost every enterprise Spring Boot project

------------------------------------------------------------------------

# Maven Setup

Spring Boot already provides

``` xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-test</artifactId>
    <scope>test</scope>
</dependency>
```

Contains

-   JUnit 5
-   Mockito
-   AssertJ
-   Hamcrest
-   Spring Test

------------------------------------------------------------------------

# Project Structure

    src
    ├── main
    │     └── java
    │
    └── test
          └── java

Production code → src/main/java

Test code → src/test/java

------------------------------------------------------------------------

# @Test

Marks a method as a JUnit test.

``` java
@Test
void shouldReturnSum(){

}
```

No main() method required.

------------------------------------------------------------------------

# Assertions

Assertions compare Expected vs Actual.

## assertEquals()

``` java
assertEquals(30, calculator.add(10,20));
```

Use for

-   Numbers
-   Strings
-   Characters

------------------------------------------------------------------------

## assertTrue()

``` java
assertTrue(service.isEligible(80));
```

Use when expecting true.

------------------------------------------------------------------------

## assertFalse()

``` java
assertFalse(service.isEligible(18));
```

Use when expecting false.

------------------------------------------------------------------------

## assertNull()

``` java
assertNull(service.getStudent(30));
```

Use when object should not exist.

------------------------------------------------------------------------

## assertNotNull()

``` java
assertNotNull(service.getStudent(20));
```

Use when object must exist.

------------------------------------------------------------------------

## assertThrows()

``` java
assertThrows(
        ArithmeticException.class,
        ()->service.divide(10,0)
);
```

Use when exception is expected.

------------------------------------------------------------------------

## assertDoesNotThrow()

``` java
assertDoesNotThrow(
        ()->service.divide(10,2)
);
```

Use when no exception should occur.

------------------------------------------------------------------------

## assertTimeout()

``` java
assertTimeout(
        Duration.ofSeconds(2),
        ()->service.findAll()
);
```

Checks execution time.

------------------------------------------------------------------------

# Arrange Act Assert Pattern

Professional format

``` java
@Test
void shouldReturnStudent(){

    // Arrange
    StudentService service = new StudentService();

    // Act
    Student student = service.getStudent(1);

    // Assert
    assertNotNull(student);
}
```

Arrange

Prepare objects.

Act

Execute method.

Assert

Verify result.

------------------------------------------------------------------------

# Test Lifecycle

Execution order

    @BeforeAll

    ↓

    @BeforeEach

    ↓

    @Test

    ↓

    @AfterEach

    ↓

    @AfterAll

------------------------------------------------------------------------

## @BeforeEach

Runs before every test.

``` java
@BeforeEach
void setup(){
    service = new StudentService();
}
```

Use for reusable setup.

------------------------------------------------------------------------

## @AfterEach

Runs after every test.

Used for cleanup.

------------------------------------------------------------------------

## @BeforeAll

Runs only once before all tests.

``` java
@BeforeAll
static void init(){

}
```

Why static?

Because JUnit executes it before creating test objects.

------------------------------------------------------------------------

## @AfterAll

Runs once after all tests.

------------------------------------------------------------------------

# @DisplayName

``` java
@Test

@DisplayName("Should return student when ID exists")
void shouldReturnStudentWhenIdExists(){

}
```

Produces readable reports.

------------------------------------------------------------------------

# Test Naming

Use

    should + Expected + When + Condition

Examples

    shouldSaveStudentSuccessfully()

    shouldReturnStudentWhenIdExists()

    shouldThrowExceptionWhenIdDoesNotExist()

Avoid

    test1()

    abc()

    demo()

------------------------------------------------------------------------

# @Disabled

Temporarily skips a test.

``` java
@Test

@Disabled("Waiting for payment module")

void paymentTest(){

}
```

------------------------------------------------------------------------

# Parameterized Tests

Instead of

``` java
@Test
void test1(){}

@Test
void test2(){}
```

Use

``` java
@ParameterizedTest
@ValueSource(ints={20,25,30})
void shouldReturnTrue(int age){

    assertTrue(service.checkEligibility(age));

}
```

Runs three times automatically.

------------------------------------------------------------------------

# Best Practices

✔ One behavior per test

✔ Use AAA Pattern

✔ Use meaningful names

✔ Independent tests

✔ Keep tests small

✔ Avoid shared state

✔ Test Service layer first

✔ Use @BeforeEach for repeated setup

✔ Use Parameterized Tests for multiple inputs

------------------------------------------------------------------------

# Common Beginner Mistakes

❌ Forgetting @Test

❌ Writing tests in src/main/java

❌ Using @Autowired without SpringBootTest

❌ Testing multiple behaviors in one test

❌ Depending on previous tests

❌ Using System.out.println instead of assertions

------------------------------------------------------------------------

# Spring Boot + JUnit

Normal Unit Test

    JUnit

    ↓

    new Service()

    ↓

    Run Test

Spring Boot NOT started.

@Autowired will not work.

Use

``` java
@SpringBootTest
```

only when Spring Context is required.

------------------------------------------------------------------------

# Why Test Methods are void?

JUnit decides PASS/FAIL using assertions.

Return values are ignored.

------------------------------------------------------------------------

# Interview Questions

### What is JUnit?

Java framework for automated unit testing.

### Why @BeforeEach?

To create fresh objects before every test.

### Why @BeforeAll is static?

Runs before any test instance exists.

### Difference between assertTrue and assertEquals(true,value)?

assertTrue is cleaner for boolean conditions.

### Why doesn't @Autowired work in normal JUnit?

Because Spring Context is not started.

### Why use AAA?

Improves readability and maintainability.

------------------------------------------------------------------------

# Quick Revision

  Item                 Purpose
  -------------------- ------------------------------
  @Test                Test Method
  @BeforeEach          Before every test
  @AfterEach           After every test
  @BeforeAll           Once before all tests
  @AfterAll            Once after all tests
  @DisplayName         Friendly test name
  @Disabled            Skip test
  assertEquals         Compare values
  assertTrue           Expect true
  assertFalse          Expect false
  assertNull           Expect null
  assertNotNull        Expect object
  assertThrows         Expect exception
  assertDoesNotThrow   Expect no exception
  assertTimeout        Verify execution time
  @ParameterizedTest   Run same test multiple times
  @ValueSource         Supply input values

------------------------------------------------------------------------

# Final Learning Flow

    Testing Fundamentals

    ↓

    JUnit

    ↓

    Mockito

    ↓

    Spring Boot Testing

    ↓

    TDD

    ↓

    Logging

    ↓

    Automation Testing

This handbook covers the complete JUnit fundamentals required for
freshers, CTS DN 5.0, and Java Spring Boot backend interviews.
