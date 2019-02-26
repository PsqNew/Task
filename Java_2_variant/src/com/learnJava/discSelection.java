package com.learnJava;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class discSelection {
    /**
     * Varibals
     */
    private static int sumfinal;
    private static boolean result;
    /**
     * Check input data for compliance with the condition
     */
    private void dataInputCheck(List<Integer> array) {
        boolean check = false;
        if (array.stream().mapToInt(Integer::intValue).sum() <= 10000) {
            for (int i = 0; i < array.size(); i++) {
                if (array.get(i) > 20 || array.get(i) < 0) {
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
     *Find Stecks
     * */
    static boolean findStecks (List<Integer> arr, int n) {
        sumfinal = 0;
        for (int i = 0; i < n; i++) {
            sumfinal += arr.get(i);
        }
        if (sumfinal%2 != 0){
            return false;
        }
        boolean part[][]=new boolean[sumfinal/2+1][n+1];

        for (int i = 0; i <= n; i++) {
            part[0][i] = true;
        }
        for (int i = 1; i <= sumfinal/2; i++) {
            part[i][0] = false;
        }
        for (int i = 1; i <= sumfinal/2; i++) {
            for (int j = 1; j <= n; j++){
                part[i][j] = part[i][j-1];
                if (i >= arr.get(j-1)){
                    part[i][j] = part[i][j] || part[i - arr.get(j-1)][j-1];
                }
            }
        }
        return part[sumfinal/2][n];
    }
    /**
     *Program steps
     **/
    public void steps(Integer[] array){
        List<Integer> arrayList = new ArrayList<>();
        List<Integer> tempArray = new ArrayList<>();
        arrayList.addAll(Arrays.asList(array));
        dataInputCheck(arrayList);
        arrayList.sort(Integer::compareTo);
        if (arrayList.size() == 2){
            findStecks(arrayList,arrayList.size());
        } else {
            for (int i = 0; i < arrayList.size(); i++) {
                arrayList.sort(Integer::compareTo);
                if (!findStecks(arrayList, arrayList.size())) {
                    tempArray.add(arrayList.get(i));
                    arrayList.remove(i);
                    if (!findStecks(arrayList, arrayList.size())) {
                        arrayList.add(tempArray.get(i));
                        if (i+1 < arrayList.size()){
                            tempArray.add(arrayList.get(i + 1));
                            arrayList.remove(i + 1);
                            if (findStecks(tempArray, tempArray.size())){
                                result = true;
                                break;
                            }
                        }
                    }
                } else {
                    result = true;
                    break;
                }
            }
        }
        if (result || findStecks(arrayList, arrayList.size()) || findStecks(tempArray, tempArray.size())){
            System.out.println(sumfinal);
        } else {
            sumfinal = 0;
            System.out.println(sumfinal);
        }
    }
}

