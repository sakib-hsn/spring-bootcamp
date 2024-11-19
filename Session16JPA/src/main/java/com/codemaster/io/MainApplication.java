package com.codemaster.io;

public class MainApplication {
    public static void main(String[] args) {
        RealCar realCar = new RealCar();

        CarManager carManager = new CarManager();
        Car proxyCar = new ProxyCar(realCar);

        carManager.addCar(proxyCar);

        proxyCar.startEngine();
        proxyCar.stopEngine();

    }
}