package ru.yur0k.lesson6;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class Task2Test {

//**************************************
//    оказалось не все так просто. @CsvSource не конвертирует данные в массив автоматически. Нужно написать собственный конвертер
// *************************************

//    @ParameterizedTest
//    @CsvSource({"1,2,3,4,5,6,7,8,9,0", "5,6,7,8,9,0"})
//
//    void arr(int[] in, int[] result) {
//      Task2 task2 = new Task2();
//        //String res = Arrays.toString(task2.arr(in));
//        int[] res = task2.arr(in);
//        //assertEquals(res, Arrays.toString(result), "Error task2");
//        assertEquals(res,result,"Error arr");
//    }

    @Test
    void arr1() {
        int[] in = {1,2,3,4,5,6,7,8,9,0};
        int[] result = {5,6,7,8,9,0};
        Task2 task2 =new Task2();
        int[] res = task2.arr(in);
        assertEquals(Arrays.toString(res),Arrays.toString(result),"Error task2");
    }

    @Test
    void arr2() {
        int[] in = {1,2,3,4,5,6,7,8,9,0,4,000};
        int[] result = {000};
        Task2 task2 =new Task2();
        int[] res = task2.arr(in);
        assertEquals(Arrays.toString(res),Arrays.toString(result),"Error task2");
    }

    @Test
    void arr3() {
        Task2 task2 =new Task2();
        assertThrows(RuntimeException.class,
                () -> task2.contain(new int[]{2,3,5}));
    }
}