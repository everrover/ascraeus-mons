package dsa.leetcode.kuiperBelt;

public class Solution {
  public String minWindow(String s1, String s2) {
    int st = 0, minLength = Integer.MAX_VALUE, start=0, end=0;
    while(st<s1.length()){
      int r1 = st, r2 = 0;
      while(r1<s1.length()){
        if(s1.charAt(r1) == s2.charAt(r2)){
          r2++;
          if(s2.length() == r2) break;
        }
        r1++;
      }
      if(r2 != s2.length()) break; // no subseq found
      int l1=r1, l2=s2.length()-1;
      while(l2>=0){
        if(s1.charAt(l1) == s2.charAt(l2)){
          l2--;
        }
        l1--;
      }
      if(r1-l1<minLength){
        start = l1+1;
        end = r1+1;
        minLength = r1-l1;
      }
      st = l1+2;
    }
    return s1.substring(start, end);
  }
}