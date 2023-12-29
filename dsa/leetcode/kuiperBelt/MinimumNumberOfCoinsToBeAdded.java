package dsa.leetcode.kuiperBelt;

import java.util.Arrays;

public class MinimumNumberOfCoinsToBeAdded {
  /**
   * https://leetcode.com/problems/minimum-number-of-coins-to-be-added/
   * 
   * All elements are needed in set from [1...target]. If any element is missing, we need to add it. 
   * Once, added `x` since all elements before `x` are reachable, we can reach upto `2*x-1` elements.
   * 
   * In below case `t=element needed to be added in set`
   * 
   * TC: O(nlogn) SC: O(1)
   * 
   * #array #greedy #medium #math #induction?
   */
  public int minimumAddedCoins(int[] coins, int target) {
    Arrays.sort(coins);
    
    int t=0, res=0;
    for(int coin:coins){
      while(t<coin-1){ // `t` needs to be added in set
        res++;
        t=2*t+1; // this is the min element to be added
      }
      t += coin; // Till `t` all values are reachable
      if(t>=target) return res;
    }
    while(t<target){
      res++;
      t=2*t+1; // this is the min element to be added
    }
    return res;
  }
}
