package dsa.leetcode.fermi_s_paradox;

import java.util.*;

public class MaximumNumberOfEatenApples {
  /**
   * https://leetcode.com/problems/maximum-number-of-eaten-apples/
   *
   * Greedy choice : track the apples by their expiration `day` and `count` using a priority queue
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