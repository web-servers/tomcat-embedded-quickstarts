# Introduction

This project demonstrates the use of Apache Tomcat embedded artifacts to run a single servlet.


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
