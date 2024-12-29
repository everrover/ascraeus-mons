package dsa.leetcode.fermi_s_paradox;

public class FindTheLexicographicallyLargestStringFromTheBoxI {
  
  /**
   * https://leetcode.com/problems/find-the-lexicographically-largest-string-from-the-box-i/description/
   *
   * Amongst all possible substrings(with a maximum size of n - numFriends + 1), the largest one is tracked
   * and returned.
   * 
   * TC: O(n) SC: O(n) where n is the length of the string
   * #string #greedy #medium
   */
  
  public class Solution {
    public String answerString(String word, int numFriends) {
      if(numFriends == 1) return word;
      char []chs = word.toCharArray();
      String sb = "", res = "";
      for(int i=chs.length-1; i>=0; i--){
        sb = chs[i]+sb;
        if(sb.length()>chs.length-numFriends+1) sb = sb.substring(0, sb.length()-1);
        if(sb.compareTo(res)>0) res = sb;
      }
      return res;
    }
  }
}