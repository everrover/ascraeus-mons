package dsa.leetcode.KuiperBelt;

import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeSet;

public class Solution1 {

  public long countSubarrays(int[] nums, long k) {
    long res = 0;
    int l=0, r=0;
    long prefix = 0L;
    while(r<nums.length){
      prefix += nums[r];
      while(prefix*(r-l+1) >= k) {
        prefix -= nums[l];
        l++;
      }
      res += r-l+1;
      r++;
    }
    return res;
  }
  public static void main(String[] args) {
    Solution1 s = new Solution1();
    // [2,1,4,3,5]
    //10
    System.out.println(
        s.countSubarrays(
            new int[]{2,1,4,3,5},
        10
        ));
    System.out.println(
        s.countSubarrays(
            new int[]{8,6,4,9,16,1000,8,3,4},
            80
        )
    );
  }
}