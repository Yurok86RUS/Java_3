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
        Task2 task2 = new Task2();
        System.out.println(Arrays.toString(task2.arr(arrIn)));
    }

    private static void task3(int[] arrIn){
        Task3 task3 = new Task3();
        System.out.println(task3.check(arrIn));
    }

}
