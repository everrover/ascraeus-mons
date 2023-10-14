package dsa.leetcode.kuiperBelt;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class CountOfSubMultisetsWithBoundedSum {

  /**
   * https://leetcode.com/problems/count-of-sub-multisets-with-bounded-sum/description/
   *
   * It was as simple as putting in a prefix-sum array😑. The solution gave me the idea. Again I forgot to read out the constraints.
   * It was 0-1 knapsack problem. But with needed optimization of grouping the same numbers together.
   * memo[i] = count of possible subsets with sum i
   * For all given `nums` memo[i] = memo[i] + memo[i - num] + memo[i - 2 * num] + ... + memo[i - freq * num] till (i - freq * num >= 0)
   *
   * TC: O(r * min(N, sqrt(sum(nums))) SC: O(r + min(N, sqrt(sum(nums)))
   * #dynamic-programming #knapsack #optimization #prefix-sum  #contest
   */
  // https://leetcode.com/problems/count-of-sub-multisets-with-bounded-sum/solutions/4168064/Optimized-Dynamic-Programming/
  private final int MOD = (int)(1e9+7);
  public int countSubMultisets(List<Integer> nums, int l, int r) {
    TreeMap<Integer, Integer> numsMap = new TreeMap<>();
    for(int num: nums) numsMap.put(num, 1+numsMap.getOrDefault(num, 0));
    long []memo = new long[r+1];
    memo[0] = 1;
    for(Map.Entry<Integer, Integer> m: numsMap.entrySet()) {
      int num = m.getKey(), freq = m.getValue();
      long[] prefix = Arrays.copyOf(memo, r + 1);
      for (int i = 0; i <= r; ++i) {
        if (i >= num) {
          prefix[i] += prefix[i - num];
          prefix[i] %= MOD;
        }
      }
      for(int i=r; i>=0; i--){
        if(num>0) {

          int j = i - (freq+1) * num;
          memo[i] = prefix[i] - (j>=0? prefix[j]:0);
          memo[i] = Math.floorMod(memo[i], MOD); // https://stackoverflow.com/questions/52377246/the-difference-between-math-floormod-and-in-java
          // System.out.println("Here"+num+":"+freq+":"+i+":"+(i-num));
          // for(int k=1; i-k*num>=0 && k<=freq; k++){
          //   // System.out.println("Here"+num+":"+freq+":"+i+":"+k);
          //   memo[i] += memo[i-k*num];
          //   memo[i] %= MOD;
          // }
        }else {
          memo[i] *= freq+1;
          memo[i] %= MOD;
        }
      }
    }
    int res = 0;
    for(int i=l; i<=r; i++){
      // System.out.println(memo[i]);
      res += memo[i];
      res %= MOD;
    }
    return res;
  }
}
