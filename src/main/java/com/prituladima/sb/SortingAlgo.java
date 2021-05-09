package com.prituladima.sb;

import java.util.Random;

public abstract class SortingAlgo {

    protected static final int SMALL_SIZE = 27;

    private final int len;

    public SortingAlgo(int len) {
        this.len = len;
    }

    public String name() {
        return this.getClass().getSimpleName();
    }

    public long sortAndMeasureTime() {
        int[] array = generate(len);
        long start = System.currentTimeMillis();
        sort(array);
        long end = System.currentTimeMillis();
        validateSorted(array);
        array = null;
        return end - start;
    }

    private void validateSorted(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            if (arr[i - 1] > arr[i]) {
                throw new RuntimeException("Wrong algo in " + name());
            }
        }
    }


    private int[] generate(int len) {
        int[] ans = new int[len];
        Random random = new Random(System.currentTimeMillis());
        for (int i = 0; i < ans.length; i++) {
            ans[i] = random.nextInt() & 0x7fffffff;
        }
        return ans;
    }

    abstract void sort(int[] arr);

    protected void slowSort(int[] arr, int from, int upTo) {
//        bubbleSort(arr, from, upTo);
        insertionSort(arr, from, upTo);
    }


    protected void bubbleSort(int[] arr, int from, int upTo) {
        for (int i = from; i < upTo; i++) {
            int minVal = arr[i];
            int minInd = i;
            for (int j = i + 1; j < upTo; j++) {
                if (minVal > arr[j]) {
                    minVal = arr[j];
                    minInd = j;
                }
            }
            swap(arr, minInd, i);
        }
    }

    protected void bubbleSort(int[] arr) {
        bubbleSort(arr, 0, arr.length);
    }

    protected void insertionSort(int[] arr, int from, int upTo) {
        for (int i = from + 1; i < upTo; i++) {
            //[from..i) is sorted
            //Lets find the index where to insert
            //lower_bound
            int low = from - 1;//wrong value from left
            int high = i;//maybe correct value right
            final int value = arr[i];
            while (high - low > 1) {
                int mid = (low + high) >>> 1;
                if (value <= arr[mid]) {
                    high = mid;
                } else {
                    low = mid;
                }
            }

            int val = arr[i];
//            for(int j = i ; j > high ; j--){
//                arr[j] = arr[j - 1];
//            }
            if (i - high >= 0) System.arraycopy(arr, high, arr, high + 1, i - high);
            arr[high] = val;


        }
    }


    protected void swap(int[] arr, int first, int second) {
//        int temp = arr[first];
//        arr[first] = arr[second];
//        arr[second] = temp;

        if (arr[first] != arr[second]) {
            arr[first] = arr[first] ^ arr[second];
            arr[second] = arr[first] ^ arr[second];
            arr[first] = arr[first] ^ arr[second];
        }
    }
}
