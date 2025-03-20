package dsa.leetcode.VallesMarineris;

import java.util.*;

public class ClosestEqualElementQueries {

    /**
     * https://leetcode.com/problems/closest-equal-element-queries/description/
     * 
     * Use a dictionary that maps each unique value in the array to a sorted list of its indices. 
     * For each query, use binary search on the sorted indices list to find the nearest occurrences 
     * of the target value.
     * 
     * Alternatively we can also use a BST for each of the elements.
     * 
     * Optimal version uses three passes. For each element we compute 
     * - For tracking the leftmost index and the left index for a given current element.
     * - Same but for rightmost and right indexes...
     * - For computing result.
     *  - If an element isn't present on either left or right, we set -1
     *  - If an element is present is present on both right and left, we pick the min-distanced one
     *  - If an element is absent on either side and then pick the min-distanced one
     * 
     * TC: O(n) SC: O(n)
     * #array #hash-table #binary-search #medium #greedy #bst
     */

     public List<Integer> solveQueries(int[] nums, int[] queries) {
        List<Integer> res = new LinkedList<>();
        Map<Integer, Integer> map = new HashMap<>();
        Map<Integer, Integer> leftm = new HashMap<>();
        Map<Integer, Integer> rightm = new HashMap<>();
        int []left = new int[nums.length];
        int []right = new int[nums.length];
        for(int i=0; i<nums.length; i++){
          map.putIfAbsent(nums[i], -1);
          leftm.putIfAbsent(nums[i], i);
          left[i] = map.get(nums[i]);
          map.put(nums[i], i);
        }
        map.clear();
        for(int i=nums.length-1; i>=0; i--){
          map.putIfAbsent(nums[i], -1);
          rightm.putIfAbsent(nums[i], i);
          right[i] = map.get(nums[i]);
          map.put(nums[i], i);
        }
        for(int q: queries){
          int n = nums[q];
          if (left[q] == -1 && right[q] == -1) {
            res.add(-1);
          }else{
            int dl = left[q] == -1?(nums.length-rightm.get(n)+q):(q-left[q]);
            int dr = right[q] == -1?(nums.length-q+leftm.get(n)):(right[q]-q);
            res.add(Math.min(dl, dr));
          }
        }
        return res;
      }

}