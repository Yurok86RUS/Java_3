package ru.yur0k.lesson6;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class Task2Test {

    @ParameterizedTest
    @CsvSource({"1,2,3,4,5,6,7,8,9,0", "5,6,7,8,9,0"})

    void arr(int[] in, int[] result) {
      Task2 task2 = new Task2();
        //String res = Arrays.toString(task2.arr(in));
        int[] res = task2.arr(in);
        //assertEquals(res, Arrays.toString(result), "Error arr");
        assertEquals(res,result,"Error arr");
    }
}