package ru.yur0k.lesson6;

import java.util.Arrays;

public class Task2 {

    public int[] arr (int[] array){
        boolean f = false;
        int start = 0;
        for (int i = 0; i <array.length; i++) {
            if (array[i] == 4){
                start = i+1;
                f = true;
            }
        }
        if (!f){
            throw new RuntimeException();
        }
        int[] arrays = Arrays.copyOfRange(array,start,array.length);
        return arrays;
    }

}
