package dsa.leetcode.RhoCassiopeiae;

import java.util.*;

public class ExclusiveTimeOfFunctions {

  /**
   * https://leetcode.com/problems/exclusive-time-of-functions/
   *
   * For each log entry, handle it based on whether it is a start or end of a function.
   * Use a stack to keep track of the function call stack. Calculate the difference in time
   * between consecutive log entries, accounting for overlapping times if necessary.
   *
   * TC: O(L) where L is the number of logs, SC: O(F) where F is the number of function IDs
   * #array #stack #medium
   */

  public int[] exclusiveTime(int n, List<String> logs) {
    Stack<Integer> st = new Stack<>();
    int prevproc = -1, prevtime = -1;
    boolean isPrevStart = false;
    int[] res = new int[n];
    for (String log : logs) {
      String[] sp = log.split(":");
      int proc = Integer.valueOf(sp[0]);
      boolean isStart = sp[1].equals("start");
      int time = Integer.valueOf(sp[2]);
      
      // Calculate time difference
      int diff = time - prevtime + ((isStart == isPrevStart) ? 0 : (isStart && !isPrevStart ? -1 : 1));
      if (!st.isEmpty()) res[st.peek()] += diff;
      if (isStart) {
        st.push(proc);
      } else {
        st.pop();
      }
      
      // Prepare for next iteration
      prevproc = proc; isPrevStart = isStart; prevtime = time;
    }
    return res;
  }
}