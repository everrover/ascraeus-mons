package dsa.leetcode.VallesMarineris;

import java.util.*;

public class MaximumNumberOfTasksYouCanAssign {
  /**
   * https://leetcode.com/problems/maximum-number-of-tasks-you-can-assign/?envType=daily-question&envId=2025-05-01
   *
   * Greedy choice: Match the strongest workers to the most easiest tasks.
   * 
   * Earlier I used a BST to track the `k` best workers and for each task, 
   * I would check if the worker can complete the task just barely, i.e. task[i] is mapped to map.ceiling(task[i]).
   * OR ... map.ceiling(task[i] - strength) and if it exists, I would use a pill.
   * 
   * But, I found if a biggest worker can finish hardest task, i.e. `lastKey`, then he can also finish the easiest task. 
   * So it's the best candidate for the hardest task. If it can't it'd need the pill. And hence the `ceil` func
   * 
   * If any one of the best `k` workers can't finish the task, then for a given `k` this is impossible.
   * 
   * This process is better simulated using a monotonic(which we maintain using sequential inserts) deque.
   * For a given task, we insert(in our deque) all the workers that can finish the task, by either using a pill or not.
   * If largest worker can finish the task, we remove the largest worker from the deque. Else, 
   * we remove the smallest and decrement the pill count. Since all workers in the deque are ones which
   * can finish the task with or without the pill...
   *
   * // last => strongest, first => weakest
   * 
   * TC: O(n log n + m log m)  SC: O(n)
   * #array #bst #greedy #queue #hard #binary-search-over-solution-space
   */

   public int maxTaskAssign(int[] tasks, int[] workers, int pills, int strength) {
    int res = 0;
    int l = 1, r = Math.min(tasks.length, workers.length);
    Arrays.sort(tasks);
    Arrays.sort(workers);
    while(l<=r){
      int mid = (l+r)/2;
      if(possible(pills, strength, mid, tasks, workers)){
        res = Math.max(res, mid);
        l = mid+1;
      }else r = mid-1;
    }
    return res;
  }

  private boolean possible(int pills, int strength, final int till, final int[] tasks, final int[] workers){
    int jdx = workers.length-1, idx = till-1;
    Deque<Integer> dq = new ArrayDeque<>();
    while(idx>=0){
      while(jdx >= workers.length - till && workers[jdx] + strength >= tasks[idx]){
        dq.addFirst(workers[jdx--]);
      }
      if(dq.isEmpty()) {
        return false;
      }else if(dq.getLast() >= tasks[idx]){
        dq.pollLast();
      }else{
        if(pills == 0) return false;
        pills--;
        dq.pollFirst();
      }
      idx--;
    }
    return true;
  }

  private boolean possibleBST(int pills, int strength, final int till, final int[] tasks, final int[] workers){
    int idx = till-1;
    TreeMap<Integer, Integer> tm = new TreeMap<>();
    for(int i=workers.length-till; i<workers.length; i++) tm.put(workers[i], tm.getOrDefault(workers[i], 0)+1);
    while(idx>=0){
      Integer k = tm.lastKey();
      if(k >= tasks[idx]) {
        tm.put(k, tm.get(k)-1);
        if(tm.get(k) == 0) tm.remove(k);
      }else{
        if(pills == 0) return false;
        k = tm.ceilingKey(tasks[idx]-strength);
        if(k == null) return false;
        pills--;
        tm.put(k, tm.get(k)-1);
        if(tm.get(k) == 0) tm.remove(k);
      }
      idx--;
    }
    return true;
  }
}