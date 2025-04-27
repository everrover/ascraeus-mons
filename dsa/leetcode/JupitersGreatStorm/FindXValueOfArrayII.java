package dsa.leetcode.JupitersGreatStorm;

public class FindXValueOfArrayII {

  /**
   * https://leetcode.com/problems/find-x-value-of-array-ii/description/
   *
   * Use a segment tree to efficiently maintain and merge product prefix information for the array nums.
   * Store a frequency count of prefix product remainders for every x in the range [0, k-1] in each node.
   * For each query, update the given index and merge the corresponding segments.
   * 
   * Since the merge just uses the segments as it's children, it doesn't increase the TC and SC of our ops.
   * ❗️ Please note, that merge function can't be used for all scenarios and is supposed to be used only sporadically.
   *
   * TC: O((n + q) log n) SC: O(n log n)
   * #array #math #segment-tree #hard
   */

   private static class ST {

    private static class N{
      public int []rem;
      public int prod;
      public N(int k){
        this.rem = new int[k];
        this.prod = 0;
      }
    }
    private int k, n, x;
    private int []nums;
    private N []stx;
    public ST(int []nums, int k){
      this.n = nums.length;
      this.k = k;
      this.x = k-1;
      this.nums = new int[nums.length];
      for(int i=0; i<nums.length; i++) this.nums[i] = nums[i]%k;
      stx = new N[4*n];
      for(int i=0; i<stx.length; i++){
        stx[i] = new N(k);
      }
      construct(0, n-1, 0);
    }

    private void construct(int ss, int se, int si){
      if(ss == se){
        stx[si].prod = nums[se];
        stx[si].rem[nums[se]] = 1;
      }else{
        int mid = ss+(se-ss)/2;
        int left = 2*si+1, right = 2*si+2;
        construct(ss, mid, left);
        construct(mid+1, se, right);
        merge(si);
      }
    }

    public N query(int ss, int se, int si, int qs, int qe){
      if(qs > se || qe < ss) return new N(k);
      if(qs <= ss && se <= qe) return stx[si];
      int mid = ss+(se-ss)/2;
      int left = 2*si+1, right = 2*si+2;
      N ln = query(ss, mid, left, qs, qe);
      N rn = query(mid+1, se, right, qs, qe);
      N res = merge(ln, rn);
      return res;
    }

    public void update(int ss, int se, int si, int target, int val){
      if (ss == se) {
        for(int i=0; i<=x; i++) stx[si].rem[i] = 0;
        val %= k;
        stx[si].rem[val] = 1;
        stx[si].prod = nums[target] = val;
      }else{
        int mid = ss+(se-ss)/2;
        int left = 2*si+1, right = 2*si+2;
        if(target<=mid) update(ss, mid, left, target, val);
        else update(mid + 1, se, right, target, val);
        merge(si);
      }
    }

    private void merge(int si){
      int left = 2*si+1, right = 2*si+2;
      stx[si].prod = (stx[left].prod * stx[right].prod)%k;
      for(int i=0; i<k; i++) stx[si].rem[i] = stx[left].rem[i];
      for(int i=0; i<k; i++) {
        int nval = (i*stx[left].prod) % k;
        stx[si].rem[nval] += stx[right].rem[i];
      }
    }

    private N merge(N l, N r){
      N res = new N(k);
      res.prod = l.prod*r.prod;
      for(int i=0; i<k; i++) res.rem[i] = l.rem[i];
      for(int i=0; i<k; i++) {
        int nval = (i*res.prod) % k;
        res.rem[nval] += r.rem[i];
      }
      return res;
    }
  }
  public int[] resultArray(int[] nums, int k, int[][] queries) {
    ST st = new ST(nums, k);
    int []res = new int[queries.length];
    for(int i=0; i<queries.length; i++){
      st.update(0, nums.length-1, 0, queries[i][0], queries[i][1]);
      ST.N r = st.query(0, nums.length-1, 0, queries[i][2], nums.length-1);
      res[i] = r==null?0:r.rem[queries[i][3]];
    }
    return res;
  }
}