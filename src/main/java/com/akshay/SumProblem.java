package com.akshay;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.*;

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

//    public static void main(String... arg) {
//        int arr[] = {10, 2, -2, -20, 10};
//        int n = arr.length;
//        int sum = -10;
//        logger.info("Array :" + Arrays.toString(arr) + " n :" + n);
//        subArraySum(arr, n, sum);
//
//    }

        public static void main( String  [] args ) {

            Collection<String> listOne = Arrays.asList("milan", "iga",
                    "dingo", "iga",
                    "elpha", "iga",
                    "hafil", "iga",
                    "meat", "iga",
                    "neeta.peeta", "iga");

            Collection<String> listTwo = Arrays.asList("hafil",
                    "iga",
                    "binga",
                    "mike",
                    "dingo", "dingo", "dingo");

            Collection<String> similar = new HashSet<String>(listOne);

            similar.retainAll(listTwo);
            System.out.println(similar);
            ObjectMapper mapper = new ObjectMapper();
            try {
                mapper.readValue("dw", new TypeReference<List<String>>(){});
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


}
