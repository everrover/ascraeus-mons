package dsa.leetcode.VallesMarineris;

import java.util.*;

public class ImplementRouter {

    /**
     * https://leetcode.com/problems/implement-router/description/
     *
     * The Router class manages network packets by using a combination of hash maps and lists.
     * It ensures that the FIFO (First In First Out) principle is maintained, and effectively handles duplicates 
     * and retrieval counts using auxiliary methods and efficient data structures.
     *
     * TC: O(1) for add and forward, O(log n) for getCount
     * SC: O(n)
     * #array #hash-table #binary-search #design #queue #medium
     */
    private final static long M = (long)1e6;
  
    private Map<Long, Set<Integer>> pacheck = new HashMap<>();
    private Map<Integer, List<Integer>> pamap = new HashMap<>();
    private Map<Integer, Integer> old = new HashMap<>();
    private Queue<int[]> paque = new LinkedList<>();
  
    private int ml;
    public ImplementRouter(int memoryLimit) {
      ml = memoryLimit;
    }
  
    private int searchLess(int dest, int ts){
      if(!pamap.containsKey(dest)) return -1;
  
      List<Integer> tss = pamap.get(dest);
  
      int l = old.get(dest), r = tss.size()-1, mid = -1;
      int res = -1;
      while(l<=r){
        mid = (l+r)/2;
        if(tss.get(mid)>ts) r=mid-1;
        else {
          l=mid+1;
          res = mid;
        }
      }
  
      return res;
    }
  
    private int searchGrt(int dest, int ts){
      if(!pamap.containsKey(dest)) return -1;
  
      List<Integer> tss = pamap.get(dest);
  
      int l = old.get(dest), r = tss.size()-1, mid = -1;
      int res = -1;
      while(l<=r){
        mid = (l+r)/2;
        if(tss.get(mid)>=ts) {
          r=mid-1;
          res = mid;
        } else {
          l=mid+1;
        }
      }
  
      return res;
    }
    
    public boolean addPacket(int src, int dest, int ts) {
      long key = src*M+dest;
      if(pacheck.containsKey(key) && pacheck.get(key).contains(ts)) return false;
      if(paque.size() == ml) forwardPacket();
      
      pacheck.putIfAbsent(key, new HashSet<>());
      pacheck.get(key).add(ts);
  
      pamap.putIfAbsent(dest, new ArrayList<>());
      pamap.get(dest).add(ts);
  
      old.putIfAbsent(dest, 0);
      paque.offer(new int[]{src, dest, ts});
      return true;
    }
    
    public int[] forwardPacket() {
      if(paque.isEmpty()) return new int[]{};
      int[] p = paque.poll();
      long key = p[0]*M+p[1];
      if(pacheck.containsKey(key)){
        if(!pacheck.get(key).contains(p[2])) return new int[]{};
        pacheck.get(key).remove(p[2]);
      }
  
      old.put(p[1], old.get(p[1])+1);
      return p;
    }
    
    public int getCount(int dest, int st, int en) {
      int sidx = searchGrt(dest, st);
      int eidx = searchLess(dest, en);
      if(sidx>eidx) return 0;
      else if(sidx == -1 || eidx == -1) return 0;
      else return eidx-sidx+1;
    }
}