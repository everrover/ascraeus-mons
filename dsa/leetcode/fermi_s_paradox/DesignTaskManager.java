package dsa.leetcode.fermi_s_paradox;

import java.util.TreeSet;
import java.util.HashMap;
import java.util.Map;

public class TaskManager {
  static class Task implements Comparable<Task> {
    int u, t, p;
    Task(int userId, int taskId, int priority) {
      this.u = userId;
      this.t = taskId;
      this.p = priority;
    }

    public int compareTo(Task o) {
      if (this.p != o.p) return o.p - this.p;
      return o.t - this.t;
    }
  }

  private Map<Integer, Task> map = new HashMap<>();
  private TreeSet<Task> ts = new TreeSet<>();

  public TaskManager(int[][] tasks) {
    for (int[] task : tasks) {
      add(task[0], task[1], task[2]);
    }
  }

  public void add(int userId, int taskId, int priority) {
    Task task = new Task(userId, taskId, priority);
    map.put(taskId, task);
    ts.add(task);
  }

  public void edit(int taskId, int newPriority) {
    Task task = map.get(taskId);
    ts.remove(task);
    task.p = newPriority;
    ts.add(task);
  }

  public void rmv(int taskId) {
    Task task = map.remove(taskId);
    if (task != null) ts.remove(task);
  }

  /**
   * https://leetcode.com/problems/design-task-manager/description/
   *
   * Executes task with highest priority. If tasks have the same priority, execute the one with the highest taskId.
   * TC: O(log n) for execTop due to TreeSet operations, SC: O(n) for storing tasks
   *
   * #priority-queue #data-structure #medium
   */
  public int execTop() {
    if(ts.isEmpty()) return -1;
    Task t = ts.last();
    ts.remove(t);
    map.remove(t.t);
    return t.u;
  }
}