package dsa.leetcode.RhoCassiopeiae;

import java.util.List;
import java.util.Stack;

public class ExclusiveTimeOfFunctions {

  /**
   * https://leetcode.com/problems/exclusive-time-of-functions/
   *
   * Use a stack to track function calls and calculate exclusive times.
   * On 'start', push the current function with the starting time onto the stack.
   * On 'end', pop from the stack to find the function's total time.
   * 
   * It's equivalent to a one I did before.... so no biggie!
   *
   * TC: O(m), SC: O(n)
   * #array #stack #easy
   */

  private static final String S = "start";

  public int[] exclusiveTime(int n, List<String> logs) {
    int[] res = new int[n];
    int prevtime = 0;
    boolean prevstate = true;
    Stack<int[]> st = new Stack<>();
    for (String log : logs) {
      int[] slogs = new int[2];
      String[] cs = log.split(":");
      slogs[0] = Integer.valueOf(cs[0]);
      boolean state = cs[1].equals(S);
      slogs[1] = Integer.valueOf(cs[2]);
      // If the function starts and the stack is not empty,
      // add time to the top function
      if (state) {
        if (!st.isEmpty()) res[st.peek()[0]] += (slogs[1] - prevtime - (prevstate ? 0 : 1));
        st.push(slogs);
      } 
      else {
        // If the function ends, calculate its total time
        res[st.peek()[0]] += (slogs[1] - prevtime + (prevstate ? 1 : 0));
        st.pop();
      }
      prevtime = slogs[1];
      prevstate = state;
    }
    return res;
  }
}