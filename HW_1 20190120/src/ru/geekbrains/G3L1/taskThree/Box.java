package ru.geekbrains.G3L1.taskThree;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Box <T extends Fruit> {

    List<T> fruits;

    public Box() {
        this.fruits = new LinkedList<T>();
    }

    public void add (T ... fruits){
        this.fruits.addAll(Arrays.asList(fruits));
    }

    public double getWeight(){
        if(fruits.size()>0){
            return fruits.get(0).getWeight()*fruits.size();
        }
        else return 0;
    }

    public boolean compare (Box<? extends Fruit> second){
        return Math.abs(this.getWeight() - second.getWeight())<0.00001;
    }

    public <T extends Fruit> T get(){
        return ((LinkedList<T>)fruits).pollLast();
    }
}
