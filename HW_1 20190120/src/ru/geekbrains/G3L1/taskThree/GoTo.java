package ru.geekbrains.G3L1.taskThree;

public class GoTo {

    public <T extends Fruit> void goToFruits(Box <T> from, Box<T> to){
        T fruits = null;
        while (from.getWeight() !=0){
            fruits = from.get();
            to.add(fruits);
        }
        
        System.out.println("переложили " + fruits.getClass().getSimpleName() + " из коробки в коробку");
    }

}
