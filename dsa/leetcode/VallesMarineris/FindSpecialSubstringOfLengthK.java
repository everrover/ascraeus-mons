package dsa.leetcode.VallesMarineris;

public class Solution {

  /**
   * https://leetcode.com/problems/find-special-substring-of-length-k/description/
   *
   * Check for consecutive characters of length k and ensure the previous and next characters are different.
   *
   * TC: O(n) SC: O(1)
   * #string #easy
   */

  public boolean hasSpecialSubstring(String s, int k) {
    char []chs = s.toCharArray();
    for(int i=0; i<chs.length;){
      int j=0;
      boolean flag = true;
      while(i+j<chs.length){
        if(chs[i+j] != chs[i]) {break;}
        j++;
      }
      if(j != k) flag = false;
      if(flag){
        boolean flaga = false, flagb = false;
        if(i==0 || chs[i-1] != chs[i]) flaga = true;
        if(i+j>=chs.length || chs[i+j] != chs[i]) flagb = true;
        if(flaga && flagb) return true;
      }
      i = i+j;
    }
    return false;
  }
}