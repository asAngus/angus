# Logging with Spring AOP

Example of using Logging with Spring AOP

# System Requirements:

- Maven 3
- Tomcat

# Building the project:

```
mvn clean install
```

# Running the project and manually testing it:

```
mvn tomcat:run
```

Open browser with <http://localhost:8080/logging-spring-aop/steve.html> and <http://localhost:8080/logging-spring-aop/bill.html> and wath the term output :

```
[10-03-2015 17:52:40]INFO : com.javachen.service.UserService - getSteveJobs() executed in 20ms
[10-03-2015 17:56:24]INFO : com.javachen.service.UserService - getSteveJobs() executed in 0ms
[10-03-2015 17:56:26]INFO : com.javachen.service.UserService - getSteveJobs() executed in 0ms
```