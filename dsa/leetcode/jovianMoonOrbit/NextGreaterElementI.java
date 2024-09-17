package dsa.leetcode.jovianMoonOrbit;

import java.util.*;

public class NextGreaterElementI {
	/**
	 * https://leetcode.com/problems/next-greater-element-i/
	 *
	 * Use a stack to keep track of elements and a hashmap to store the next greater element for each.
	 * Traverse nums2, using the stack to find and store the next greater element for each number.
	 * Then, for each element in nums1, get the next greater element from the hashmap.
	 *
	 * TC: O(nums1.length + nums2.length) SC: O(nums2.length)
	 * #array #hash-table #stack #monotonic-stack #easy
	 */
	
	public int[] nextGreaterElement(int[] nums1, int[] nums2) {
		Map<Integer, Integer> map = new HashMap<>();
		Stack<Integer> st = new Stack<>();
		int[] res = new int[nums1.length];

		for(int i = 0; i < nums2.length; i++){
			while(!st.isEmpty() && st.peek() < nums2[i])
				map.put(st.pop(), nums2[i]);
			st.push(nums2[i]);
		}

		for(int i = 0; i < nums1.length; i++){
			res[i] = map.getOrDefault(nums1[i], -1);
		}
		return res;
	}
}