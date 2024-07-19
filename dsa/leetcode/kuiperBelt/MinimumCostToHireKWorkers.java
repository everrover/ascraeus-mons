package dsa.leetcode.kuiperBelt;

import java.util.*;

/**
 * https://leetcode.com/problems/minimum-cost-to-hire-k-workers/
 *
 * To form a paid group with exactly k workers, this solution keeps track of workers' quality to wage ratio.
 *
 * It uses a priority queue to manage workers by their wage to quality ratio in descending order, aiming to minimize total
 * wage. Because in a given set of k workers(we track the workers with lowest wage/quality ratio in PQ), the worker with
 * the highest wage/quality ratio will set the wage for the group.
 *
 * Sorting of workers by quality ensures the proportionality rule for pay is maintained.
 *
 * I iterated over all scenarios in my head and used PQs for both earlier
 * Quality(low<->high) & Cost(high->low)[obvious, since for a given quality, we want to minimize cost]
 * : and found the one to work for the given solution. And hence picked it.
 *
 * TC: O(nlogn) SC: O(n)
 * #greedy #sorting #heap #priority-queue #hard
 */
public class MinimumCostToHireKWorkers {

  private static class T{
    public double cost; public int q;
    private T(int c, int q){
      this.cost = (double)c/(double)q;
      this.q = q; 
    }
  }
  public double mincostToHireWorkers(int[] quality, int[] wage, int k) {
    
    double minwage = (double)Long.MAX_VALUE;
    Queue<T> mq = new PriorityQueue<T>((a,b)->(a.cost<b.cost)?1:-1);
    
    T []tarr = new T[quality.length];
    for(int i=0; i<quality.length; i++){
      tarr[i] = new T(wage[i], quality[i]);
    }
    Arrays.sort(tarr, (a,b)->(a.q-b.q));
    int tq = 0, i=0;
    while(i<tarr.length) {
      T t = tarr[i++];
      mq.offer(t);
      tq += t.q;
      if(mq.size()>k) tq -= mq.poll().q;
      if(mq.size()==k) minwage = Math.min(tq * mq.peek().cost, minwage);
    }
    
    return minwage;
  }
}