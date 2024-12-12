package dsa.leetcode.RhoCassiopeiae;

import java.util.*;

public class SlidingWindowMaximum {

  /**
   * https://leetcode.com/problems/sliding-window-maximum/
   *
   * The solution employs a deque (double-ended queue) data structure to manage the current window's elements efficiently.
   * As the sliding window moves, elements get added to the deque until they become unnecessary, at which point they are removed.
   * This allows for constant time retrieval of the maximum element in the current window.
   *
   * TC: O(n) SC: O(k)
   * #array #deque #sliding-window #hard #queue #binary-search-tree
   */

  public int[] maxSlidingWindow(int[] nums, int k) {
    int[] ans = new int[nums.length - k + 1];
    Deque<Integer> q = new LinkedList<>();
    for (int i = 0; i < nums.length; i++) {
      while (q.size() > 0 && q.getLast() < nums[i]) q.removeLast();
      q.add(nums[i]);
      if (i - k + 1 < 0) continue; // init k elements
      ans[i - k + 1] = q.getFirst();
      if (nums[i - k + 1] == q.getFirst()) q.removeFirst();
    }
    return ans;
  }
}

// To mitigate loss of max heap we use treemap or BST - O(nlgk)
// class Solution{
//     public int[] maxSlidingWindow(int[] nums, int k) {
//         if (nums.length == 0) {
//             return new int[0];
//         }
//         int[] res = new int[nums.length - k + 1];
//         TreeMap<Integer, Integer> window = new TreeMap<>();

//         for (int i = 0; i < nums.length; i++) {
//             if (i - k + 1 > 0) {
//                 // remove leftmost indexed in window, i - k
//                 window.put(nums[i - k], window.get(nums[i - k]) - 1);
//                 if (window.get(nums[i - k]) == 0) {
//                     window.remove(nums[i - k]);
//                 }
//             }
//             window.put(nums[i], window.getOrDefault(nums[i], 0) + 1);
//             if (i - k + 1 >= 0) {
//                 res[i - k + 1] = window.lastKey(); 
//             }
//         }

//         return res;
//     }
// }

// max heap - removal takes k time so TC: O(nk-k^2) = O(nk)
// class Solution {
//     public int[] maxSlidingWindow(int[] nums, int k) {
//         PriorityQueue<Integer> pq = new PriorityQueue<>(k, Collections.reverseOrder());
        
//         for(int i=0; i<k; i++){
//             pq.offer(nums[i]);
//         }
//         int ans[] = new int[nums.length-k+1];
//         int j=0;
//         for(int i=k; i<nums.length; i++){
//             ans[j++] = pq.peek();
//             pq.remove(nums[i-k]);
//             pq.offer(nums[i]);
//         }
//         ans[j++] = pq.peek();
//         return ans;
//     }
// }

// crude brute force - O(nk)
// class Solution {
//     public int[] maxSlidingWindow(int[] nums, int k) {
//         int max = findMax(0, k, nums);;
//         int[] ans = new int[nums.length - k + 1];
//         ans[0] = max;
//         for(int i = 1; i < ans.length; i++) {
//             if (nums[i - 1] == max && nums[i + k - 1] < max) {
//                 max  = findMax(i, i + k, nums);
//             }
//             max = Math.max(max, nums[i + k - 1]);
//             ans[i] = max;
//         }
//         return ans;
//     }

//     public int findMax(int i, int k, int[] nums) {
//         int max = Integer.MIN_VALUE;
//         while(i < k){
//             max = Math.max(max, nums[i]);
//             i++;
//         }
//         return max;
//     }
// }