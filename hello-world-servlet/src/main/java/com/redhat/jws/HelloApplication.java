package com.redhat.jws;

import com.redhat.jws.servlets.HelloServlet;

import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;

public class HelloApplication {
    public static void main(String[] args) throws Exception {
        Tomcat tomcat = new Tomcat();

        Context ctx = tomcat.addContext("", null);

        Tomcat.addServlet(ctx, "hello", new HelloServlet());

        ctx.addServletMapping("/hello", "hello");

        tomcat.start();
        tomcat.getServer().await();
    }
}
