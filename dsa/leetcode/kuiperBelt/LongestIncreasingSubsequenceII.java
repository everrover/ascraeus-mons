package dsa.leetcode.kuiperBelt;

// imports here

/**
 * https://leetcode.com/problems/longest-increasing-subsequence-ii/
 * 
 * Tried to solve with DP, rec(idx) = max(rec(jdx)) + 1, arr.length > jdx > idx, nums[jdx] > nums[idx] && nums[jdx] - nums[idx] <= k
 * A Segment Tree (ST) data structure is used to efficiently update the values and query the maximum length of any subsequence till the current index.
 * 
 * Against the number in segment tree, we update the value at the index with the maximum length of subsequence till that index for the current number.
 * And we query the maximum length of subsequence for [(nums[i] - k) ... (nums[i]-1)].
 * 
 * TC: O(n log n) SC: O(n) for the segment tree
 * #dynamic-programming #segment-tree #binary-indexed-tree #hard
 */
public class LongestIncreasingSubsequenceII {

  private static class ST {
    int []st;
    public ST(int n){
      int x = (int) (Math.ceil(Math.log(n) / Math.log(2))); // log2(n)
      int max_size = 2 * (int) Math.pow(2, x) - 1;
      st = new int[max_size];
    }
    private void update(int ss, int se, int val, int si, int idx){
      if(se<idx || ss>idx) return;
      else if(ss == se) st[si] = val;
      else {
        int mid = ss+(se-ss)/2;
        if(ss<=idx&&idx<=mid) update(ss, mid, val, (2*si+1), idx);
        else update(mid + 1, se, val, (2*si+2), idx);
        st[si] = Math.max(st[2*si+1], st[2*si+2]);
      }
    }
    
    private int query(int ss, int se, int qs, int qe, int idx){
      if(se<qs || ss>qe) return Integer.MIN_VALUE;
      if(ss == se || (ss >= qs && se <= qe)) return st[idx];
      else{
        int mid = ss + (se-ss)/2;
        return Math.max(query(ss, mid, qs, qe, 2*idx+1),query(mid+1, se, qs, qe, 2*idx+2));
      }
    }
    
    public void update(int val, int pos){
      update(0, 100000, val, 0, pos);
    }

    public int query(int qs, int qe){
      return query(0, 100000, qs, qe, 0);
    }
  }
  public int lengthOfLIS(int[] nums, int k) {
    if(nums.length == 1) return 1;
    ST st = new ST(100001);
    int res = 0;
    for(int i=0; i<nums.length; i++){
      int q = 1+st.query(Math.max(nums[i]-k,0), nums[i]-1);
      res = Math.max(q, res);
      st.update(q, nums[i]);
    }
    return res;
  }
}

/**
class Solution {
  private int mink;
  private int []numsarr, dp;
  public int lengthOfLIS(int[] nums, int k) {
    mink = k; numsarr = nums; 
    dp = new int[numsarr.length];
    Arrays.fill(dp, -1);
    int x = 1;
    for(int i=0; i<nums.length; i++){
      x = Math.max(x, recurse(i));
    }
    return x;
  }
  
  private int recurse(int idx) {
    if(idx >= numsarr.length) return 0;
    if(dp[idx] != -1) return dp[idx];
    int res = 1;
    for(int i=idx+1; i<numsarr.length; i++){
      if(numsarr[idx]<numsarr[i] && numsarr[i]-numsarr[idx] <= mink)
        res = Math.max(res, 1+recurse(i));
    }
    return dp[idx]=res;
  }
}
 */