package dsa.leetcode.kuiperBelt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class LeetcodeBiweekly118 {

  /**
   * https://leetcode.com/problems/find-words-containing-character/
   * #easy #string
   * TC: O(n) SC: O(n)
   */
  public List<Integer> findWordsContaining(String[] words, char x) {
    List<Integer> ans = new LinkedList<>();
    int i=0;
    for(String word: words){
      if(word.contains(""+x)) ans.add(i);
      i++;
    }
    return ans;
  }

  /**
   * https://leetcode.com/problems/number-of-days-in-a-month/
   * 
   * from the given constraints, we can deduce that from any given index we can only move to [index+1...2*index+1],
   *  if we want to buy all. Therefore we can use a recursive approach to solve this problem. And memoize the results...
   * 
   * dp[i] = min(dp[i], prices[i-1] + dp[j]), where i <= j <= 2*i+1
   * 
   * #array #dynamic-programming
   * TC: O(n) SC: O(n)
   */
  public int minimumCoins(int[] prices) {
    int []dp = new int[prices.length+1];
    Arrays.fill(dp, -1);
    return rec(1, prices, dp);
  }
  private int rec(int idx, int []prices, int[] dp){
    if(idx>prices.length) return 0;
    else if(dp[idx] != -1) return dp[idx];
    int res = Integer.MAX_VALUE;
    for(int i=idx+1; i<=idx*2+1; i++){
      // int p= dp(i, prices);
      // System.out.println(idx+":"+i+":"+p);
      res = Math.min(res, prices[idx-1]+rec(i, prices, dp));
    }
    return dp[idx]=res;
  }

  /**
   * https://leetcode.com/problems/maximize-area-of-square-hole-in-grid/
   * 
   * since all the bars can be removed, we do so. now we are left with bars that can't be removed.
   * if we count consecutive bars, we can find the maximum gap between them. this gap is counted in both
   * horizontal and vertical directions. therefore we take the minimum of the two which returns the maximum
   * area of the square hole.
   * 
   * #math #matrix #logic
   * TC: O(n) SC: O(n)
   */
  public int maximizeSquareHoleArea(int n, int m, int[] hbars, int[] vbars) {
    int gap = Math.min(getMaxGap(hbars), getMaxGap(vbars));
    return gap * gap;
  }
  private int getMaxGap(int[] bars){
      Arrays.sort(bars);
      int count = 2, res = 2;
      for(int i = 1; i < bars.length; ++i) {
          count = (bars[i-1]+1 == bars[i])? count + 1: 2;
          res = Math.max(res, count);
      }
      return res;
  }

  /**
   * https://leetcode.com/problems/find-maximum-non-decreasing-array-length/
   * format the text, put in emoji's and complete the equations for following post. i want it all in markdown.
   * Essentially we are to split the array into multiple sub arrays such that the constraints are satisfied. Each 
   * of the aforementioned sub arrays are summed up to create one single element.
   * 
   * In order to solve this we assume that `dp[i]` denotes the result at ith index.
   * 
   * ```dp_i = max(dp_i, dp_j + 1), for valid values of j```
   * 
   * Now `j` is the largest index where `sum(a[j+1] ... a[i]) >= last[j]`
   * last[j] = previous valid value that satisfies non-decreasing property for result A[x]
   * 
   * If precomputed prefix sum array is used,
   * `prefix[i] - prefix[j] >= last[j] => prefix[i] >= last[j] + prefix[j]`
   * 
   * Therefore,
   * ```
   * dp[i] = max(dp[i], dp[j] + 1), where prefix[i] >= last[j] + prefix[j]
   * ```
   * 
   * Which does not work for our constraints. Why? Since this runs in O(n^2) time.
   * 
   * Now there's two observations... Both dp[i] and last[i] are non-decreasing in nature. Therefore to satisfy 
   * the constraints, we can use binary search to find the pivot point and better yet, we can traverse through 
   * last[i] once, and hence we need only a monotonic stack. 
   * 
   * Now to maintain monotonic properties, we'd put checks on `dp[i] >= dp[j]` and `last[i] + prefix[j] <= last[i] + prefix[i]`
   * 
   * Note how `dp[x]` is stored within the stack. This is because we need to find and use only the largest `dp[x]`
   * that satisfies the constraints.
   * 
   * #array #dynamic-programming #stack #mono-stack #math #combinatorics #tricky #wasnt-able-to-solve
   * TC: O(n) SC: O(n)
   */
  public static class S {
        public long lastPlusPre; // corresponds to last + pre
        public long pre; // corresponds to the pre value
        public long dp; // corresponds to dp

        public S(long lastPlusPre, long pre, long dp) {
            this.lastPlusPre = lastPlusPre;
            this.pre = pre;
            this.dp = dp;
        }
    }

    public int findMaximumLength(int[] nums) {
        long n = nums.length;
        List<S> stk = new ArrayList<>();
        stk.add(new S(0, 0, 0));

        long pre = 0;
        long res = 0;
        int j=0;
        for (int i = 0; i < n; i++) {
            pre += nums[i];
            j = Math.min(j, stk.size()-1);
            while (j + 1 < stk.size() && pre >= stk.get(j + 1).lastPlusPre) j++;
            S lastEntry = stk.get(j);
            long cur_dp = lastEntry.dp + 1;
            res = cur_dp;

            long last = pre - lastEntry.pre;
            while (!stk.isEmpty() && stk.get(stk.size() - 1).lastPlusPre >= last + pre) {
                stk.remove(stk.size() - 1);
            }
            stk.add(new S(last + pre, pre, cur_dp));
        }
        return (int) res;
    }
}