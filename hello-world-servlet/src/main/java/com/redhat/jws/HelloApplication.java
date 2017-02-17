package com.redhat.jws;

import com.redhat.jws.servlets.HelloServlet;

import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;

public class HelloApplication {
    public static void main(String[] args) throws Exception {
        // Get an instance of Tomcat
        Tomcat tomcat = new Tomcat();

        // Add an empty context
        Context ctx = tomcat.addContext("", null);

        // Add an instance of the servlet and add the servlet mapping
        Tomcat.addServlet(ctx, "hello", new HelloServlet());
        ctx.addServletMapping("/hello", "hello");

        // Start the tomcat instance
        tomcat.start();

        // Wait for control-C to stop the process to allow for testing
        tomcat.getServer().await();
    }
}
