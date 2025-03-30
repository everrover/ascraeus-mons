package dsa.leetcode.VallesMarineris;

class LongestPalindromeAfterSubstringConcatenationI {
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
    int l =0, r = s.length()-1;
    while(l<r){
      if(s.charAt(l) != s.charAt(r)) return false;
      l++; r--;
    }
    return true;
  }
  public int longestPalindrome(String s, String t) {
    int res = 1;
    for(int i=0; i<s.length(); i++){
      for(int j=i; j<=s.length(); j++){
        String fs = s.substring(i,j);
        for(int k=0; k<t.length(); k++){
          for(int l=k; l<=t.length(); l++){
            String ls = fs+t.substring(k,l);
            if(isP(ls)) res = Math.max(res, ls.length());
          }
        }
      }
    }
    return res;
  }
}