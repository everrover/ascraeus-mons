package dsa.leetcode.VallesMarineris;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/eat-pizzas/description/
 *
 * To maximize the weight gain, we need to strategically select pizzas based on the day (odd or even).
 *
 * - On odd-numbered days, select the largest pizza and three smallest.
 * - On even-numbered days, choose two largest and two smallest pizzas.
 *
 * Using this greedy approach ensures an optimal solution.
 *
 * TC: O(n log n) SC: O(1)
 * #greedy #array #sorting #medium
 */

class Solution {
  public long maxWeight(int[] pizzas) {
    Arrays.sort(pizzas);  // Sort the pizzas to easily access smallest and largest
    final int n = pizzas.length / 4;
    long res = 0;
    int i = 4 * n - candsize, j = 4 * n - 1, k = 1;
    final int candsize = ((n / 2) * 3) + (n % 2 == 0 ? 0 : 1);
    while (k <= n) {
      if (k % 2 == 0) {  // Even day: add two largest distinct pizzas
        res += pizzas[j];
        j--;
      } else {  // Odd day: add one smallest and three largest pizzas
        res += pizzas[j];
        j--;
        res += pizzas[i];
        i += 2;
      }
      k++;
    }
    return res;
  }
}