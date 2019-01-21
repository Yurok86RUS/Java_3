package ru.geekbrains.G3L1.taskThree;

public class GoTo {

    public <T extends Fruit> void goToFruits(Box <T> from, Box<T> to){
        while (from.getWeight() !=0){
            T fruits;
            fruits = from.get();
            to.add(fruits);
        }

        System.out.println("переложили  из коробки в коробку");
    }

}
