
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
import org.apache.tomcat.util.digester.Digester;
import org.apache.tomcat.util.IntrospectionUtils.PropertySource;

import org.apache.tomcat.vault.util.PropertySourceVault;
import java.io.File;

public class TomcatVaultApplication {
    public static void main(String[] args) throws Exception {
        // Get an instance of Tomcat
        Tomcat tomcat = new Tomcat();

        // Add an empty context
        Context ctx = tomcat.addContext("", null);

        // Add an instance of the servlet and add the servlet mapping
        Tomcat.addServlet(ctx, "hello", new HelloServlet());
        ctx.addServletMapping("/hello", "hello");

        // Grab a reference to vault.properties from src/main/resources/ which will point to all of
        // the other configuration files needed; they are also in src/main/resources/.
        File propFile = new File(TomcatVaultApplication.class.getClassLoader().getResource("vault.properties").getFile());

        // This property will allow you to load vault.properties from somewhere other than conf/
        System.setProperty("org.apache.tomcat.vault.util.VAULT_PROPERTIES", propFile.getPath());
        PropertySourceVault psv = new PropertySourceVault();

        // Print a test property from the vault
        System.out.println("Vault entry from block 'my_block' and 'attribute' manager_password: " + 
            psv.getProperty("VAULT::my_block::manager_password::"));
        
        // If you want tomcat to use the vault to decrypt strings in XML files, pass the PropertySourceVault that you
        // created above into the Digester
        Digester.setPropertySource((PropertySource) psv);

        // Start the tomcat instance
        tomcat.start();

        // Wait for control-C to stop the process to allow for testing
        tomcat.getServer().await();
    }
}
