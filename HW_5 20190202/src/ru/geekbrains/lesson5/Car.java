package ru.geekbrains.lesson5;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class Car implements Runnable {
    private static int CARS_COUNT;
    static {
        CARS_COUNT = 0;
    }


    private Race race;
    private int speed;
    private String name;

    private final CountDownLatch startLatch;
    private final Semaphore tunnelSemaphore;
    private final CyclicBarrier finishBarrier;

    public Car(Race race, int speed, CountDownLatch startLatch, Semaphore tunnelSemaphore, CyclicBarrier finishBarrier) {
        CARS_COUNT++;
        this.race = race;
        this.speed = speed;
        this.name = "Участник #" + CARS_COUNT;

        this.startLatch = startLatch;
        this.tunnelSemaphore = tunnelSemaphore;
        this.finishBarrier = finishBarrier;
    }

    public String getName() {
        return name;
    }
    public int getSpeed() {
        return speed;
    }

    @Override
    public void run() {
        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int)(Math.random() * 800));
            System.out.println(this.name + " готов");
            // Машина подъехала к старту, уменьшаем счетчик и блокируем поток
            startLatch.countDown();
            startLatch.await();
            for (int i = 0; i < race.getStages().size(); i++) {
                // Если этап тоннель то запрашиваем семафор
                if(race.getStages().get(i) instanceof Tunnel) {
                    tunnelSemaphore.acquire();
                    race.getStages().get(i).go(this);
                    tunnelSemaphore.release();
                    continue;
                }
                race.getStages().get(i).go(this);
            }
            synchronized (race) {
                if(!this.race.hasWinner()) {
                    System.out.println(this.name + " ПОБЕДИТЕЛЬ");
                    this.race.setWinner();
                }
            }
            // Машина подъехала к финишу, уменьшаем счетчик и блокируем поток
            finishBarrier.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}