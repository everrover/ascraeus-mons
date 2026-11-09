package dsa.leetcode.JupitersGreatStorm;

import java.util.*;

/**
 * https://leetcode.com/problems/minimum-operations-to-sort-with-locked-indices/
 *
 * Unlocks are forced, not chosen, and the remaining work is just counting adjacent swaps.
 * - A locked index i can stay locked only if nothing ever has to cross it. That holds exactly 
 * when nums[i] == i+1 and the prefix max over [0..i] is i+1, i.e. nums[0..i] is already a 
 * permutation of [1..i+1]. Then the prefix and suffix sort independently and i never moves. 
 * Otherwise some value has to pass through i, or nums[i] itself has to move, so i must be 
 * unlocked. Each forced unlock costs 1 op, and if there are more than k of them the answer is -1.
 * - After the forced unlocks, the locked indices that remain split the array into independent 
 * segments. Each segment holds exactly the values that belong to it, so sorting it with adjacent 
 * swaps takes exactly its inversion count (each swap fixes one inversion). Unlocking any more 
 * indices would never reduce the swap count.
 * - Inversions per segment are counted with merge sort: when rarr[j] < larr[i] during a merge, 
 * every element left in larr forms an inversion with rarr[j].
 * - Answer = forced unlocks + the sum of segment inversions.
 * - If not for the actual numbers between 1..n, the problem would be straightforward still. I'd first
 * map the numbers to their ranks first.
 *
 * TC: O(n log n) SC: O(n)
 * #array #greedy #divide-and-conquer #merge-sort #medium
 */

class MinimumOperationsToSortWithLockedIndices {
  public int mergesortcnt(int[] arr, int left, int right) {
    if (left < right) {
      int mid = left + (right - left) / 2;
      int ll = mergesortcnt(arr, left, mid);
      int rl = mergesortcnt(arr, mid + 1, right);
      int ml = mergecnt(arr, left, mid, right);
      return ll+rl+ml;
    }
    return 0;
  }

    private int mergecnt(int[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] larr = new int[n1];
        int[] rarr = new int[n2];

        for (int i = 0; i < n1; ++i) larr[i] = arr[left + i];
        for (int j = 0; j < n2; ++j) rarr[j] = arr[mid + 1 + j];

        int i = 0, j = 0, k = left, inv = 0;
        while (i < n1 && j < n2) {
            if (larr[i] <= rarr[j]) arr[k++] = larr[i++];
            else {arr[k++] = rarr[j++]; inv+=larr.length-i;}
        }

        while (i < n1) arr[k++] = larr[i++];
        while (j < n2) arr[k++] = rarr[j++];
        return inv;
    }
    private int cntinv(int []nums, int L, int R){
        return mergesortcnt(nums, L, R);
    }
    public int minOperations(int[] nums, int[] locked, int k) {
        int res = 0, unlocked = 0, mx=0;
        for(int i=0; i<nums.length; i++){
          mx = Math.max(mx, nums[i]);
          if(locked[i] == 1 && (mx != i+1 || nums[i] != i+1)) {
              unlocked++;
              locked[i] = 0;
            }
        }
        if(unlocked>k) return -1;
        for(int i=0, l=0; i<=nums.length; i++){
          if(i == nums.length || locked[i] == 1){
            res += cntinv(nums, l, i-1);
            l = i+1;
          }
        }
        res+=unlocked;
        return res;
    }
}
