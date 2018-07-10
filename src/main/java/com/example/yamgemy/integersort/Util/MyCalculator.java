package com.example.yamgemy.integersort.Util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class MyCalculator {

    public MyCalculator(){

    }

    public ArrayList<Integer> get2MaxNumsfromStack(Stack stackOfInts) {
        ArrayList<Integer> result = new ArrayList<>();

        //dup stack for operation
        Stack<Integer> copyStack = new Stack();
        copyStack.addAll(stackOfInts);

        //remove duplicates
        Set<Integer> set1 = new HashSet<>();
        set1.addAll(copyStack);
        copyStack.clear();
        copyStack.addAll(set1);

        //find biggest
        while (result.size()!=2){
            Integer b = popBiggest(copyStack);
            result.add(b);
        }
        return result;
    }

    private Integer popBiggest(Stack<Integer> copyStack){
        int tempmax_biggest = copyStack.get(0);
        for (int p = 0; p<copyStack.size()-1; p++){
            int next = copyStack.get(p+1);
            if (next > tempmax_biggest) {
                tempmax_biggest = next;
            }
        }
        Integer x = copyStack.remove(copyStack.indexOf(tempmax_biggest));
        return x;
    }
}
