package gr.project.future.insurance;

import java.util.Arrays;

public class Main {

    private static String[] bubbleSort(String arr[]) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j].compareTo(arr[j + 1]) > 0) {
                    String temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        return arr;
    }

    public static void main(String[] args) {

        String arr[] = {"d", "b", "a", "c"};
        System.out.println(Arrays.toString(arr));

        arr = bubbleSort(arr);
        System.out.println(Arrays.toString(arr));

    }
}
