package dsa.leetcode.jovianMoonOrbit;

import java.util.*;

public class DailyTemperatures {
 
 /**
 * https://leetcode.com/problems/daily-temperatures/
 *
 * Given an array of integers `temperatures`, return an array `answer` such that `answer[i]`
 * is the number of days you have to wait after the ith day to get a warmer temperature.
 * Use a stack to keep track of indices of the temperatures array.
 * 
 * TC: O(n) SC: O(n)
 * #array #stack #monotonic-stack #medium
 */
 
 public int[] dailyTemperatures(int[] temperatures) {
     int[] res = new int[temperatures.length];
     Stack<Integer> st = new Stack<>();
     for (int i = 0; i < temperatures.length; i++) {
         while (!st.isEmpty() && temperatures[st.peek()] < temperatures[i]) {
             res[st.peek()] = i - st.peek();
             st.pop();
         }
         st.push(i);
     }
     return res;
 }
}

// Example usage:
// int[] temps = {73, 74, 75, 71, 69, 72, 76, 73};
// int[] result = new DailyTemperatures().dailyTemperatures(temps);