package io.sakib.demo.design_patterns.proxy;

public class BMWCar implements Car{
    @Override
    public void makeSound() {
        System.out.println("I'm BMW");
    }
}
