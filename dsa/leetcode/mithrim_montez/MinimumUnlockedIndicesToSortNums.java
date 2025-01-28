package dsa.leetcode.mithrim_montez;

public class MinimumUnlockedIndicesToSortNums {
    /**
     * https://leetcode.com/problems/minimum-unlocked-indices-to-sort-nums/description/
     *
     * The goal is to determine the minimum number of operations needed to make the nums array sortable.
     * Use a greedy approach by determining the locked/unlocked positions and the max values.
     * 
     * TC: O(n) SC: O(1)
     * #array #greedy #sort #medium
     */

    public int minUnlockedIndices(int[] nums, int[] locked) {
        if (locked.length == 1) return 0;
        int max = 0, locksUnlocked = 0, res = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > max) {
                max = nums[i];
            } else if (nums[i] < max) {
                if (nums[i] + 1 < max) return -1; // 3,2,1 => 2,3,1 => ret -1
                res += locksUnlocked;
                locksUnlocked = 0;
                // found a new max, all locks before it are unnecessary since array is already sorted till this point
                // all locks unlocked for max need to be executed
            }
            // Consider the locked status as well
            if (locked[i] == 0) {
                locksUnlocked++;
            }
        }
        return res;
    }
}