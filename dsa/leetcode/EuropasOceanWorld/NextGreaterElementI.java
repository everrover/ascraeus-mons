package dsa.leetcode.EuropasOceanWorld;

import java.util.*;

public class NextGreaterElementI {

  /**
   * https://leetcode.com/problems/next-greater-element-i/?envType=company&envId=agoda&favoriteSlug=agoda-all
   *
   * A stack is used to keep track of elements for which the next greater has not yet been determined. As we traverse nums2, we use a map to store the next greater element for each number. Finally, for each element in nums1, we lookup the next greater from the map or return -1 if not present.
   *
   * TC: O(n + m) SC: O(m)
   * #array #hash-table #stack #monotonic-stack #easy
   */

  public int[] nextGreaterElement(int[] nums1, int[] nums2) {
    Map<Integer, Integer> map = new HashMap<>();
    Stack<Integer> st = new Stack<>();
    int []res = new int[nums1.length];
    for(int i=0; i<nums2.length; i++){ 
      while (!st.isEmpty() && st.peek() < nums2[i])
        map.put(st.pop(), nums2[i]);
      st.push(nums2[i]);
    }
    for(int i=0; i<nums1.length; i++){
      res[i] = map.getOrDefault(nums1[i], -1);
    }
    return res;
  }
}