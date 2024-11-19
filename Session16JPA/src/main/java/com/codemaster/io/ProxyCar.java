//package com.codemaster.io;
//
//public class ProxyCar implements Car {
//    private RealCar realCar;
//
//    public ProxyCar(RealCar realCar) {
//        this.realCar = realCar;
//    }
//
//    @Override
//    public void startEngine() {
//        callingTime();
//        realCar.startEngine();
//    }
//
//    @Override
//    public void stopEngine() {
//        callingTime();
//        realCar.stopEngine();
//    }
//
//    @Override
//    public void makeSound() {
//        callingTime();
//        realCar.makeSound();
//    }
//
//    public void callingTime() {
//        System.out.println("Calling time: " + System.currentTimeMillis());
//    }
//}
