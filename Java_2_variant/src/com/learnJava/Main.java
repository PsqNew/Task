package com.learnJava;

import java.util.Random;

public class Main {

    public static void main (String[]args){
        discSelection discSelection = new discSelection();
        Random random = new Random();
        int sumfinal;
        int[] array = new int[]{1,2,3,4,5,6};
        sumfinal = discSelection.steps(array);
        if(sumfinal > 0 ){
            System.out.println(sumfinal);
        }
    }
}
