package dsa.leetcode.JupitersGreatStorm;

import java.util.*;

public class CountSubarraysWithFixedBounds {

  /**
   * https://leetcode.com/problems/count-subarrays-with-fixed-bounds/submissions/1618276109/?envType=daily-question&envId=2025-04-26
   *
   * Iterate through the array, maintaining two queues to track indices of minK and maxK.
   * When both queues are non-empty, count subarrays formed from maximum of those indices to current.
   * Divide the array based on boundaries formed by elements out of range.
   * 
   * Basically these subarrays are split by the elements that are out of range. To keep track of 
   * the shortest range of minK and maxK, we use a monotonic queue. And for result, we use the largest of two
   * indices to calculate the number of valid subarrays from a given `i`
   * 
   * Consider the case of a range : 1 3 5 5 2 5 1
   *                                x   y y   y x
   *                                |   |
   *                                    | |   | |
   *
   * TC: O(n), where n is the length of nums
   * SC: O(n) in the worst-case due to queue operations
   * #array #queue #sliding-window #monotonic-queue #hard
   */

  public long countSubarrays(int[] nums, int minK, int maxK) {
    Queue<Integer> maxJ = new LinkedList<>(), minJ = new LinkedList<>();
    long res = 0L;
    for (int i = 0, j = 0; i < nums.length; i++) {
      j = Math.max(i, j);
      while (j < nums.length && nums[j] <= maxK && nums[j] >= minK) {
        if (nums[j] == maxK) { maxJ.offer(j); }
        if (nums[j] == minK) { minJ.offer(j); }
        j++;
      }
      if (!maxJ.isEmpty() && !minJ.isEmpty()){
        res += (j - Math.max(maxJ.peek(), minJ.peek()));
      }
      while (!maxJ.isEmpty() && maxJ.peek() < i) maxJ.poll();
      while (!minJ.isEmpty() && minJ.peek() < i) minJ.poll();
    }
    return res;
  }

  /**
 * https://leetcode.com/problems/count-subarrays-with-fixed-bounds/
 * Logically, I split the array into sections across indices where values were minK > x or x > maxK,
 * I executed my checks for valid ranges:: eg: 1,2,3,4,5,6,3,4,2,1,6,3,1,1,2,5,2,2,1,1,3,6 and minK=1 and maxK=3
 * => [1,2,3],4,5,6,[3],4,[2,1],6,3,[1,1,2],5,[2,2,1,1,3],6
 * For each valid range I found indexes where maxK and minK are present and calculated combinations using those fetched indices
 * -----------------------------------------------------------
 * eg:: xxx3xx8xxxx3x8xx is a valid range: maxK = 8 & minK = 3
 * We find all ranges where atleast one instance of `maxK` or `minK` has been observed.
 * We track only last seen `maxK` and `minK` instances
 * Please note if both maxK and minK are present in (splitIdx...currentIdx], we'll have a valid subarray
 * ... that'll become part of the result
 * xxx[3xx8]xxxx:: Count is 4*5=20 ... (1)
 * xxxYxx[8xxxx3]x:: Count is 7*2=14 ... (2)
 * xxxYxxZxxxx[3x8]xx:: Count is 12*3=6 ... (3)
 * ---
 * eg:: xxx3xx3xxxx8x8xx is a valid range: maxK = 8 & minK = 3
 * Ranges::
 * xxxYxx[3xxxx8]x :: 7*2=14, Results of xxxYxx[3xxxx8]x already includes results of xxx[3xxYxxxx8]x.
 * This is why we only track last seen 3 and 8
 * xxxYxx[3xxxxZx8]xx :: 7*3=21
 * Y=ignored 3, X=ignored 8
 * -----------------------------------------------------------
 * Twist:: maxK, minK, minP and maxP has been provided, which all should be part of sub-arrays?
 * Res:: We'd start tracking all four and use the extremes for computations. All four should be there. Check code for HASH{TWISTYFOUR}
 *
 * Formal proofs::
 * https://leetcode.com/problems/count-subarrays-with-fixed-bounds/discuss/2708018/Four-Pointers
 * https://leetcode.com/problems/count-subarrays-with-fixed-bounds/discuss/2708099/JavaC%2B%2BPython-Sliding-Window-with-Explanation
 */
  public long countSubarraysOlder(int[] nums, int minK, int maxK) {
    long res = 0L;
    int split = 0, maxJ = 0, minJ = 0;
    boolean maxF = false, minF = false;
    for(int i=0; i<nums.length; i++){
      if(nums[i] > maxK || nums[i] < minK){
        maxF = minF = false;
        split = i+1;
      }
      if(nums[i] == maxK) { maxJ = i; maxF = true; }
      if(nums[i] == minK) { minJ = i; minF = true; }
      // HASH{TWISTYFOUR} :: add if nums[i] == minP and maxP == nums[i] and set flags

      if(maxF && minF){
        res += (Math.min(maxJ, minJ)-split+1); // HASH{TWISTYFOUR} :: Select min(maxJ, maxP, minJ, minP) for fetching leftmost extreme
      }
    }
    return res;
  }
  public long countSubarraysNoBooleanVarsUsed(int[] nums, int minK, int maxK) {
    long res = 0L;
    int split = -1, maxJ = -1, minJ = -1;
    for(int i=0; i<nums.length; i++){
      if(nums[i] > maxK || nums[i] < minK){
        maxJ = minJ = -1;
        split = i;
      }
      if(nums[i] == maxK) { maxJ = i; }
      if(nums[i] == minK) { minJ = i; }
      // System.out.println(minJ+":"+maxJ+"::"+split);

      // this check hasn't been used by many and should be present
      // the subarray should have both the maximum and minimum
      if(maxJ != -1 && minJ != -1){
        res += (Math.min(maxJ, minJ)-split);
      }
    }
    return res;
  }
}