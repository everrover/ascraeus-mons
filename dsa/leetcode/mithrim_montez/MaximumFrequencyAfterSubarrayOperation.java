package dsa.leetcode.mithrim_montez;

public class MaximumFrequencyAfterSubarrayOperation {
/**
 * https://leetcode.com/problems/maximum-frequency-after-subarray-operation/description/
 *
 * We're basically finding the majority element amongst a `num` and the element `k`.
 * For each subarray, we've got three cases:
 * 3 parts
 * [0,x] ... (0) [x+1, y] ... (1) [y+1, n-1] ... (2)
 * = ks[x] + (nums[y]-nums[x]) + (ks[n-1]-ks[y])
 * = ks[x]+nums[y]-nums[x]+ks[n-1]-ks[y]
 * = nums[y] - nums[x] - (ks[y] - ks[x]) ... (I)
 *   + ks[n-1] ... (II)
 * 
 * Doesn't yeild anything.
 * 
 * ...(I) - For any given subarray, a `num` and a `k`, if we encounter `num`(+1) else if we 
 * encounter `k`(-1). This essentially represents simulates this sum. Any `num`'s out of this subarray 
 * are assumed to be 0 and all `k`'s are assumed to be 1. So, we essentially need to 
 * find the max-sum subarray of such 1's and -1's. Brute force doesn't work, so we use 
 * Kadane's algorithm to find the max-sum subarray. Since `num` is limited to 50, we can 
 * do it for each `num` and find the max sum subarray across all `num`'s. 
 * 
 * `num`+`x`=`k` => `k`+`x`!=`k` if `x`!=0
 * +1 is added because if we add `x` to the subarray which transforms it to a `k` our `res` increases by 1
 * -1 is added if we add `k` to the subarray which transforms sth other than `k` and `res` decreases by 1
 * 
 * - To get the result we add the count of `k`'s in the original array due to ...(II).
 *
 * TC: O(n) SC: O(1)
 * #prefix-sum #sliding-window #medium #tricky
 */
  
 public int maxFrequency(int[] nums, int k) {
    int []nummap = new int[51];
    for(int num: nums) nummap[num]++;
    int res = 0;
    for(int i=0; i<=50; i++){
      res = Math.max(res, kadanesearch(nums, k, i));
    }
    return res+nummap[k];
  }

  private int kadanesearch(final int []nums, final int k, final int b) {
    int res = 0, currcnt = 0;
    for(int num: nums){
      if(num == b) currcnt++;
      if(num == k) currcnt--;
      currcnt = Math.max(currcnt, 0);
      res = Math.max(currcnt, res);
    }
    // y y y y y x x k x x x k k x x k y y y y
    // return 7-4=3 for x
    return res;
  }
}
// k=2
// 1,1,1,1,2,2,2,1,2,3,3,3,2,3,2,3,3
// 1 2 3 4 4 4 4 5 5 5 5 5 5 5 5 5 5
// 0 0 0 0 1 2 3 3 4 4 4 4 5 5 6 6 6
// 0 0 0 0 0 0 0 0 0 1 2 3 3 4 4 5 6
// 