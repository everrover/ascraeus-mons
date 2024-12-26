package dsa.leetcode.fermi_s_paradox;

public class SumOfDigitsOfStringAfterConvert {
  
  /**
   * https://leetcode.com/problems/sum-of-digits-of-string-after-convert/
   *
   * Convert the letters of the string to their alphabet positions, form an integer,
   * and then repeatedly sum its digits k times. This reduces the problem to finding 
   * the digit sum iteratively.
   * 
   * TC: O(n + k*m), SC: O(m) where m is the number of digits initially after conversion,
   * n is the length of the string, and k is the number of transformations.
   * #string #simulation #easy
   */
  
  public int getLucky(String s, int k) {
    StringBuilder sb = new StringBuilder();
    for(char ch: s.toCharArray()){
      sb.append((int)(ch-'a')+1);
    }
    String q = sb.toString();
    int cnt = 0;
    while(k-- > 0) {
      int sz = q.length();
      cnt = 0;
      while(sz-- > 0) {
        cnt += (q.charAt(sz) - '0');
      }
      q = "" + (cnt);
    }  
    return cnt;
  }
}