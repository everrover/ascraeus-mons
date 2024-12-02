package dsa.leetcode.RhoCassiopeiae;

public class TargetSum {

  /**
   * https://leetcode.com/problems/target-sum/submissions/
   *
   * DFS explaines the choices taken against the bottom-up approach.
   * Since + and - are associative, we can either build on top of the previous sum or subtract from it.
   *
   * TC: O(n * sum) SC: O(sum)
   * #array #dynamic-programming #backtracking #medium
   */  

  public int findTargetSumWays(int[] nums, int target) {
    int sum = 0;
    for(int num: nums) sum += Math.abs(num); // since in worst case, if num's negative, we need to go to -num for an increased value
    int []dp = new int[2*sum+1];
    dp[sum+nums[0]] = 1;
    dp[sum-nums[0]] += 1;

    for(int i=1; i<nums.length; i++){
      int []next = new int[2*sum+1];
      for(int s = -sum; s<=sum; s++){
        if(dp[sum+s] > 0){
          next[sum+s+nums[i]] += dp[s+sum];
          next[sum+s-nums[i]] += dp[s+sum];
        }
      }
      dp = next;
    }
    return Math.abs(target) > sum? 0 : dp[target+sum];
  }
  /**
  private int []nums;
  private int target;
  int [][]dp; int sum = 0;
  private int dfs(int idx, int curr){
    if(idx == nums.length){
      return curr == target?1:0;
    }
    int c = curr+this.sum;
    if(dp[idx][c] != -1) return dp[idx][c];
    int res = 0;
    res += dfs(idx+1, curr-nums[idx]);
    res += dfs(idx+1, curr+nums[idx]);
    return dp[idx][c] = res;
  }
  
  public int findTargetSumWays(int[] nums, int target) {
    this.sum = 0;
    for(int num: nums){
      this.sum += Math.abs(num);
    }
    dp = new int[nums.length][2*this.sum+1];
    for(int []d: dp) Arrays.fill(d, -1);
    this.nums = nums;
    this.target = target;
    return dfs(0, 0);
  }
   */
}