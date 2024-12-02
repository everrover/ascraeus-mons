package dsa.leetcode.RhoCassiopeiae;

import java.util.*;

public class LeastNumberOfUniqueIntegersAfterKRemovals {
  /**
   * https://leetcode.com/problems/least-number-of-unique-integers-after-k-removals/
   *
   * Use a map to count the frequencies of the numbers in the array.
   * An optimal strategy is to remove the numbers with the smallest count first. Hence used a PQ(min heap).
   * Could've used a TreeMap(BST) as well.
   * This ensures we are left with the minimum unique numbers. Since remaining 0nes are duplicates.
   *
   * TC: O(n log n) SC: O(n)
   * #array #hash-table #greedy #sorting #medium
   */
  
  public int findLeastNumOfUniqueInts(int[] arr, int k) {
    int res = 0;
    Arrays.sort(arr);
    Queue<Integer> pq = new PriorityQueue<>((a, b) -> (a - b));  
    int count = 1, i = 1, sz = 0;
    while (i < arr.length) {
      if (arr[i - 1] == arr[i]) {
        count++;
      } else {
        pq.offer(count);
        count = 1;
        sz++;
      }
      i++;
    }
    if (arr.length > 1 && arr[arr.length - 1] != arr[arr.length - 2]) {
      pq.offer(1);
      sz++;
    } else {
      pq.offer(count);
    }
    while (!pq.isEmpty() && k >= 0) {
      // Remove least frequent elements first
      if (pq.peek() > k) k -= pq.peek();
      else {
        k -= pq.poll();
        res++;
      }
    }
    return pq.size();
  }
}