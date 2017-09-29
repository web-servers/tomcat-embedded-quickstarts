# Introduction

This project utilizes Apache Tomcat 8.0.36 with SpringBoot 1.4.1.RELEASE.

# Launch

     # compile the servlet code
     mvn clean install
     # start the process/container
     mvn exec:java

# Test

* We can access the servlet by using curl:

```
curl http://localhost:8080/hello
```
