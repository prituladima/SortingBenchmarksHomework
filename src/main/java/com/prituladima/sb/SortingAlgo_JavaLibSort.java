package com.prituladima.sb;

import java.util.Arrays;

public class SortingAlgo_JavaLibSort extends SortingAlgo {
    public SortingAlgo_JavaLibSort(int len) {
        super(len);
    }

    @Override
    void sort(int[] arr) {
        Arrays.sort(arr);
    }
}
