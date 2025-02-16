package dsa.leetcode.VallesMarineris;

class Solution {

    /**
     * https://leetcode.com/problems/sum-of-good-numbers/description/
     * 
     * For each element in the array, check if it is strictly greater than
     * the elements at indices i-k and i+k, if they exist.
     * If so, it's a good number and is added to the result.
     * 
     * TC: O(n) SC: O(1)
     * #array #iteration #easy
     */

    public int sumOfGoodNumbers(int[] nums, int k) {
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            if (
                (i - k < 0 || nums[i - k] < nums[i]) &&
                (i + k >= nums.length || nums[i + k] < nums[i])
            ) {
                res += nums[i];
            }
        }
        return res;
    }
}