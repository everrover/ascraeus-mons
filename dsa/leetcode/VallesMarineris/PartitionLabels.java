package dsa.leetcode.VallesMarineris;

import java.util.*;

public class PartitionLabels {
  /**
   * https://leetcode.com/problems/partition-labels/description/?envType=company&envId=facebook&favoriteSlug=facebook-thirty-days
   *
   * Traverse the string to find the last occurrence of each character. Use a two-pointer technique to find
the rightmost boundary for each partition by ensuring all characters appear in one partition only.
   *
   * TC: O(n log n) SC: O(1)
   * #hash-table #two-pointers #string #greedy #medium
   */
  public List<Integer> partitionLabels(String s) {
    List<Integer> res = new LinkedList<>();
    char []chs = s.toCharArray();
    int [][]chpos = new int[26][2];
    for(int []d: chpos) Arrays.fill(d, -1);
    for(int i=0; i<chs.length; i++){
      int pos = chs[i]-'a';
      if(chpos[pos][0] == -1) chpos[pos][0] = i;
      chpos[pos][1] = i;
    }
    Arrays.sort(chpos, (a,b)->(a[0]==b[0]?(a[1]-b[1]):(a[0]-b[0])));
    int l = 0, r = 0, currr = 0, currl = 0, last = -1;
    while(l<26 && r<26){
      currl = chpos[l][0]; currr = chpos[l][1];
      while(r<26 && chpos[r][0] <= currr) {
        currr = Math.max(chpos[r][1], currr);
        r++;
      }
      res.add(currr-currl+1);
      l = r;
      if(chpos[l][0] == -1) {l++; continue;}
      r = l;
    }
    return res;
  }
}