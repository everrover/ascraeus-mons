package dsa.leetcode.fermi_s_paradox;

import java.util.Deque;
import java.util.ArrayDeque;

public class ShortestSubarrayWithSumAtLeastK {

  /**
   * https://leetcode.com/problems/shortest-subarray-with-sum-at-least-k/
   *
   * For each index `i`  need to find the smallest sum and an largest index j such
   * that prefix_i - prefix_j >= k. We can use a monotonic queue to keep track of 
   * the indices of the prefix sums. 
   * Came up to this via sourcing monotonic increasing stack with binary-search for the same. It works 
   * in logaritmic time, though. 

   * How? When performing binary search, the indexes that were found as a part of solution for `i` were all going from bottom of the stack to the top in a linear order. Simply meaning the bottom most entries were not required for next iterations. And so came up with double ended monotonic queue as mentioned above.
   * 
   * ❗I came up with all my building insights one on top of another. Brute force O(n^3) -> Brute force with prefix sum array O(n^2) -> Priority queue O(n * logn) -> Monotonic stack and binary search -> Double ended Monotonic queue O(n)
   *
   * TC: O(n), where n is the length of the array.
   * SC: O(n), to store the prefix sums and deque.
   * #array #queue #prefix-sum #monotonic-queue #hard #binary-search #monotonic-stack #stack #heap
   */

  public int shortestSubarray(int[] nums, long k) {
    long[] prefix = new long[nums.length + 1];
    for (int i = 0; i < nums.length; i++) {
      prefix[i + 1] = prefix[i] + nums[i];
    }

    Deque<Integer> dq = new ArrayDeque<>();
    int res = Integer.MAX_VALUE;

    for (int i = 0; i <= nums.length; ++i) {
      while (!dq.isEmpty() && prefix[i] - prefix[dq.peekFirst()] >= k) {
        res = Math.min(res, i - dq.pollFirst());
      }
      while (!dq.isEmpty() && prefix[i] <= prefix[dq.peekLast()]) {
        dq.pollLast();
      }
      dq.addLast(i);
    }

    if (res == Integer.MAX_VALUE) return -1;
    return res;
  }

  /*
  private static class T{
    public int idx;
    public long s;
    public T(long s, int idx){
      this.s = s;
      this.idx = idx;
    }
  }
  public int shortestSubarray(int[] nums, long k) {
    long rsum = 0L;
    int res = Integer.MAX_VALUE;
    Queue<T> q = new PriorityQueue<>((a, b) -> Long.compare(a.s,b.s));
    for (int i=0; i<nums.length; i++) {
      rsum += nums[i];
      // check if the sum of the subarray >= k
      if(rsum >= k) res = Math.min(res,i+1);

      // basically, we try to find the largest index j such that rsum - q[j].s >= k
      // by tracking smallest running sums, we can iterate over largest arrays that support our req
      // they won't be used for next iterations, so we can remove them 
      // ❗ think in terms of positive and negative values added to commutative sum `rsum` and it's difference with `k`
      while (!q.isEmpty() && rsum-q.peek().s >= k) {
        res = Math.min(res,
                      i-q.poll().idx);
      }
      q.offer(new T(rsum, i));
    }
        
    if(res == Integer.MAX_VALUE) return -1;
    return res;
  }
   */

}