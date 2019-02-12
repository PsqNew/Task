package com.learnJava;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Main {

    public static class discsSelection {
        private ArrayList<ArrayList<Integer>> placeInArray = new ArrayList<>();
        private static ArrayList<Integer> mainArray = new ArrayList<>();
        public static Integer halfSumArray;
        private static Integer sumArray;
        private boolean solutionCheck;

        public discsSelection(Integer sumArray, boolean solutionCheck){
            this.sumArray = sumArray;
            this.solutionCheck = solutionCheck;
        }

        public static void dataInputCheck(ArrayList<Integer> array) {
            boolean check = false;
            if (array.stream().mapToInt(Integer::byteValue).sum() <= 10000) {
                for (int i = 0; i < array.size(); i++) {
                    if (array.get(i) <= 0 && array.get(i) >= 20) {
                        check = true;
                    }
                }
            } else {
                System.out.println("Barbell weighs more than 10,000 pounds");
                System.exit(1);
            }
            if (check == true) {
                System.out.println("One of the disks has a weight that falls under the constraints of the conditions of the problem.");
                System.exit(2);
            }
        }
        public static void preparationData(Integer[] array){
            mainArray.addAll(Arrays.asList(array));
            sumArray = mainArray.stream().mapToInt(Integer::byteValue).sum();
            halfSumArray = sumArray/2;
        }
        public static void calculationAllStack(ArrayList<Integer> array,
                                               ArrayList<discsSelection> discsSelectionArray,
                                               int halfSumArray){
            ArrayList<Integer> pointInStack = new ArrayList<Integer>();
            ArrayList<Object> stackInArray = new ArrayList<Object>();
            ArrayList<Integer> bestpoint = new ArrayList<Integer>();
            int maxSize = (int) Math.pow(2, array.size());
            int sum = 0;
            for (int i = 0; i < maxSize; i++) {
                pointInStack.clear();
                sum = 0;
                for (int j = 0; j < array.size(); j++) {
                    if ((i & (1 << j)) > 0) {
                        sum += array.get(j);
                        pointInStack.add(j);

                    }
                }
                findSequenceArray(stackInArray, bestpoint,pointInStack, discsSelectionArray, halfSumArray,sum);

            }
        }
        private static void findSequenceArray(ArrayList<Object> stackInArray, ArrayList<Integer> bestpoint,
                                              ArrayList<Integer> pointInStack, ArrayList<discsSelection> discsSelectionArray,
                                              int halfSumArray, int sum) {
            boolean check = true;

            if (sum <= halfSumArray && pointInStack.size() > 0) {
                if (bestpoint.indexOf(sum) != -1) {
                    for (int i = 0; i < discsSelectionArray.size(); i++) {
                        if (discsSelectionArray.get(i).sumArray == sum) {
                            discsSelectionArray.get(i).placeInArray.add((ArrayList<Integer>) pointInStack.clone());
                            if (!discsSelectionArray.get(i).solutionCheck) {
                                findStackmatch(discsSelectionArray, i, discsSelectionArray.get(i).placeInArray);
                            }
                            check = false;
                            break;
                        }
                    }
                    if (check) {
                        discsSelectionArray.add(new discsSelection(sum, false));
                        discsSelectionArray.get(discsSelectionArray.size() - 1).
                                placeInArray.add((ArrayList<Integer>) pointInStack.clone());
                        discsSelectionArray.get(discsSelectionArray.size() - 1).
                                placeInArray.add((ArrayList<Integer>) stackInArray.get(bestpoint.indexOf(sum)));

                        findStackmatch(discsSelectionArray, (discsSelectionArray.size() - 1), discsSelectionArray.get(discsSelectionArray.size() - 1).placeInArray);
                    }
                } else {
                    bestpoint.add(sum);
                    stackInArray.add(pointInStack.clone());
                }
            }
        }
        private static void findStackmatch (ArrayList <discsSelection> discsSelectionArray,int point,
                                            ArrayList<ArrayList<Integer>> placeInArray){
            int x = 0;
            boolean check = false;
            loop:
            for (int i = 0; i < placeInArray.size(); i++) {
                for (int j = i + 1; j < placeInArray.size(); j++) {
                    x = 0;
                    for (int m = 0; m < placeInArray.get(j).size(); m++) {
                        if (placeInArray.get(i).contains(placeInArray.get(j).get(m))) {
                            x++;
                            break;
                        }
                    }
                    if (x == 0) {
                        check = true;
                        break loop;
                    }
                }
                if (check) {
                    discsSelectionArray.get(point).solutionCheck = true;
                }
            }
        }
        public static ArrayList<Integer> finalCalculations(Integer halfSumArray, ArrayList<discsSelection> discsSelectionArray) {
            ArrayList<Integer> finalStack = new ArrayList<Integer>();
            for (int i = 0; i < discsSelectionArray.size(); i++) {
                if (discsSelectionArray.get(i).solutionCheck) {
                    finalStack.add((discsSelectionArray.get(i).sumArray));
                }
            }
            return finalStack;
        }
        public static void findWeightOfTheBarbell(Integer []array){
            ArrayList<discsSelection> discsSelections = new ArrayList<discsSelection>();
            ArrayList<Integer> arrayList = new ArrayList<>();
            discsSelections.add(new discsSelection(0, true));
            arrayList.addAll(Arrays.asList(array));
            dataInputCheck(arrayList);
            preparationData(array);
            calculationAllStack(arrayList, discsSelections, halfSumArray);
            Collections.max(finalCalculations(halfSumArray, discsSelections));
            System.out.println("Maximum weight that can be lifted with a given set of disks = " + halfSumArray*2);
        }

    }
    public static void main(String[] args) {
        /**Check*/
        Integer[] array = new Integer[]{1,2,3,4,5,6};
        discsSelection.findWeightOfTheBarbell(array);
        System.out.println();
    }
}
