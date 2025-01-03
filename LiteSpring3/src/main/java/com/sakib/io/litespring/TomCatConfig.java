package com.sakib.io.litespring;

import jakarta.servlet.http.HttpServlet;
import org.apache.catalina.Context;
import org.apache.catalina.Host;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

import java.io.File;

public class TomCatConfig {
    private static Tomcat tomcat;
    private static Context context;
    private static final String contextPath = "";

    protected static void initTomcat(int port) {
        try{
            tomcat = new Tomcat();
            tomcat.setPort(port);
            tomcat.getConnector();

            // Create a host
            Host host = tomcat.getHost();
            host.setName("localhost");
            host.setAppBase("webapps");

            tomcat.start();

            String docBase = new File(".").getAbsolutePath();
            context = tomcat.addWebapp(contextPath, docBase);
        } catch (LifecycleException e){
            throw new RuntimeException(e);
        }
    }

    protected static void registerServlet(Object instance, String className, String urlMapping){
        tomcat.addServlet(contextPath, className, (HttpServlet) instance);
        context.addServletMappingDecoded(urlMapping, className);
    }
}
