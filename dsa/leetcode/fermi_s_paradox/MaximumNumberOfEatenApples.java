package dsa.leetcode.fermi_s_paradox;

import java.util.*;

public class MaximumNumberOfEatenApples {
  /**
   * https://leetcode.com/problems/maximum-number-of-eaten-apples/
   *
   * To maximize the number of apples eaten, track the apples by their expiration dates using a priority queue.
   * At each day, prioritize eating apples that will rot sooner and maintain the count of uneaten, non-rotten apples.
   * Continue eating apples even beyond the initial days if any remain uneaten but not yet rotten.
   *
   * TC: O(n log n) SC: O(n)
   * #array #greedy #heap #priority-queue #medium
   */

  private static class T {
    public int c, e;
    public T(int c, int e) {
      this.c = c;
      this.e = e;
    }
    @Override
    public String toString() {
      return "{" + this.c + ":" + this.e + "}";
    }
  }

  public int eatenApples(int[] apples, int[] days) {
    Queue<T> pq = new PriorityQueue<>((a, b) -> a.e - b.e);
    int day = 0, res = 0;
    for (int i = 0; i < apples.length; i++) {
      if (apples[i] != 0) {
        pq.offer(new T(apples[i], day + days[i]));
      }
      while (!pq.isEmpty() && (pq.peek().c == 0 || pq.peek().e <= day)) {
        // remove rotten/empty stuff
        pq.poll();
      }
      if (!pq.isEmpty()) {
        pq.peek().c--;
        res++;
      }
      day++;
    }
    while (!pq.isEmpty()) {
      while (!pq.isEmpty() && (pq.peek().c == 0 || pq.peek().e <= day)) {
        // remove rotten/empty stuff
        pq.poll();
      }
      if (!pq.isEmpty()) {
        pq.peek().c--;
        res++;
      }
      day++;
    }
    return res;
  }
}