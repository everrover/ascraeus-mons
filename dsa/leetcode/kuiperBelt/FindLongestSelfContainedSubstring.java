package dsa.leetcode.KuiperBelt;

import java.util.Arrays;

public class FindLongestSelfContainedSubstring {

  /**
   * https://leetcode.com/problems/find-longest-self-contained-substring/submissions/
   * This method aims to find the longest self-contained substring within a given string.
   * A self-contained substring means that for any character in the substring, it doesn't appear outside it.
   *
   * The range for any given character is limited to it's max and min index in the string.
   * If we take any boundary in sub-array we have two chars(can be same). Alternative way to look at it is with 
   * a char sub-array starting and ending with two characters, the right boundary will be the max of the two characters
   * and the left boundary will be the min of the two characters. This we mimic with a for loop for leftmost point
   * and rightmost point being max of the two character's boundaries.
   * 
   * We then check if the substring is self-contained by checking if the count of each character in the substring
   * (pre-detemined by prefix sum).
   * 
   * TC: O(n * 26 * 26) SC: O(n*26)
   * #substring #optimization #hard #prefix-sum #alt-way-to-look-at-problem
   */
  public int maxSubstringLength(String s) {
    char []chs = s.toCharArray();
    int []maxC  = new int[26]; Arrays.fill(maxC, -1);
    int []minC = new int[26]; Arrays.fill(minC, -1);
    int [][]counts = new int[chs.length][26];
    maxC[chs[0]-'a'] = 0;
    minC[chs[0]-'a'] = 0;
    counts[0][chs[0]-'a'] = 1;
    for(int i=1; i<chs.length; i++){
      int ch = chs[i]-'a';
      maxC[ch] = i;
      if(minC[ch] == -1) minC[ch] = i;
      for(int j=0; j<26; j++){
        if(ch != j) counts[i][j] = counts[i-1][j];
        else counts[i][j] = counts[i-1][j]+1;
      }
    }
    var res = -1;
    for(int i=0; i<26; i++){
      if(minC[i] == -1) continue;
      int l = minC[i];
      for(int j=0; j<26; j++){
        if(minC[j] == -1 || minC[j] < l) continue;
        int r = Math.max(maxC[i], maxC[j]);
        if(r-l+1 == chs.length) continue;
        if(check(counts, l, r)) res = Math.max(res, r-l+1);
      }
    }
    return res;
  }
  
  private boolean check(int [][]counts, int start, int end){
    int n = counts.length-1;
    for(int i=0; i<26; i++){
      int ic = counts[end][i] - (start==0?0:counts[start-1][i]);
      if(ic == 0 || ic == counts[n][i]) continue;
      return false;
    }
    return true;
  }

}