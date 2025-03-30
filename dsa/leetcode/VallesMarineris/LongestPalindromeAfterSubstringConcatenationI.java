package dsa.leetcode.VallesMarineris;

class Solution {
  /**
   * https://leetcode.com/problems/longest-palindrome-after-substring-concatenation-i/
   * 
   * To find the longest palindrome from potential substrings of two strings, iterate through substrings,
   * concatenate them, and check if the result is a palindrome.
   * 
   * TC: O(n^3) SC: O(n)
   * #substring #palindrome #medium
   */

  private boolean isP(String s){
    int l = 0, r = s.length() - 1;
    while(l < r){
      if(s.charAt(l) != s.charAt(r)) return false;
      l++; r--;
    }
    return true;
  }

  public int longestPalindrome(String s, String t) {
    int res = 1;
    for(int i = 0; i < s.length(); i++){
      for(int j = i; j <= s.length(); j++){
        String fs = s.substring(i, j);
        // Further code to consider all potential concatenations and updates to 'res' needed...
      }
    }
    return res;
  }
}