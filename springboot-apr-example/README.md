# Introduction

This project packages Red Hat's JWS distribution of the Apache Tomcat 8.0.36 artifacts with SpringBoot 1.4.1.RELEASE. It also uses a tomcat-native from the JWS distribution.

# Configure

Before you can run the example, you'll need to update the [pom.xml](./pom.xml) to point to your tomcat-native and APR location. Uncomment the `configuration` block in `spring-boot-maven-plugin` and update the `java.library.path` system property to point to your tomcat-native:

```
<plugin>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-maven-plugin</artifactId>
    <configuration>
        <jvmArguments>
            -Djava.library.path=/path/to/tomcat-native/
        </jvmArguments>
    </configuration>
</plugin>
```

That's all you need to configure tomcat-native for use by the APR Connector.

# Launch

     mvn spring-boot:run

# Test

This example doesn't have a running application at present. The test on whether or not this works is if it starts successfully :) It will fail if the JVM can't find tomcat-native (but it will succeed if you have tomcat-native installed and it's on the JVM's classpath).
