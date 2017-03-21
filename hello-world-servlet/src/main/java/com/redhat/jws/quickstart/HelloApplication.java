
/**
 * Copyright 2016-2017 Red Hat, Inc, and individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.redhat.jws.quickstart;

import com.redhat.jws.quickstart.servlets.HelloServlet;

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
