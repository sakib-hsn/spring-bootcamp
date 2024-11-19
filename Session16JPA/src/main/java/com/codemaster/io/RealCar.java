package com.codemaster.io;

public class RealCar implements Car {
    @Override
    public void startEngine() {
        System.out.println("Real Car Started");
    }

    @Override
    public void stopEngine() {
        System.out.println("Real Car Stopped");
    }

    @Override
    public void makeSound() {

    }
}
