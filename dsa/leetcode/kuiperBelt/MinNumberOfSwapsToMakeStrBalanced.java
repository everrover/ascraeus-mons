package dsa.leetcode.kuiperBelt;

public class MinNumberOfSwapsToMakeStrBalanced {
  /**
   * https://leetcode.com/problems/minimum-number-of-swaps-to-make-the-string-balanced/
   * Simply, kept track of the number of '[' and ']' in the string. coming from left or right.
   * If any voilation is found, swap the characters and reset the counters. Could have used stack as well.
   * 
   * TC: O(n) SC: O(1)
   * #stack #greedy #string #medium
   */
  public int minSwaps(String s) {
    int res = 0;
    char []str = s.toCharArray();
    int l=0, r=str.length-1, cntl = 0, cntr = 0, cnt=0;
    for(char c: str){
      if(c==']') cnt++;
      else if(c=='[') cnt--;
    }
    if(cnt!=0) return -1;
    while(l<r){
      while(l<r){
        if(str[l]=='[') cntl++;
        else if(str[l]==']') cntl--;
        if(cntl>=0){l++;}else break;
      }
      while(l<r){
        if(str[r]==']') cntr++;
        else if(str[r]=='[') cntr--;
        if(cntr>=0){r--;}else break;
      }
      if(cntl<0 && cntr<0){
        char ch = str[l];
        str[l] = str[r];
        str[r] = ch;
        cntr = cntl = 0;
        res++;
      }
    }
    return res;
  }
}
