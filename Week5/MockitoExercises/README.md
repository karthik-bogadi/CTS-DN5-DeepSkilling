
# Mockito Complete Learning Handbook (Fresher Edition)

## Overview
Mockito is a Java Mocking Framework used together with JUnit to perform isolated unit testing.

## Topics Covered
- Why Mockito?
- Mock Objects
- mock()
- when()
- thenReturn()
- thenThrow()
- verify()
- times()
- never()
- @Mock
- @InjectMocks
- @ExtendWith(MockitoExtension.class)
- Complete Spring Boot Example
- Interview Questions

# Why Mockito?

Problem:

StudentService depends on StudentRepository.

Without Spring Boot:

```java
StudentService service = new StudentService();
```

Repository becomes null.

Mockito creates a fake repository so the service can be tested without a database.

# mock()

```java
StudentRepository repository =
        mock(StudentRepository.class);
```

Creates a fake repository object.

# Stubbing

```java
when(repository.findById(1))
        .thenReturn(Optional.of(student));
```

Meaning:

When findById(1) is called,
return the given student.

# thenThrow()

```java
when(repository.findById(100))
        .thenThrow(
                new RuntimeException("Student Not Found")
        );
```

# verify()

```java
verify(repository)
        .findById(1);
```

Confirms the method was called.

# times()

```java
verify(repository, times(2))
        .findById(1);
```

# never()

```java
verify(repository, never())
        .save(any());
```

# @Mock

```java
@Mock
StudentRepository repository;
```

Creates fake dependency automatically.

# @InjectMocks

```java
@InjectMocks
StudentService service;
```

Creates service and injects all mocks.

# @ExtendWith(MockitoExtension.class)

```java
@ExtendWith(MockitoExtension.class)
```

Enables Mockito annotations.

# Complete Example

```java
@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @Mock
    StudentRepository repository;

    @InjectMocks
    StudentService service;

    @Test
    void shouldReturnStudentWhenIdExists() {

        Student student =
                new Student(1,"Karthik");

        when(repository.findById(1))
                .thenReturn(Optional.of(student));

        Student result =
                service.getStudent(1);

        assertEquals(
                "Karthik",
                result.getName()
        );

        verify(repository)
                .findById(1);
    }
}
```

# Interview Questions

1. What is Mockito?
2. What is a Mock Object?
3. What is Stubbing?
4. Difference between @Mock and @InjectMocks?
5. Why use verify()?
6. Why use Mockito with JUnit?

# Quick Revision

| Concept | Purpose |
|----------|----------|
| mock() | Create Fake Object |
| when() | Define Behaviour |
| thenReturn() | Return Value |
| thenThrow() | Throw Exception |
| verify() | Verify Method Call |
| times() | Verify Exact Count |
| never() | Verify Not Called |
| @Mock | Create Mock |
| @InjectMocks | Inject Mocks |
| MockitoExtension | Enable Mockito |
