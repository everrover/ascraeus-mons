package dsa.leetcode.fermi_s_paradox;

import java.util.*;

public class FindTheClosestPalindrome {

    /**
     * https://leetcode.com/problems/find-the-closest-palindrome/description/?envType=company&envId=goldman-sachs&favoriteSlug=goldman-sachs-all
     *
     * The goal is to find the nearest palindrome number. For each number,
     * generate potential palindromes by adjusting the first half of the number.
     * Compare these to find the closest one.
     * 
     * The potential palindromes are
     * - smallest palindrome larger than the number ... added 1
     * - largest palindrome smaller than the number ... subtracted 1
     * - same number in reverse order ... no change
     * - smallest palindrome with 1 less digit ... 10^m-1 ... 1080 -> 999[a candidate]
     * - smallest palindrome with 1 more digit ... 10^m+1 ... 9999 -> 10001[a candidate]
     *
     * TC: O(1) SC: O(1)
     * #math #string #hard
     */
  public String nearestPalindromic(String n) {
    final int m = n.length();
    if(m == 1) return ""+(Long.valueOf(n)-1);
    long res = (long)1e19, diff = Long.MAX_VALUE;
    String onehalf = n.substring(0, (m+1)/2);
    long origval = Long.valueOf(n);
    List<Long> cand = new LinkedList<>();
    StringBuilder sb1 = new StringBuilder();
    StringBuilder sb2 = new StringBuilder();
    for(int i: new int[]{-1,0,1}){
      sb1.append((Long.valueOf(onehalf)+i));
      sb2.append((Long.valueOf(onehalf)+i));
      if(m%2 == 1 && sb2.length() > 0) sb2.setLength(sb2.length() - 1);
      cand.add(Long.valueOf(sb1.toString()+sb2.reverse().toString()));
      sb1.setLength(0); sb2.setLength(0);
    }
    cand.add(-1L+(long)Math.pow(10, m-1));
    cand.add(1L+(long)Math.pow(10, m));
    for(long c: cand){
      if(origval != c){
        if(diff > Math.abs(origval-c)) {
          res = c;
          diff = Math.abs(origval-c);
        }else if(diff == Math.abs(origval-c)) {
          res = Math.min(res, c);
        }
      }
    }
    return ""+res;
  }
}