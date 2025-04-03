package dsa.leetcode.VallesMarineris;

import java.util.*;

public class MinimumIndexOfAValidSplit {

  /**
   * https://leetcode.com/problems/minimum-index-of-a-valid-split/description/?envType=daily-question&envId=2025-03-27
   *
   * The method finds the minimum index to split the array such that both halves
   * have the same dominant element. It calculates the frequency of the dominant element
   * on both sides of the split and checks validity based on conditions.
   * 
   * MAJORITY ELEMENT is found using Voting Algorithm. Otherwise, we can use a hashmap to
   * count the frequency of each element.
   *
   * TC: O(n) SC: O(1)
   * #array #hashtable #sorting #medium
   */

   public int minimumIndex(List<Integer> nums) {
    int cand = -1, cnt = 0;
    for(int num: nums){
      if(cnt == 0) {cand = num; cnt++;}
      else{
        if(cand == num){
          cnt++;
        }else{
          cnt--;
        }
      }
    }
    if(cand == -1) return -1;
    cnt = 0;
    for(int num: nums){
      if(cand == num){
        cnt++;
      }
    }
    int tot = cnt; cnt = 0;
    for(int i=0; i<nums.size(); i++){
      if(cand == nums.get(i)) cnt++;
      int fc = cnt, fp = i+1-cnt;
      int sc = tot-cnt, sp = nums.size()-i-1-tot+cnt;
      if(fc>fp && sc>sp) return i;
    }
    return -1;
  }
}