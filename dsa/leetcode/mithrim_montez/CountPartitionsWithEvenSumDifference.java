package dsa.leetcode.mithrim_montez;

public class CountPartitionsWithEvenSumDifference {

  /**
   * https://leetcode.com/problems/count-partitions-with-even-sum-difference/description/
   *
   * Calculate total sum and iterate through numbers.
   * For each partition, check if the difference between the total sum
   * and twice the sum of the left partition is even. If so, it's a valid partition.
   *
   * TC: O(n) SC: O(1)
   * #array #greedy #easy
   */
  public int countPartitions(int[] nums) {
    int sumt = 0, sum = 0, res = 0;
    for (int num : nums) sumt += num;
    for (int i = 0; i < nums.length - 1; i++) {
      sum += nums[i];
      // Check if the difference between total and twice the left sum is even
      if (Math.abs(sumt - sum - sum) % 2 == 0) res++;
    }
    return res;
  }
}