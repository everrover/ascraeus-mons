package dsa.leetcode.kuiperBelt;

import java.util.LinkedList;
import java.util.List;

public class LongestUnequalAdjGroupsSubseqI {
  /**
   * https://leetcode.com/problems/longest-unequal-adjacent-groups-subsequence-i/
   * 
   * Implemented via two simple greedy iterations. Could've done with LIS as well.
   * 
   * TC: O(n) SC: O(1)
   * #greedy #dynamic-programming #augmentation-on-known-algorithm #longest-increasing-subsequence #dp-trailing  #contest
   */
  public List<String> getWordsInLongestSubsequence(int n, String[] words, int[] groups) {
    List<String> res1 = new LinkedList<>();
    List<String> res2 = new LinkedList<>();
    int l = 0;
    for(int i=0; i<n; i++){
      if(groups[i] == l) {
        res1.add(words[i]);
        l = l==0?1:0;
      }
    }
    l = 1;
    for(int i=0; i<n; i++){
      if(groups[i] == l) {
        res2.add(words[i]);
        l = l==0?1:0;
      }
    }
    return res1.size()>res2.size()?res1:res2;
  }
}
