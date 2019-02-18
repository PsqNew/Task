package com.learnJava;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class discSelection {
    /**
     * Varibals
     */
    private ArrayList<Integer> mainArray = new ArrayList<>();
    private int sumright = 0;
    private int sumleft = 0;
    private int sumfinal = 0;
    private int sumArray = 0;
    private int sumDuplicate = 0;
    private int halfSumArray = 0;
    /**
     * Check input data for compliance with the condition
     */
    private void dataInputCheck(int[] array) {
        boolean check = false;
        sumArray = Arrays.stream(array).sum();
        if (sumArray <= 10000) {
            for (int i = 0; i < array.length; i++) {
                if (array[i] > 20 || array[i] < 0) {
                    check = true;
                }
            }
        } else {
            System.out.println("Barbell weighs more than 10,000 pounds");
            System.exit(1);
        }
        if (check) {
            System.out.println("One of the disks has a weight that falls under the constraints of the conditions problem.");
            System.exit(2);
        }
    }
    /**
     * Checks the possibility of a simple solution
     * */
    private boolean checkSimpleSolution(int[] array) {
        if (array.length <= 2){
            if(array[0] == array[1]){
                sumfinal = array[0] + array[1];
            }
            return true;
        } return false;
    }
    /**
     * Simplifies the array
     * */
    private void duplicateInStack(int[] array){
        Arrays.sort(array);
        for (int i = 0; i < array.length;){
            if (i == array.length - 1) {
                mainArray.add(array[i]);
                break;
            }
            if (array[i] == array[i+1]){
                sumDuplicate += array[i]*2;
                i += 2;
            } else {
                mainArray.add(array[i++]);
            }
        }
    }
    /**
     * The calculation of the maximum possible sum of subsets
     * */
    private int calculateSumInStack(int sumArray, ArrayList<Integer> arrayList) {
        Collections.reverse(arrayList);
        halfSumArray = this.sumArray / 2;
        int x;
        for (int i = 0; i< arrayList.size(); i++) {
            if (Math.abs(sumleft + arrayList.get(i)) == halfSumArray ||
                    Math.abs(sumleft + arrayList.get(i) - sumright) <= Math.abs(sumright + arrayList.get(i) - sumleft)) {
                if (sumleft == halfSumArray) {
                    break;
                }
                sumleft += arrayList.get(i);
            } else {
                sumright += arrayList.get(i);
            }
        }
        if (sumleft == sumright || sumleft == sumright + sumArray) {
            if (sumArray == 0) {
                x = sumleft + sumright;
            } else {
                x = sumleft + sumright + sumArray;
            }
        } else {
            x = sumArray;
            for (int i = 0; i < arrayList.size(); i++) {
                for (int j = 1; j < arrayList.size(); j++) {
                    if (x == arrayList.get(i) + arrayList.get(j)) {
                        x += arrayList.get(i) + arrayList.get(j);
                        break;
                    }
                }
            }
        }
        return x;
    }
    /**
     *Program steps
     * */
    public int steps(int[] array){
        dataInputCheck(array);
        if (checkSimpleSolution(array) == true){
            return sumfinal;
        } else {
            duplicateInStack(array);
            sumfinal = calculateSumInStack(sumDuplicate, mainArray);
        }
        return sumfinal;
    }
}

