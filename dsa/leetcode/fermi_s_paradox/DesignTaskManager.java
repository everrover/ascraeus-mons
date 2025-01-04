package dsa.leetcode.fermi_s_paradox;

import java.util.TreeSet;
import java.util.HashMap;
import java.util.Map;

public class DesignTaskManager {
  /**
   * https://leetcode.com/problems/design-task-manager/description/
   *
   * Executes task with highest priority. If tasks have the same priority, execute the one with the highest taskId.
   * 
   * TreeSet(Binary Search Tree) is used to store tasks in descending order of priority and taskId.
   * HashMap is used to store tasks for quick access w.r.t. taskId.
   *
   * TC: O(log n) for execTop due to TreeSet operations, SC: O(n) for storing tasks
   * #priority-queue #medium #binary-search-tree #design
   */
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

  public DesignTaskManager(int[][] tasks) {
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

  public int execTop() {
    if(ts.isEmpty()) return -1;
    Task t = ts.last();
    ts.remove(t);
    map.remove(t.t);
    return t.u;
  }

  /** // Using PriorityQueue and HashMap
   * private static class T implements Comparable<T>{
    public int u, t, p;
    public boolean isV;
    public T(int u, int t, int p){
      this.u = u;
      this.t = t;    
      this.p = p;
      this.isV = true;
    }
    public int compareTo(T a){
      return a.p == this.p?
        (
          a.t==this.t?
          (a.u-this.u):
          (a.t-this.t)
        )
        :(a.p-this.p);
    }
  }
  private Queue<T> q = new PriorityQueue<>();
  private Map<Integer, T> map = new HashMap<>();
  public TaskManager(List<List<Integer>> tasks) {
    for(List<Integer> task: tasks){
      this.add(task.get(0),task.get(1),task.get(2));
    }
  }
  
  public void add(int userId, int taskId, int priority) {
    T t = new T(userId, taskId, priority);
    map.put(taskId, t);
    q.offer(t);
  }
  
  public void edit(int taskId, int newPriority) {
    T t = map.get(taskId);
    t.isV = false;
    t = new T(t.u, t.t, newPriority);
    q.offer(t);
    map.put(taskId, t);
  }
  
  public void rmv(int taskId) {
    T t = map.get(taskId);
    t.isV = false;
    map.remove(taskId);
  }
  
  public int execTop() {
    while(!q.isEmpty() && !q.peek().isV) q.poll();
    if(q.isEmpty()) return -1;
    T t = q.poll();
    map.remove(t.t);
    return t.u;
  }
   */
}