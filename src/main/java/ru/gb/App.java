package ru.gb;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;


public class App {

        public static final int CARS_COUNT = 4;
        public static final int TUNNEL_LIMIT = CARS_COUNT / 2;


    public static void main(String[] args) throws InterruptedException {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(CARS_COUNT);
        final CountDownLatch cdl_finish = new CountDownLatch(CARS_COUNT);
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        Race race = new Race(new Road(60), new Tunnel(), new Road(40));
        Car[] cars = new Car[CARS_COUNT];
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10));
            cars[i].setCdl_finish(cdl_finish);
            cars[i].setCyclicBarrier(cyclicBarrier);
        }
        for (int i = 0; i < cars.length; i++) {
            new Thread(cars[i]).start();
        }
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
        cdl_finish.await();
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
    }
    }

