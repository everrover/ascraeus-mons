package dsa.leetcode.kuiperBelt;

import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode.com/problems/peaks-in-array/
 * For peaks we store 1 and for non-peaks we store 0 in the segment tree.
 * So, `sum = number of peaks in the range`. We have to exclude the first and last elements from the range to account for the extremities.
 * Utilizes a Segment Tree (ST) data structure for efficient range queries and updates.
 *
 * TC: Query - O(logn), Update - O(logn) Construction - O(n*log(n))
 * SC: O(n) for segment tree storage
 * 
 * Can easily be solved using a Binary Indexed Tree (BIT) as well.
 *
 * #segment-tree #array #binary-indexed-tree #hard
 */
public class PeaksInArray {

  private static class ST {
    private int []st;
    private int n;
    public ST(int []arr){
      this.n = arr.length;
      this.st = new int[4*n];
      for(int i=1; i<arr.length-1; i++){
        update(i, arr);
      }
      // construct(arr, 0, n-1, 0);
    }
    // private void construct(int []arr, int ss, int se, int si){
    //   if(ss == se){
    //     st[si] = arr[ss];
    //   }else{
    //     int mid = ss+(se-ss)/2;
    //     construct(arr, ss, mid, 2*si+1);
    //     construct(arr, mid+1, se, 2*si+2);
    //     st[si] = st[si*2+1]+st[si*2+2];
    //   }
    // }
    
    private int query(int ss, int se, int si, int qs, int qe){
      if(qs>qe) return 0;
      else if (ss == qs && se == qe) return st[si];
      else{
        int mid = ss+(se-ss)/2;
        return query(ss, mid, 2*si+1, qs, Math.min(qe, mid)) + query(mid + 1, se, 2*si+2, Math.max(qs, mid + 1), qe);
      }
    }
    
    private void update(int ss, int se, int si, int idx, int val){
      if(ss>idx || se<idx) return;
      else if (ss == se) {
        st[si] = val; return;
      }else{
        int mid = ss+(se-ss)/2;
        if(ss<=idx && idx<=mid) update(ss, mid, 2*si+1, idx, val);
        else update(mid + 1, se, 2*si+2, idx, val);
        st[si] = st[(si*2+1)] + st[(si*2+2)];
      }
    }
    
    public void update(int idx, int val){
      update(0, n-1, 0, idx, val);
    }
    
    public int query(int qs, int qe){
      return query(0, n-1, 0, qs+1, qe-1);
    }
    
    public String toString(){
      String res = "";
      for(int i=0; i<st.length; i++){
        res += st[i]+",";
      }
      return res;
    }
    
    
    public void update(int idx, int []arr){
      if(idx > 0 && idx < arr.length-1){
        if(arr[idx]>arr[idx-1] && arr[idx]>arr[idx+1]) update(idx, 1);
        else update(idx, 0);
      }
    }
  }
  public List<Integer> countOfPeaks(int[] nums, int[][] queries) {
    ST st = new ST(nums);
    List<Integer> res = new LinkedList<>();
    for(int []query: queries){
      if(query[0] == 1){
        res.add(st.query(query[1], query[2]));
      }else if(query[0] == 2){
        int idx = query[1];
        nums[idx] = query[2];
        st.update(idx, nums);
        st.update(idx-1, nums);
        st.update(idx+1, nums);
      }
    }
    return res;
  }
}