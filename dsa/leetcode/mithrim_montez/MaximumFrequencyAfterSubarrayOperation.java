package dsa.leetcode.mithrim_montez;

public class MaximumFrequencyAfterSubarrayOperation {

    /**
     * https://leetcode.com/problems/maximum-frequency-after-subarray-operation/description/
     *
     * To find the maximum frequency of the value k after the operation, first fix the element you want to convert to k.
     * Then, use prefix sums to optimize counting occurrences of an element.
     *
     * TC: O(n) SC: O(1)
     * #prefix-sum #sliding-window #medium
     */

    public int maxFrequencyAfterOperation(int[] nums, int k) {
        int res = 0, currcnt = 0;
        for(int num: nums){
            if(num == k) currcnt++;
            else currcnt--;
            currcnt = Math.max(currcnt, 0);
            res = Math.max(currcnt, res);
        }
        // Example for tracing operation
        // // y y y y y x x k x x x k k x x k y y y y
        // Return: maximum frequency difference
        return res;
    }

    private int kadanesearch(final int[] nums, final int k, final int b) {
        return res + nummap[k];
    }
}