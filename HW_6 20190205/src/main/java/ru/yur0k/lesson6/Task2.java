package ru.yur0k.lesson6;

import java.util.Arrays;

public class Task2 {

    public int[] arr (int[] array) throws RuntimeException {
        contain(array);
        int start = 0;
        for (int i = 0; i <array.length; i++) {
            if (array[i] == 4){
                start = i+1;
            }
        }
        int[] arrays = Arrays.copyOfRange(array,start,array.length);
        return arrays;
    }

    public void contain (int[] array1){
        boolean f = false;
        for (int i = 0; i < array1.length; i++) {
            if (array1[i] == 4){
                f =true;
            }
        }
        if (!f){
            throw new RuntimeException();
        }
    }

}
