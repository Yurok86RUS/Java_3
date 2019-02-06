package ru.yur0k.lesson6;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class Task3Test {

    @Test
    void check() {
        int[] in = {1,2,3,4,5,6,7,8,9,0};
        Task3 task3 =new Task3();
        boolean res = task3.check(in);
        assertEquals(res,true,"Error task3");
    }

    @Test
    void check1() {
        int[] in = {2,3,5,6,7,8,9,0};
        Task3 task3 =new Task3();
        boolean res = task3.check(in);
        assertEquals(res,false,"Error task3");
    }
}