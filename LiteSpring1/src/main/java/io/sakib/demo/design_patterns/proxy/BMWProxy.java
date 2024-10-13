package io.sakib.demo.design_patterns.proxy;

public class BMWProxy extends BMWCar {

    @Override
    public void makeSound() {
        System.out.println("I'm proxy");
        super.makeSound();
        System.out.println("I'm done");
    }
}
