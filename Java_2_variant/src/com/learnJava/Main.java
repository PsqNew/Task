package com.learnJava;

import java.util.Random;

public class Main {

    public static void main (String[]args){
        discSelection discSelection = new discSelection();
        Random random = new Random();
        Integer[] array = new Integer[]{1,2,4,8,16};
        discSelection.steps(array);
    }
}
