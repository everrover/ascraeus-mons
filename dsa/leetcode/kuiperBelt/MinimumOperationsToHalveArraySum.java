package dsa.leetcode.kuiperBelt;

import java.util.Collections;
import java.util.PriorityQueue;

public class MinimumOperationsToHalveArraySum {

  /**
   * https://leetcode.com/problems/minimum-operations-to-halve-array-sum/
   * 
   * This method uses a priority queue to always halve the maximum element in the array.
   * By doing so, it ensures that the sum is reduced as quickly as possible.
   * 
   * | I was thinking that a straightforward solution will get TLE, and we need some clever way to halve large numbers.
   * | Then I realized that large numbers would also reduce sum quite a lot, so I went for the straightforward one.
   * | - @votrubac
   * 
   * So did I. 🙃
   * 
   * TC: O(n log n) SC: O(n)
   * #heap #priority-queue #greedy #medium
   */
  
  public int halveArray(int[] nums) {
    double s = 0, k = 0; int i = 0;
    PriorityQueue<Double> pq = new PriorityQueue<>(Collections.reverseOrder());
    for (int x: nums) {
      pq.offer((double)x);
      s += x;
    }
    while (s - k > s / 2) {
      double x = pq.poll();
      k += x / 2;
      pq.offer(x / 2);
      i++;
    }
    return i;
  }
}