package dsa.leetcode.kuiperBelt;

public class MinimumEqualSumOfTwoArraysAfterReplacingZeros {
  /**
   * https://leetcode.com/problems/minimum-equal-sum-of-two-arrays-after-replacing-zeros/description/
   * 
   * The sum of two arrays is computed while setting sum for 0s to 1. Since minimum of acceptable integer is 1.
   * If sum1>sum2, and smaller array 0 has 0s, then we can replace 0s with 1s to make sum1=sum2.
   * If sum1<sum2, and smaller array 1 has 0s, then we can replace 0s with 1s to make sum1=sum2.
   * Else return -1.
   * 
   * #arrays #greedy #compuational-math
   */
  public long minSum(int[] nums1, int[] nums2) {
    long x = 0, s1 = 0;
    for(int num: nums1){
      if(num == 0) {x++; s1++;}
      else s1+=num;
    }
    long y= 0, s2 = 0;
    for(int num: nums2){
      if(num == 0) {y++; s2++;}
      else s2+=num;
    }
    // System.out.println(s1+":"+s2);
    if(s1-s2 > 0 && y>0){
      return s1;
    }else if(s2-s1 > 0 && x>0){
      return s2;
    }else if(s1 == s2){
      return s1;
    }else{
      return -1;
    }
  }
}
