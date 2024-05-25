package dsa.leetcode.kuiperBelt;

import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;

/**
 * https://leetcode.com/problems/block-placement-queries/
 * The implementation involves a custom Segment Tree (ST) for checking if a block can be placed given the current obstacles.
 * For each query of type 2, it checks if the maximum distance in the segment tree from 0 to x-sz is greater than the size of the block.
 *
 * Found using brute-force(for each query and by traversing sorted-set(of obstacles) to find max possible range), that O(m*n) TC was present and needed to be optimized. All queries have to be traversed so O(m*sth) was there with `m`. Hence sth had to be log2(n) to meet the reqd criteria. So updates and queries were to be done both in O(log2(n)), hecne ST or BIT.
 * 
 * TC: O(n log n) SC: O(n)
 * #segment-tree #treeset #hard #optimization-based-on-constraint #bin-indexed-tree
 */
public class BlockPlacementQueries {

  private static class ST{
    private int []sT;
    private int n;
    public ST(int sz){
      sT = new int[sz*2];
      n = sz;
      sT[sz] = 1000000;
      for(int i=sz-1; i>0; i--){
        sT[i] = Math.max(sT[i*2], sT[i*2+1]);
      }
    }
    public int query(int l, int r){
      int res = 0;
      l+=n; r+=n;
      while(l<=r){
        if(l%2 == 1) res = Math.max(res, sT[l++]);
        if(r%2 == 0) res = Math.max(res, sT[r--]);
        l/=2; r/=2;
      }
      return res;
    }
    public void mod(int idx, int val){
      idx += n; // modding the base layer, begins at [n ... 2*n)
      sT[idx] = val;
      idx/=2;
      while(idx>0){
        sT[idx] = Math.max(sT[idx*2], sT[idx*2+1]);
        idx /= 2;
      }
    }
  }

  public List<Boolean> getResults(int[][] queries) {
    int n = 50001;
    List<Boolean> res = new LinkedList<>();
    ST st = new ST(n);
    TreeSet<Integer> s = new TreeSet<>();
    s.add(0);n    s.add(1000000);
    for(int []q: queries){
      if(q[0] == 1){
        int x = q[1];
        int prev = s.floor(x);
        int nxt = s.ceiling(x);
        s.add(x);
        st.mod(prev, x-prev);
        st.mod(x, nxt-x);
      } else {
        int x = q[1], sz = q[2];
        int maxd = st.query(0, x-sz);
        res.add(maxd < sz);
      }
    }
    return res;
  }
}
