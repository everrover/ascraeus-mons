package dsa.leetcode.kuiperBelt;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class MinimumDifferenceInSumsAfterRemovalOfElements {

  /**
   * https://leetcode.com/problems/minimum-difference-in-sums-after-removal-of-elements/
   * The approach involves using two priority queues to manage the smallest and largest `n` elements and hence to track the 
   * max and min required sums from the right and left of the array, respectively. Maxheap is used to track the smallest `n`, by 
   * removing the largest element from the heap and adding the new element. Similarly, minheap is used to track the largest `n`
   * elements.
   *  
   * Maintains running sums and differences to find the minimum difference achievable.
   * 
   * The remaining elements become a part of reqd subseq and hence focus on those is irrelevant. Earlier thought of finding subseq only,
   * but that forced to resolve two unknown variables, so flipped the thought process and hence the approach.
   * 
   * TC: O(n log n) SC: O(n)
   * #array #dynamic-programming #heap #priority-queue #hard #ignoring-unnecessary-subproblems
   */
  public long minimumDifference(int[] nums) {
    long res = Long.MAX_VALUE;
    Queue<Integer> maxpq = new PriorityQueue<>(Comparator.reverseOrder());
    Queue<Integer> minpq = new PriorityQueue<>();
    final int n = nums.length/3;
    long sum = 0;
    long[] diff = new long[n+1];
    // long[] left = new long[3*n];
    // long[] right = new long[3*n];
    for(int i=0; i<n; i++){
      maxpq.offer(nums[i]);
      sum += nums[i];
    }
    for(int i=n; i<=2*n; i++){
      diff[i-n] = sum;
      // left[i] = sum;
      if(maxpq.peek() > nums[i]){
        sum += (nums[i] - maxpq.poll());
        maxpq.offer(nums[i]);
      }
    }
    sum = 0;
    for(int i=nums.length-1; i>=2*n; i--){
      minpq.offer(nums[i]);
      sum += nums[i];
    }
    for(int i=2*n-1; i>=n-1; i--){
      diff[i-n+1] -= sum;
      // right[i] = sum;
      if(minpq.peek() < nums[i]){
        sum += nums[i] - minpq.poll();
        minpq.offer(nums[i]);
      }
    }
    for(long d: diff){
      res = Math.min(res, d);
    }
    // for(int i=n; i<2*n; i++){
    //   res = Math.min(res, left[i] - right[i-1]);
    // }
    return res;
  }
}