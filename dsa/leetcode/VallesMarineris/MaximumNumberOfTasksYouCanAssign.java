package dsa.leetcode.VallesMarineris;

import java.util.*;

public class MaximumNumberOfTasksYouCanAssign {
  /**
   * https://leetcode.com/problems/maximum-number-of-tasks-you-can-assign/?envType=daily-question&envId=2025-05-01
   *
   * To solve this problem, use a greedy approach where we match the strongest workers to the most difficult tasks. We 
   * use a deque to manage which workers can take a pill and strategize which tasks they can complete. If a worker can't 
   * complete a task even with a pill, it's skipped. The process repeats until no more tasks can be assigned.
   *
   * TC: O(n log n + m log m)  SC: O(n)
   * #array #binary-search #greedy #queue #hard
   */

  public boolean canAssign(int[] tasks, int[] workers, int pills, int strength, int till) {
    int jdx = workers.length - 1, idx = till - 1;
    Deque<Integer> dq = new ArrayDeque<>();
    while (idx >= 0) {
      while (jdx >= workers.length - till && workers[jdx] + strength >= tasks[idx]) {
        dq.addFirst(workers[jdx--]);
      }
      if (dq.isEmpty()) {
        return false;
      } else if (dq.getLast() >= tasks[idx]) {
        dq.pollLast();
      } else {
        if (pills == 0) return false;
        pills--;
        dq.pollFirst();
      }
      idx--;
    }
    return true;
  }
}