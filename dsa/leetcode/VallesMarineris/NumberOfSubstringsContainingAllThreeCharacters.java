package dsa.leetcode.VallesMarineris;

public class NumberOfSubstringsContainingAllThreeCharacters {

  /**
   * https://leetcode.com/problems/number-of-substrings-containing-all-three-characters/?envType=daily-question&envId=2025-03-11
   *
   * Utilize a sliding window approach. Similar as in CountOfSubstringsContainingEveryVowelAndKConsonantsIi.java
   *
   * TC: O(n), SC: O(1)
   * #hash-table #string #sliding-window #medium
   */

   public int numberOfSubstrings(String s) {
    char []chs = s.toCharArray();
    int l=0, r=0, res=0;
    int []cnts = new int[3];
    while(l<chs.length && r<chs.length){
      while(r<chs.length && (cnts[0]<1 || cnts[1]<1 || cnts[2]<1)){
        if(chs[r] == 'a') cnts[0]++;
        else if(chs[r] == 'b') cnts[1]++;
        else if(chs[r] == 'c') cnts[2]++;
        r++;
      }
      if(cnts[0]>=1 && cnts[1]>=1 && cnts[2]>=1){
        while(l<=r){
          if(chs[l] == 'a' && cnts[0]>0) cnts[0]--;
          else if(chs[l] == 'b' && cnts[1]>0) cnts[1]--;
          else if(chs[l] == 'c' && cnts[2]>0) cnts[2]--;
          l++;
          res += (chs.length-r+1);
          if(cnts[0] == 0 || cnts[1] == 0 || cnts[2] == 0) break;
        }
      }else{
        if(chs[l] == 'a') cnts[0]--;
        else if(chs[l] == 'b') cnts[1]--;
        else if(chs[l] == 'c') cnts[2]--;
        l++;
      }
    }
    return res;
  }
}