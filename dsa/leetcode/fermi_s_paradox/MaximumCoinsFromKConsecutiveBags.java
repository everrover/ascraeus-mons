package dsa.leetcode.fermi_s_paradox;

import java.util.Arrays;

public class MaximumCoinsFromKConsecutiveBags {
  /**
   * https://leetcode.com/problems/maximum-coins-from-k-consecutive-bags/description/
   *
   * To obtain the maximum coins from k consecutive bags, we can either start at the beginning of a 
   * bag or at the end of a bag.
   * 
   * START: For any bag, l, we find the score by iterating till r such that,
   * X[r][1] - X[l][0] + 1 <= k. We reach at `r` and we need to pick max number of elements from it
   * such that the total number of elements picked is <= k. 
   * 
   * X<X[l][0] ... X[l][1]> ... <X[r-1][0] ... X[r-1][1]> PK1 <X[r][0] ..PK2.. X[r][1]>
   * In such a case we have `k` elements ending at either PK1 or PK2. 
   * 
   * PK = max(X[r][0] + k - 1 - X[r][0] + 1, 0), for PK1 < 0
   * 
   * PK number of bags are added to the score.
   * 
   * ~ case for END.
   *
   * TC: O(n) SC: O(1)
   * #sliding-window #prefix-sum #medium #tricky
   */

  public long maximumCoins(int[][] coins, int k) {
    Arrays.sort(coins, (a,b)->(a[0]-b[0]));
    int l=0, r=0;
    long res = 0, currscore = 0, coinscurr = 0;
    for(l=0,r=0; l<coins.length; l++){
      while(r<coins.length && coins[r][1]-coins[l][0]+1 <= k){
        currscore += 1L*coins[r][2]*(coins[r][1]-coins[r][0]+1);
        r++;
      }
      if(r<coins.length){
        int tillk = coins[l][0]+k-1-coins[r][0]+1;
        long part = 1L*Math.max(0, tillk)*coins[r][2];
        res = Math.max(res, currscore+part);
      }
      currscore -= 1L*coins[l][2]*(coins[l][1] - coins[l][0] + 1);      
    }
    currscore = 0;
    for(l=r=0; r<coins.length; r++){
      currscore += 1L*coins[r][2]*(coins[r][1]-coins[r][0]+1);
      while(l<=r && coins[r][1]-coins[l][1]+1 > k){
        currscore -= 1L*coins[l][2]*(coins[l][1]-coins[l][0]+1);
        l++;
      }
      int tillk = coins[r][1]-k+1-coins[l][0];
      long part = 1L*Math.max(0, tillk)*coins[l][2];
      res = Math.max(res, currscore-part);
    }
    return res;
  }
}