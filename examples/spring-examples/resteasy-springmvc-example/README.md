# Spring MVC and Resteasy
---

Example of using RestEasy with Spring MVC.

This project contains three module：

- resteasy-example-client：web for the client user
- resteasy-example-core: core classes 
- resteasy-example-service: web for restful api

# System Requirements:

- Maven 3
- Tomcat

# Building the project:

```
mvn clean install
```

# Running the project and manually testing it:

Download tomcat and then copy `resteasy-example-client/target/resteasy-example-client.war` and `resteasy-example-service/target/resteasy-example-service.war` to tomcat webapp dictionary，start tomcat。

Test webservice ：http://localhost:8080/resteasy-example-service/api/getUser

Test client：http://localhost:8080/resteasy-example-client/index.html