package dsa.leetcode.Ipestus;

import java.util.*;

public class DivideAnArrayIntoSubarraysWithMinimumCostIi {

    /**
     * https://leetcode.com/problems/divide-an-array-into-subarrays-with-minimum-cost-ii/description/?envType=daily-question&envId=2026-02-07
     * 
     * For any given range we have three parts: [0...i-1], [i...k-1], [k...n-1].
     * And we work for the k'th element. For the middle part, we need to select k-2 smallest sub-arrays and
     * that's better represented with k-2 smallest elements in range.
     * 
     * Use two heaps/BSTs to manage the selection of k - 2 smallest elements within a given valid range.
     * One secures the top k - 2 smallest elements, while the other secures the largest elements.
     *
     * TC: O(n log k) SC: O(k)
     * #array #sliding-window #heap #tree-set #hard
     */

    private static class T {
    TreeMap<Integer, Integer> st1 = new TreeMap<>();
    TreeMap<Integer, Integer> st2 = new TreeMap<>();
    private int K = 0, st1sz = 0, st2sz = 0;
    public long sum = 0;
    public T(int K){
      this.K = K;
    }
    private void addE(TreeMap<Integer, Integer> st, int x){
      st.put(x, st.getOrDefault(x, 0)+1);
      if(st == st1) st1sz++; else st2sz++;
    }
    private void removeE(TreeMap<Integer, Integer> st, int x){
      st.put(x, st.getOrDefault(x, 0)-1);
      if(st.get(x) == 0) st.remove(x);
      if(st == st1) st1sz--; else st2sz--;
    }
    private void fixup() {
      while(st1sz <= K && !st2.isEmpty()){
        int x = st2.firstKey();
        addE(st1, x);
        sum += x;
        removeE(st2, x);
      }
      while(st1sz > K){
        int x = st1.lastKey();
        addE(st2, x);
        sum -= x;
        removeE(st1, x);
      }
    }
    private void add(int x){
      if(!st2.isEmpty() && x>=st2.firstKey()) {
        addE(st2, x);
      }else{
        addE(st1, x);
        sum += x;
      }
      fixup();
    }
    private void remove(int x){
      if(st1.containsKey(x)) {
        removeE(st1, x);
        sum -= x;
      }else if (st2.containsKey(x)) {
        removeE(st2, x);
      }
      fixup();
    }
  }
  public long minimumCost(int[] nums, int k, int dist) {
    int N = nums.length;
    T t = new T(k-2);
    for(int i=1; i<k-1; i++)
      t.add(nums[i]);
    long res = t.sum+nums[k-1];
    for(int i=k; i<N; i++){
      int j = i-dist-1;
      if(j > 0) t.remove(nums[j]);
      t.add(nums[i-1]);
      res = Math.min(res, t.sum + nums[i]);
    }
    return res+nums[0]; 
  }
}