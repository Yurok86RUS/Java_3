package ru.geekbrains.G3L1;

import java.util.ArrayList;

public class TaskTwo {

    public static void main(String[] args) {

        Integer[] integers = new Integer[] {0,1,2,3,4,5,6,7,8,9};
        String [] strings = new String[] {"zero", "one", "two", "three"};

        ArrayList<Integer> integers1 = transfer(integers);
        System.out.println(integers1);

        System.out.println();

        ArrayList<String> strings1 = transfer(strings);
        System.out.println(strings1);

    }

    static <S>ArrayList<S> transfer (S [] array) {
        System.out.println(array.getClass());
        final ArrayList<S> arrayList = new ArrayList<S>();
        for(S c : array){
            arrayList.add(c);
        }
        return arrayList;
    }
}
