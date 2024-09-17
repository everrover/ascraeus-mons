package dsa.leetcode.jovianMoonOrbit;

import java.util.*;

public class DailyTemperatures {
 
 /**
 * https://leetcode.com/problems/daily-temperatures/
 *
 * Use a stack to keep track of indices of the temperatures array. For a given temp, all days before it
 * with lower temperatures can be popped from the stack and the difference in days can be calculated.
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