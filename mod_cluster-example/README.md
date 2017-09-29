# Introduction

This project utilizes Apache Tomcat 8.5.20 with mod_cluster version 1.4.0.Alpha1. It requires that you install and configure mod_cluster (the defaults will do) in httpd as a load balancer for the embedded process.

# Launch

Begin by installing httpd and mod_cluster to act as the load balancer. After that's done, start it up and visit the mod_cluster_manager page, which should be empty to start. Then, compile and start the tomcat embedded process:

     # compile the servlet code
     mvn clean install
     # start the process/container
     mvn exec:java

After a few seconds refresh the manager page and you should see your new node appear (the jvmRoute for this node is "embedded-node-0").

# Test

After it's all up and running (the code will need to be updated if you run a load balancer somewhere other than localhost:6666) we can access the servlet through the load balancer by using curl:

```
curl http://localhost/hello
```
