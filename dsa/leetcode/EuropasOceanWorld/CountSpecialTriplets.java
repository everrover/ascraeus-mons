package dsa.leetcode.EuropasOceanWorld;

class CountSpecialTriplets {
  private final static long M = (int)1e9+7;

  /**
   * https://leetcode.com/problems/count-special-triplets/description/
   * 
   * For each index `j`, calculate the number of valid `(i, j, k)` triplets
   * count `i` using `freql` which keeps how many times each number appeared before `j`,
   * count `k` using `freqr` which keeps how many times each number can appear after `j`.
   * Multiply their counts for each `j` to get possible triplets, add to result.
   * 
   * TC: O(n), SC: O(n)
   * #array #hash-table #counting #medium
   */

  public int specialTriplets(int[] nums) {
    long[] freql = new long[100001];
    long[] freqr = new long[100001];
    for(int num: nums) freqr[num]++;
    int res = 0;
    for(int num: nums){
      long m = freql[num*2], n = freqr[num*2];
      if(num <= freql.length/2) {
        if(m > 0 && n > 0) res = (int)((res + (m*n)%M) % M);
      }
      freql[num]++;
      freqr[num]--;
    }
    return res;
  }
}