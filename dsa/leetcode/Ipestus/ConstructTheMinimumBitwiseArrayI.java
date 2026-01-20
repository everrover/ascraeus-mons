package dsa.leetcode.Ipestus;

import java.util.*;

/*
 * https://leetcode.com/problems/construct-the-minimum-bitwise-array-i/?envType=daily-question&envId=2026-01-20
 *
 * Iterate through each number in nums, finding the minimum ans[i] that OR (ans[i] + 1) results in nums[i].
 * If no such ans[i] exists, set it to -1. Brute force over masks until solution found due to constraints.
 *
 * TC: O(n * log(max_num)) SC: O(n)
 * #array #bit-manipulation #easy
 */

public class ConstructTheMinimumBitwiseArrayI {

    public int[] minBitwiseArray(List<Integer> nums) {
        final int N = nums.size();
        int[] res = new int[N];

        for (int i = 0; i < N; i++) {
            int x = nums.get(i);
            int ress = -1;
            int d = 1;

            while ((x & d) == 0) {
                ress = x - d;
                d <<= 1;
            }
            res[i] = ress;
        }

        return res;
    }
}