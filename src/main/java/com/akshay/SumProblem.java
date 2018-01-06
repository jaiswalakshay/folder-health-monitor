package com.akshay;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SumProblem {

    private static Logger logger = LoggerFactory.getLogger(SumProblem.class);

    private static void subArraySum(int arr[], int n, int sum) {
        Map<Integer, Integer> map = new HashMap<>();

        int curr_sum = 0;

        for (int i = 0; i < n; i++) {
            // add current element to curr_sum
            curr_sum = curr_sum + arr[i];

            // if curr_sum is equal to target sum
            // we found a subarray starting from index 0
            // and ending at index i
            if (curr_sum == sum) {
                logger.info("Array :" + Arrays.toString(arr) + " nn :" + i + " total1 : " + n + " actual :" + arr.length);
                logger.info("SubArray : " + Arrays.toString(Arrays.copyOfRange(arr, 0, i + 1)));
                return;
            }

            // If curr_sum - sum already exists in map
            // we have found a subarray with target sum
            if (map.get(curr_sum - sum) != null) {
                logger.info("SubArray 2: " + Arrays.toString(Arrays.copyOfRange(arr, map.get(curr_sum - sum), i + 1)));
                return;
            }

            map.put(curr_sum, i);
        }
        logger.info("No subarray with given sum exists");
    }

    public static void main(String... arg) {
        int arr[] = {10, 2, -2, -20, 10};
        int n = arr.length;
        int sum = -10;
        logger.info("Array :" + Arrays.toString(arr) + " n :" + n);
        subArraySum(arr, n, sum);

    }
}
