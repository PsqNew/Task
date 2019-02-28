package com.learnJava;

import java.util.Random;

public class Main {

    public static void main (String[]args){
        discSelection discSelection = new discSelection();
        Random random = new Random();
        Integer[] array = new Integer[]{6, 6, 6, 6, 7, 7, 10, 10, 19, 20};
        discSelection.steps(array);
    }
}
