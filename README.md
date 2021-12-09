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
    
    
Only WAS 9.0 supports Spring 5.2.1 but not Web Sphere 8.5

    It has to be <= 5.0.0
    

Captcha: https://captcha.com/doc/java/examples/springmvc-basic-captcha-example.html

http://javainsimpleway.com/spring-mvc-hibernate-with-tomcat-jndi/



#CREATE TRIGGER tsvectorupdate_knowledge BEFORE INSERT OR UPDATE
#ON ubs.knowledge FOR EACH ROW EXECUTE FUNCTION
#tsvector_update_trigger(searchindex, 'pg_catalog.english', ticket, notes, observations);

ALTER TABLE ubs.knowledge
    ADD COLUMN indexeddata tsvector
    GENERATED ALWAYS AS (to_tsvector('english', 
            coalesce(ticket, '') 
            || ' ' || coalesce(notes, '') 
            || ' ' || coalesce(observations, '')
            || ' ' || coalesce(appid, ''))) 
    STORED;

CREATE INDEX textsearch_idx ON ubs.knowledge USING 
    GIN (indexeddata);

    
