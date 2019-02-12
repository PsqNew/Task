package com.learnJava;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class discSelection {
    /**
     * Varibals
     */
    private List<Integer> mainArray = new ArrayList<>();
    private List<Integer> rightArray = new ArrayList<>();
    private List<Integer> leftArray = new ArrayList<>();
    private int sumright = 0;
    private int sumleft = 0;
    private int sumfinal = 0;
    private int sumArray = 0;
    private int difference = 0;
    private int minElement = 0;
    private int maxElement = 0;
    private int count = 1;
    private boolean deleteDiffirence = false;

    /**
     * Print result
     */
    private void printresult() {
        System.out.println(rightArray.toString() + " " + leftArray.toString());
    }

    /**
     * Check input data for compliance with the condition
     */
    private void dataInputCheck(Integer[] array) {
        boolean check = false;
        preparation(array);
        if (sumArray <= 10000) {
            for (int i = 0; i < array.length; i++) {
                if (array[i] <= 0 && array[i] >= 20) {
                    check = true;
                }
            }
        } else {
            System.out.println("Barbell weighs more than 10,000 pounds");
        }
        if (check == true) {
            System.out.println("One of the disks has a weight that falls under the constraints of the conditions of the problem.");
        }
    }
    private void sumLeftRight() {
        leftArray.sort(Integer::compareTo);
        rightArray.sort(Integer::compareTo);
        sumright = rightArray.stream().mapToInt(Integer::intValue).sum();
        sumleft = leftArray.stream().mapToInt(Integer::intValue).sum();
        difference = Math.abs(sumright - sumleft);
    }
    /**
     * Calculation disk on the barball. Checks simple solution.
     */
    private void calculationSimple(int n) {
        while (rightArray != leftArray) {
            sumLeftRight();
            if (sumleft > sumright) {
                if (sumleft > sumright && difference != 1) {
                    rightArray.add(leftArray.get(0));
                    leftArray.remove(0);
                } else if (difference == 1 && minElement == 1) {
                    if (rightArray.contains(1) == true) {
                        rightArray.remove(0);
                    } else {
                        leftArray.remove(0);
                    }
                    break;
                }
            }
            sumLeftRight();
            if (sumleft < sumright) {
                if (sumleft < sumright && difference != 1) {
                    leftArray.add(rightArray.get(0));
                    rightArray.remove(0);
                } else if (difference == 1 && minElement == 1) {
                    if (rightArray.contains(1) == true) {
                        rightArray.remove(0);
                    } else {
                        leftArray.remove(0);
                    }
                    break;
                }
            }
            if (sumleft == sumright) {
                sumfinal = sumleft + sumright;
                printresult();
                break;
            }
            if (count == 1) {
                sumLeftRight();
                printresult();
                sumfinal = sumleft + sumright;
            }
        }
    }
    /**
     * Moves stack elements
     * */
        private void moveElement () {
            count++;
            if (sumleft < sumright) {
                if (sumleft < sumright) {
                    leftArray.add(rightArray.get(0));
                    rightArray.remove(0);
                } else if (difference == 1) {
                    rightArray.remove(0);
                }
            }
            if (sumleft > sumright) {
                if (sumleft > sumright) {
                    rightArray.add(leftArray.get(0));
                    leftArray.remove(0);
                } else if (difference == 1) {
                    leftArray.remove(0);
                }
            }
        }
        private void deleteDifference () {
            sumLeftRight();
            if (leftArray.contains(difference) == true && deleteDiffirence == false) {
                leftArray.remove(leftArray.indexOf(difference));
                deleteDiffirence = true;
            } else if (rightArray.contains(difference) == true && deleteDiffirence == false) {
                rightArray.remove(rightArray.indexOf(difference));
                deleteDiffirence = true;
            }
        }
        private void deleteOdd () {
            sumLeftRight();
            if (sumArray % 2 != 0 || count == mainArray.size() * 2) {
                if (leftArray.contains(minElement) == true) {
                    leftArray.remove(leftArray.indexOf(minElement));
                } else if (rightArray.contains(minElement) == true) {
                    rightArray.remove(rightArray.indexOf(minElement));
                }
            }
        }
        private void deleteAloneBigElement () {
            sumLeftRight();
            if (rightArray.size() == 1 && maxElement == rightArray.get(0)) {
                rightArray.add(leftArray.get(0));
                rightArray.remove(0);
                leftArray.remove(0);
            } else if (leftArray.size() == 1 && maxElement == leftArray.get(0)) {
                leftArray.add(rightArray.get(0));
                rightArray.remove(0);
                leftArray.remove(0);
            }
        }
        private void mainCalculation () {
            while (sumleft != sumright) {
                sumLeftRight();
                moveElement();
                if (mainArray.size() % 2 != 0 && sumArray % 2 == 0 && count > mainArray.size()) {
                    deleteDifference();
                }
                if (count > mainArray.size()) {
                    deleteDifference();
                    deleteOdd();
                }
                if (rightArray.size() == 1 || leftArray.size() == 1) {
                    sumLeftRight();
                    if (sumleft != sumright) {
                        deleteAloneBigElement();
                    }
                }
                sumLeftRight();
                if (sumright == sumleft || sumright == 0 || sumleft == 0 || mainArray.size() <= 2) {
                    if (sumright == 0 || sumleft == 0 || sumright != sumleft) {
                        System.out.println("The barbell will not collect");
                        break;
                    } else {
                        sumLeftRight();
                        printresult();
                        sumfinal = sumleft + sumright;
                        break;
                    }
                }
            }
            if (count == 1) {
                sumLeftRight();
                printresult();
                sumfinal = sumleft + sumright;
            }
        }
        private void preparation (Integer[]array){
            mainArray.addAll(Arrays.asList(array));
            mainArray.sort(Integer::compareTo);
            if (mainArray.contains(0) == true) {
                mainArray.remove(mainArray.indexOf(0));
            }
            for (int i = 0; i < mainArray.size(); i++) {
                if (i % 2 == 0) {
                    rightArray.add(mainArray.get(i));
                    sumright = rightArray.stream().mapToInt(Integer::intValue).sum();
                    rightArray.sort(Integer::compareTo);
                } else {
                    leftArray.add(mainArray.get(i));
                    sumleft = leftArray.stream().mapToInt(Integer::intValue).sum();
                    leftArray.sort(Integer::compareTo);
                }
            }
            sumArray = sumleft + sumright;
            difference = Math.abs(sumright - sumleft);
            minElement = mainArray.get(0);
            maxElement = mainArray.get(mainArray.size() - 1);
        }
        private void checkSimpleSolution () {
            sumLeftRight();
            if (sumArray % 2 != 0 && minElement == 1 && mainArray.size() > 2) {
                calculationSimple(minElement);
                mainCalculation();
            } else {
                mainCalculation();
            }
        }
        public int steps (Integer[]array){
            dataInputCheck(array);
            checkSimpleSolution();
            return sumfinal;
        }
    }

