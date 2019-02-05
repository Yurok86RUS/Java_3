package ru.yur0k.lesson6;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] arrIn = {1,2,3,4,5,6,7,8,9,0};
        System.out.println("Task 1: ");
        task2(arrIn);
        System.out.println("\n" + "Task 2: ");
        task3(arrIn);
    }

    private static void task2(int[] arrIn) {
        System.out.println(Arrays.toString(Task2.arr(arrIn)));
    }

    private static void task3(int[] arrIn){
        System.out.println(Task3.check(arrIn));
    }

}
