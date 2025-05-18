package dsa.leetcode.EuropasOceanWorld;

import java.util.*;

public class MinimumSwapsToSortByDigitSum {

  /**
   * https://leetcode.com/problems/minimum-swaps-to-sort-by-digit-sum/
   *
   * First, compute the digit sum for each number and sort
   * based on these sums. If digit sums are equal, use the
   * number itself as a tiebreaker. Then create a mapping
   * of original indices to their new sorted positions.
   * The problem then reduces to finding the minimum
   * number of swaps needed to sort this mapping, which is
   * calculated as n - number_of_cycles in the permutation.
   *
   * TC: O(n log n) SC: O(n)
   * #sorting #array #digit-sum #medium
   */

  public int minimumSwaps(int[] nums) {
    int n = nums.length;
    List<int[]> combined = new ArrayList<>();

    for (int i = 0; i < n; i++) {
      int sum = digitSum(nums[i]);
      combined.add(new int[]{nums[i], sum, i});
    }

    // Sort based on digit sums, and if equal based on the numbers themselves
    Collections.sort(combined, (a, b) -> {
      if (a[1] == b[1]) return a[0] - b[0];
      return a[1] - b[1];
    });

    // Create a map of original index to sorted positions
    int[] sortedIndexes = new int[n];
    for (int i = 0; i < n; i++) {
      sortedIndexes[i] = combined.get(i)[2];
    }

    return calculateMinSwaps(sortedIndexes, n);
  }

  private int digitSum(int num) {
    int sum = 0;
    while (num > 0) {
      sum += num % 10;
      num /= 10;
    }
    return sum;
  }

  private int calculateMinSwaps(int[] sortedIndexes, int n) {
    int swaps = 0;
    boolean[] visited = new boolean[n];
    Arrays.fill(visited, false);

    for (int i = 0; i < n; i++) {
      if (visited[i] || sortedIndexes[i] == i) continue;

      int cycleSize = 0;
      int j = i;
      while (!visited[j]) {
        visited[j] = true;
        j = sortedIndexes[j];
        cycleSize++;
      }

      if (cycleSize > 0) {
        swaps += (cycleSize - 1);
      }
    }
    return swaps;
  }
}