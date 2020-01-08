    References: https://www.tutorialspoint.com/spring/index.htm
    https://www.tutorialspoint.com/spring/spring_mvc_form_handling_example.htm
   

<br>

./gradlew clean eclipse build

./gradlew appRun

    Application logs are written in ~/logs/spring-mvc.log & app-log4j2.log

Web MVC:

    http://localhost:8080/spring-mvc/web/student/

Rest API:
    
    http://localhost:8080/spring-mvc/rest/student/
    
    http://localhost:8080/spring-mvc/rest/student/all
    
    http://localhost:8080/spring-mvc/rest/student/all.json
    
    http://localhost:8080/spring-mvc/rest/student/all.xml
    
    
Web Sphere 8.5 doesn't support providedCompile("javax.servlet:javax.servlet-api:3.0.1")
    
    It has to be providedCompile("javax.servlet:servlet-api:2.5")
Where as Web Sphere 9.0 supports
    
    providedCompile("javax.servlet:javax.servlet-api:3.0.1")
    
    
    
