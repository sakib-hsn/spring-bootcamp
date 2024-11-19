package com.codemaster.io;

import java.lang.reflect.Proxy;

public class MainApplication {
    public static void main(String[] args) {
        Car realCar = new RealCar();

        // Interface Car
        // Object realCar
        // ClassLoader Car

        Car proxy = (Car) Proxy.newProxyInstance(Car.class.getClassLoader(),
                new Class<?>[]{Car.class}, new LogAdder(realCar));

        proxy.startEngine();
        proxy.stopEngine();
        proxy.makeSound();
    }
}