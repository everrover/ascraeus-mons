package dsa.leetcode.EuropasOceanWorld;

class MajorityElement {
  /**
   * https://leetcode.com/problems/majority-element/description/?envType=company&envId=makemytrip&favoriteSlug=makemytrip-all
   *
   * Use Boyer-Moore Voting Algorithm, which maintains a current candidate and a count,
   * updating the candidate whenever the count reaches zero.
   *
   * TC: O(n) SC: O(1)
   * #array #hash-table #divide-and-conquer #sorting #counting #easy
   */
  public int majorityElement(int[] nums) {
    int majority, count;
    count = 0;
    majority = nums[0];
    for(int num: nums){
      if(num == majority){
        count++;
      }else{
        if(count>0){
          count--;
        }else{
          count = 1;
          majority = num;
        }
      }
    }
    return majority;
  }
}