package com.fortunate;

import java.util.Arrays;


/**
 * @author fortunate on 28/10/2022
 * @project
 */

public class AlgorithmSolution {

    public static int notifications(int[] expenseList, int checkToDay, int minimumInformation){
        int count  = 0;
        for(int i= minimumInformation; i< checkToDay; i++){
            int[] sorted = sort(expenseList, (i -minimumInformation), (i-1));//);
            int median = 0;
            if(minimumInformation % 2 == 0) {
                median = (sorted[minimumInformation / 2] + sorted[(minimumInformation / 2) - 1]) / 2;
            }else{
                median = sorted[minimumInformation / 2];
            }
            if(expenseList[i] >= 2 * median){
                count++;
            }
        }
        return count;
    }

    public static int[] sort(int[] arr, int min, int max){
        int[] sorted = new int[(max-min)+1];
        for(int i=0; i<sorted.length; i++){
            sorted[i] = arr[min];
            min++;
        }
        Arrays.sort(sorted);
        return sorted;
    }

    public static void main(String[] args) {
        System.out.println(notifications(new int[]{2, 3, 4, 2, 3, 6, 8, 4, 5}, 9, 5));
    }

}
