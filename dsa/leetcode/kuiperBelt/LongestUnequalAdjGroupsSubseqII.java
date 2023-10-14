package dsa.leetcode.kuiperBelt;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class LongestUnequalAdjGroupsSubseqII {
  /**
   * https://leetcode.com/problems/longest-unequal-adjacent-groups-subsequence-ii/
   * 
   * Implemented LIS with the provided constraints. 
   * `parent` array is used to keep track of the longest subsequence. 
   * Although it's more like a `next-in-sequence` array.
   * 
   * TC: O(n^2) SC: O(n)
   * #dynamic-programming #augmentation-on-known-algorithm #longest-increasing-subsequence #dp-trailing #contest
   */
  private int n;
  private String[] words;
  private int[] groups;
  public List<String> getWordsInLongestSubsequence(int n, String[] words, int[] groups) {
    this.n = n;
    int []parent = new int[n];
    int []memo = new int[n];
    this.groups = groups;
    this.words = words;
    Arrays.fill(memo, -1);
    for(int i=0; i<n; i++){
      lisLen(i, memo, parent);
    }
    int largest = 0, li = -1;
    for(int i=0; i<n; i++){
      // System.out.println(memo[i]+":"+parent[i]);
      if(memo[i] > largest){
        largest = memo[i];
        li = i;
      }
    }
    List<String> res = new LinkedList<>();
    while(li != parent[li]){
      res.add(words[li]);
      li = parent[li];
    }
    res.add(words[li]);
    return res;
  }
  private int lisLen(int i, int[] memo, int[] parent){
    if(i == n) return 0;
    if(memo[i] != -1) return memo[i];
    int res = 1;
    parent[i] = i;
    for(int j=i+1; j<n; j++) {
      if(groups[j] != groups[i] && hamming(words[i], words[j])==1){
        int ret = 1+lisLen(j, memo, parent);
        if(ret >= res){
          parent[i] = j;
          res = ret;
        }
      }
    }
    return memo[i] = res;
  }
  
  private int hamming(String w1, String w2){
    if(w1.length() != w2.length()) return -1;
    int h = 0;
    for(int i=0; i<w1.length(); i++) 
      h += (w1.charAt(i)!=w2.charAt(i))?1:0;
    return h;
  }
}
