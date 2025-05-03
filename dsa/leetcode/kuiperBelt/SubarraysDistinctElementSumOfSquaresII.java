package dsa.leetcode.KuiperBelt;

/**
 * https://leetcode.com/problems/subarrays-distinct-element-sum-of-squares-ii/
 * Approach: i needed help at this. made me remember iit days when i used this (x+1) trick
 * 
 * b = (a+1)^2 = a^2 + 2*a + 1
 * so if we pick a seq at index i,
 * then sum of squares(for 1,2,3,4) = (0+1)^2 + (1+1)^2 + (2+1)^2 + (3+1)^2
 *                                  = (0^2 + 2*0*1 + 1^2) + (1^2 + 2*1*1 + 1^2) + (2^2 + 2*2*1 + 1^2) + (3^2 + 2*3*1 + 1^2)
 *                                  = (0^2 + 1^2 + 2^2 + 3^2) + 2*(0+1+2+3) + 4*1
 * 
 * therefore for seq of length n+1, sum of squares = sum of squares for n + 2*sum + n+1
 * f(x+1) = f(x) + 2*sum + x+1
 * 
 * the above is true for distinct elements, but if we have duplicates???
 * unique elements if 2 is added...
 * => 1 2 3 4 5-> 1 2 3 4 5 2 => 0:(1) 1:(2 1) 2:(3 2 1) 3:(4 3 2 1) 4:(5 4 3 2 1) 5:(5 4 4 3 2 1)
 * sum of squares = (4^2 + 3^2 + 2^2 + 1^2) + 2*(4+3+2+1) + 5 - l
 *                = prev_sum_of_squares + 2*prev_sum + n + 1 - l[since we have to ignore the duplicate subarrays ... isn't made clear in the question]
 * 
 * This solution leverages Segment Tree to efficiently calculate the **sum of distinct counts**
 * It uses a lazy propagation technique to update ranges in the segment tree for [l...curr] 
 * where l is the last occurance of the element. Since for elements before l count is same as
 * the previous occurance, we can just add the count to the previous occurance.
 * 
 * p.s. could've used BIT here.
 * 
 * TC: O(nlog(n)) SC: O(n)
 * #array #dynamic-programming #binary-indexed-tree #segment-tree #hard #lazy-propagation #math-optimization #tricky
 */
public class SubarraysDistinctElementSumOfSquaresII {

  private final static long MOD = (long)1e9+7;
  private long []st, lazy;
  public int sumCounts(int[] nums) {
    long sumSq = 0L;
    st = new long[4*nums.length];
    lazy = new long[4*nums.length];
    long res = 0;
    int []map = new int[100001];
    for(int i=0; i<nums.length; i++){
      int l = map[nums[i]];
      long sum = query(0, nums.length-1, l, i, 0);
      update(0, nums.length-1, l, i, 0, 1);
      sumSq = (sumSq+2*sum+(i-l)+1)%MOD;
      res = (res+sumSq)%MOD;
      map[nums[i]] = i+1;
    }
    return (int)res;
  }
  
  private long query(int ss, int se, int qs, int qe, int idx){
    if(qs>qe) return 0;
    else if(ss == qs && se == qe) {
      return (st[idx]+(qe-qs+1)*lazy[idx])%MOD;
    } else {
      int mid = ss+(se-ss)/2;
      return (query(ss, mid, qs, Math.min(mid, qe), 2*idx+1) + query(mid+1, se, Math.max(qs, mid+1), qe, 2*idx+2)+(qe-qs+1)*lazy[idx])%MOD;
    }
  }
  
  private void update(int ss, int se, int qs, int qe, int idx, int valDiff){
    if(ss == qs && qe == se){
      lazy[idx] += valDiff;
    } else if(qs <= qe) {
      int left = idx*2+1;
      int right = idx*2+2;
      lazy[left] += lazy[idx];
      lazy[right] += lazy[idx];
      int mid = ss+(se-ss)/2;
      update(ss, mid, qs, Math.min(mid, qe), left, valDiff);
      update(mid+1, se, Math.max(mid+1, qs), qe, right, valDiff);
      st[idx] = (query(ss, mid, ss, mid, left)+query(mid+1, se, mid+1, se, right))%MOD;
      lazy[idx] = 0;
    }
  }
}