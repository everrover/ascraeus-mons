package dsa.leetcode.JovianMoonOrbit;

import java.util.*;

public class NextGreaterElementI {
	/**
	 * https://leetcode.com/problems/next-greater-element-i/
	 *
	 * ~ logic as in DailyTemperatures.java
	 * Since in worst case, we can have largest element for i in nums1 only to right of i in nums2,
	 * we can use a hashmap to store the next greater element for each element in nums2. It's done by default,
	 * when we maintain a monotonic stack(inc) of elements in nums2. And small->greater relationship.
	 *
	 * TC: O(nums1.length + nums2.length) SC: O(nums2.length)
	 * #array #hash-table #stack #monotonic-stack #medium
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