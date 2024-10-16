package com.sakib.io.litespring;

import com.sakib.io.litespring.annotation.Autowired;
import com.sakib.io.litespring.annotation.Component;
import com.sakib.io.litespring.annotation.PackageScan;
import com.sakib.io.litespring.annotation.Servlet;

import java.lang.reflect.Field;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LiteSpringApplication {

    private static final Map<String, Object> beanFactory = new HashMap<>();
    private static final int TOMCAT_PORT = 8080;

    public static void run(Class<?> appClass) throws Exception {
        PackageScan packageScan = appClass.getAnnotation(PackageScan.class);
        ClassLoader classLoader = LiteSpringApplication.class.getClassLoader();

        List<Class<?>> classes = new ArrayList<>();

        for (String packageName : packageScan.scanPackages()) {
            System.out.println("packageName = " + packageName);
            URL resource = classLoader.getResource(packageName.replace(".", "/"));
            System.out.println("resource.toString() = " + resource.toString());
            classes.addAll(ClassScanner.recursiveClasses(resource.getPath(), packageName));
        }

        for (Class<?> clz : classes) {
            System.out.println("clz.getName() = " + clz.getName());
        }

        createBeans(classes);
        dependencyInjection(classes);

        TomCatConfig.initTomcat(TOMCAT_PORT);
        registerServlet(classes);
    }


    public static void createBeans(List<Class<?>> classes) throws Exception {
        for (Class<?> clz : classes) {
            if (clz.isAnnotationPresent(Component.class) || clz.isAnnotationPresent(Servlet.class)) {
                Object instance = clz.getDeclaredConstructor().newInstance();
                System.out.println("clz.getName() = " + clz.getName());
                beanFactory.put(getBeanName(clz), instance);
            }
        }
        for (var bean : beanFactory.entrySet()) {
            System.out.println("bean.getKey() = " + bean.getKey());
        }
    }

    private static void registerServlet(List<Class<?>> classes) {
        for (Class<?> clz : classes) {
            if (clz.isAnnotationPresent(Servlet.class)) {
                Servlet servlet = clz.getAnnotation(Servlet.class);
                Object instance = beanFactory.get(getBeanName(clz));
                TomCatConfig.registerServlet(instance, clz.getSimpleName(), servlet.urlMapping());
            }
        }
    }

    private static void dependencyInjection(List<Class<?>> classes) throws IllegalAccessException {
        for (Class<?> clz : classes) {
            if (clz.isAnnotationPresent(Component.class) || clz.isAnnotationPresent(Servlet.class)) {
                Object clzBean = beanFactory.get(getBeanName(clz));

                Field[] fields = clz.getDeclaredFields();
                for (Field field : fields) {
                    if (field.isAnnotationPresent(Autowired.class)) {
                        Object dependentBean = beanFactory.get(field.getName().toLowerCase());
                        System.out.println("dependentBean.getClass().getName() = " + dependentBean.getClass().getName());

                        field.setAccessible(true);
                        field.set(clzBean, dependentBean);
                    }
                }
            }
        }
    }


    public static String getBeanName(Class<?> clz) {
        String[] parts = clz.getName().split("\\.");
        return parts[parts.length - 1].toLowerCase();
    }

    public static Object getBean(String name) {
        return beanFactory.get(name.toLowerCase());
    }
}
