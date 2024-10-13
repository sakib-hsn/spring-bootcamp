package io.sakib.demo.design_patterns.proxy;

public class Main {
    public static void main(String[] args) {
        BMWCar bmwCar = new BMWProxy();
        bmwCar.makeSound();
    }
}
