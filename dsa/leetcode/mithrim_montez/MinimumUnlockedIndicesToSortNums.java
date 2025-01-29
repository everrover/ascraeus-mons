package dsa.leetcode.mithrim_montez;

public class MinimumUnlockedIndicesToSortNums {
    /**
     * https://leetcode.com/problems/minimum-unlocked-indices-to-sort-nums/description/
     *
     * Basically go from left to right, and bringing the max element to the right.
     * If we encounter a number = max-1, we need to unlock all the locks for max.
     * If we encounter a number < max-1, as per the condition, we return -1, since we can't sort the array.
     * 
     * TC: O(n) SC: O(1)
     * #array #greedy #sort #medium
     */

    public int minUnlockedIndices(int[] nums, int[] locked) {
        if (locked.length == 1) return 0;
        int max = 0, locksUnlocked = 0, res = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > max) {
                // found a new max, all locks before it are unnecessary since array is already sorted till this point
                max = nums[i];
                locksUnlocked = 0;
            } else if (nums[i] < max) {
                // all locks unlocked for max need to be executed
                if (nums[i] + 1 < max) return -1; // 3,2,1 => 2,3,1 => ret -1
                res += locksUnlocked;
                locksUnlocked = 0;
            }
            // Consider the locked status as well
            locksUnlocked+=locked[i];
        }
        return res;
    }
}