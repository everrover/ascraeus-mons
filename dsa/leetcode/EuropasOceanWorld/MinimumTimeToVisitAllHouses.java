package dsa.leetcode.EuropasOceanWorld;

class Solution {

  /**
   * https://leetcode.com/problems/minimum-time-to-visit-all-houses/description/
   *
   * Use prefix sums to compute distances for both forward and backward movements.
   * For each move between adjacent houses, take the minimum distance from the two prefix-sum arrays.
   * 
   * TC: O(n + q) SC: O(n)
   * #array #prefix-sum #medium
   */

  public long minTotalTime(int[] forward, int[] backward, int[] queries) {
    int n = forward.length;
    long pre[] = new long[n+1];
    long suf[] = new long[n+1];
    for(int i=0; i<n; i++){
      pre[i+1] = pre[i] + forward[i];
    }
    for(int i=n-1; i>=0; i--){
      suf[i] = suf[i+1] + backward[i];
    }
    long res = 0;
    int curr = 0;
    for(int q: queries){
      long diff = 0;
      // forward
      if(curr > q) diff = pre[n] - pre[curr] + pre[q];
      else diff = pre[q] - pre[curr];
      // backward
      if(curr < q) diff = Math.min(diff, suf[0] - suf[curr+1] + suf[q+1]);
      else diff = Math.min(diff, suf[q+1] - suf[curr+1]);
      res += diff;
      curr = q;
    }
    return res;
  }
}