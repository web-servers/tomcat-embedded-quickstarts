package com.redhat.jws.quickstart;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;

import org.apache.catalina.LifecycleListener;
import org.apache.catalina.core.AprLifecycleListener;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;

/**
 * You need to install the tomcat-native library and APR before you can use the Http11AprProtocol.
 */
@Configuration
public class AprConfig {

    @Bean
    public EmbeddedServletContainerFactory servletContainer() {
        TomcatEmbeddedServletContainerFactory container = new TomcatEmbeddedServletContainerFactory();

        LifecycleListener arpLifecycle = new AprLifecycleListener();
        container.setProtocol("org.apache.coyote.http11.Http11AprProtocol");
        container.addContextLifecycleListeners(arpLifecycle);

        return container;
    }

}
