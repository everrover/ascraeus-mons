package dsa.leetcode.kuiperBelt;

import java.util.*;

/**
 * https://leetcode.com/problems/sliding-window-median/
 *
 * The algorithm utilizes two TreeSets to maintain the order and balance of the sliding window elements. 
 * One TreeSet is for the smaller half of the window and the other for the larger half.
 *
 * Whenever the window slides, the elements are adjusted to keep the two halves balanced.
 * Faced problem here, used int[]{elem,index}. And also used he same comparator with getFirst() and getLast() methods.
 * Got me confused. So, simplified approach.
 *
 * If the number of elements is odd, the median is the top element of the smaller half; 
 * if even, it is the average of the top elements of both halves.
 *
 * Could've used PriorityQueue, but would've needed to track valid counts and would have to delete elements from the heap
 * using a separate set to track elements in current window and perform deletes using lazy deletion.
 * 
 * TC: O(n log k) SC: O(k)
 * #sliding-window #heap #tree-set #hard
 */

public class SlidingWindowMedian {

    boolean ise;
    int ss1, ss2;
    TreeSet<Integer> ts1, ts2;

    public double[] medianSlidingWindow(int[] nums, int k) {
      double[] res = new double[nums.length - k + 1];
      Comparator<Integer> comparator = (a, b) -> nums[a] != nums[b] ? Integer.compare(nums[a], nums[b]) : a - b;

      // TreeSet for the first half (max heap operation using reversed order)
      ts1 = new TreeSet<>(comparator.reversed());
      // TreeSet for the second half (min heap operation)
      ts2 = new TreeSet<>(comparator);

      ise = k % 2 == 0;
      ss1 = (int) Math.ceil((double) k / 2);
      ss2 = Math.floorDiv(k, 2);

      for (int i = 0; i < k; i++) ts1.add(i);
      balanceTs();

      res[0] = result(nums);
      for (int i = k; i < nums.length; i++) {
        if (!ts1.remove(i - k)) ts2.remove(i - k);
        ts2.add(i);
        ts1.add(ts2.pollFirst());
        balanceTs();
        res[i - k + 1] = result(nums);
      }
      return res;
    }

    private double result(int[] nums) {
      double res = 0.0 + nums[ts1.first()];
      if (ise) {
        res += nums[ts2.first()];
        res /= 2;
      }
      return res;
    }

    private void balanceTs() {
      while (ts1.size() > ss1) ts2.add(ts1.pollFirst());
      while (ts2.size() > ss2) ts1.add(ts2.pollFirst());
    }


  /** A good code chunk i found in the discussion section:
   * public double[] medianSlidingWindow(int[] nums, int k) {
   *     Comparator<Integer> comparator = (a, b) -> nums[a] != nums[b] ? Integer.compare(nums[a], nums[b]) : a - b;
   *     TreeSet<Integer> left = new TreeSet<>(comparator.reversed());
   *     TreeSet<Integer> right = new TreeSet<>(comparator);
   *
   *     Supplier<Double> median = (k % 2 == 0) ?
   *         () -> ((double) nums[left.first()] + nums[right.first()]) / 2 :
   *         () -> (double) nums[right.first()];
   *
   *     // balance lefts size and rights size (if not equal then right will be larger by one)
   *     Runnable balance = () -> { while (left.size() > right.size()) right.add(left.pollFirst()); };
   *
   *     double[] result = new double[nums.length - k + 1];
   *
   *     for (int i = 0; i < k; i++) left.add(i);
   *     balance.run(); result[0] = median.get();
   *
   *     for (int i = k, r = 1; i < nums.length; i++, r++) {
   *         // remove tail of window from either left or right
   *         if(!left.remove(i - k)) right.remove(i - k);
   *
   *         // add next num, this will always increase left size
   *         right.add(i); left.add(right.pollFirst());
   *
   *         // rebalance left and right, then get median from them
   *         balance.run(); result[r] = median.get();
   *     }
   *
   *     return result;
   * }
   */
}