package dsa.leetcode.jovianMoonOrbit;

import java.util.HashMap;
import java.util.Map;

public class ContinuousSubarraySum {

    /**
     * https://leetcode.com/problems/continuous-subarray-sum/submissions/
     * 
     * To determine if the array contains a subarray whose sum is a multiple of `k`, the algorithm leverages the property of prefix sums and modular arithmetic. By maintaining a running sum and storing previously seen sums modulo `k` in a hashmap, we can efficiently check for the existence of a valid subarray.
     * 
     * TC: O(n) SC: O(n)
     * #array #hash-table #math #prefix-sum #medium
     */

    public boolean checkSubarraySum(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>(){{put(0,-1);}};
        int runningSum = 0;
        for (int i = 0; i < nums.length; i++) {
            runningSum += nums[i];
            if (k != 0) runningSum %= k;
            Integer prev = map.get(runningSum);
            if (prev != null) {
                if (i - prev > 1) return true;
            } else {
                map.put(runningSum, i);
            }
        }
        return false;
    }
}