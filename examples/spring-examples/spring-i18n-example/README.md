# Spring i18n Example
---

Example of using i18n with Spring MVC.

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

And then,visit http://localhost:8080/spring-i18n-example/index.html.

# Notice 

If you are using i18n with chinese locale,you must add these lines in web.xml as below:

```xml
<filter>
       <filter-name>CharacterEncodingFilter</filter-name>
       <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
       <init-param>
           <param-name>encoding</param-name>
           <param-value>UTF-8</param-value>
       </init-param>
       <init-param>
           <param-name>forceEncoding</param-name>
           <param-value>true</param-value>
       </init-param>
   </filter>
   <filter-mapping>
       <filter-name>CharacterEncodingFilter</filter-name>
       <url-pattern>/*</url-pattern>
   </filter-mapping>
```