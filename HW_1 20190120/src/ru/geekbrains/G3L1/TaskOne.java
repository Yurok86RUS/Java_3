package ru.geekbrains.G3L1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TaskOne {

    public static void main(String[] args) {
	// write your code here

        List<Integer> integers = new ArrayList<Integer>(Arrays.asList(0,1,2,3,4,5,6,7,8,9));
        System.out.println("Исходный массив: " + integers);
        change(integers,0,9);
        System.out.println("измененный массив: " + integers);

        System.out.println("Другой массив");

        List<String> strings = new ArrayList<String>(Arrays.asList("zero", "one", "two", "three"));
        System.out.println("Исходный массив: " + strings);
        change(strings,1,2);
        System.out.println("измененный массив: " + strings);

    }

    private static <T> void change (List<T> list, int place1, int place2) throws IndexOutOfBoundsException {

        T element1 = list.get(place1);
        T element2 = list.get(place2);
        list.set(place1, element2);
        list.set(place2, element1);

    }

}
