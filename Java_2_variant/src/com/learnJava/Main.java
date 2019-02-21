package com.learnJava;

import java.util.Random;

public class Main {

    public static void main (String[]args){
        discSelection discSelection = new discSelection();
        Random random = new Random();
        Integer[] array = new Integer[]{7, 9, 11, 12, 13, 13, 18, 18, 18, 19};
        discSelection.steps(array);
    }
}
