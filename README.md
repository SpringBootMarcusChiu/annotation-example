## Running Application
- install java version 8
- either:
    - install mvn
        - <code>mvn clean compile</code>
        - <code>mvn spring-boot:run</code>
    - use mvn wrapper
        - <code>./mvnw clean compile</code>
        - <code>./mvnw spring-boot:run</code>
- output of mvn spring-boot:run
~~~~
....
LOG: aGeneric entityClassName: com.marcuschiu.example.annotation.model.pojo.RandomPojoA
LOG: bGeneric entityClassName: com.marcuschiu.example.annotation.model.pojo.RandomPojoB
2019-12-08 13:16:00.212  INFO 2182 --- [           main] c.m.e.annotation.AnnotationApplication   : Started AnnotationApplication in 5.562 seconds (JVM running for 5.778)
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  8.351 s
[INFO] Finished at: 2019-12-08T13:16:00-06:00
[INFO] ------------------------------------------------------------------------
~~~~
    
## Confluence Article
- [http://confluence.marcuschiu.com/java-spring-annotation](http://confluence.marcuschiu.com/display/NOT/Java+-+@Annotations+With+Spring)