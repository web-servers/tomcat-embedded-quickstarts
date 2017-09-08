
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
import org.apache.catalina.connector.Connector;

import org.jboss.modcluster.container.tomcat.ModClusterListener;

public class ModClusterApplication {
    public static void main(String[] args) throws Exception {
        // Get an instance of Tomcat
        Tomcat tomcat = new Tomcat();

        // create an AJP connector
        Connector ajpConnector = new Connector("AJP/1.3");
        ajpConnector.setPort(8009);
        ajpConnector.setRedirectPort(8080);
        ajpConnector.setSecure(false);

        // Add an empty context
        Context ctx = tomcat.addContext("", null);

        // Add an instance of the servlet and add the servlet mapping
        Tomcat.addServlet(ctx, "hello", new HelloServlet());
        ctx.addServletMapping("/hello", "hello");

        // Setup the mod_cluster listener
        ModClusterListener mcl = new ModClusterListener();
        mcl.setConnectorPort(8009);
        mcl.setProxyList("localhost:6666");

        // Add the ModClusterListener to the Server
        tomcat.getServer().addLifecycleListener(mcl);

        // Add connector to tomcat
        tomcat.getService().addConnector(ajpConnector);

        // Set a JVM route so that you don't get a super long GUID
        tomcat.getEngine().setJvmRoute("embedded-node-0");

        // Start the tomcat instance
        tomcat.start();

        // Wait for control-C to stop the process to allow for testing
        tomcat.getServer().await();
    }
}
