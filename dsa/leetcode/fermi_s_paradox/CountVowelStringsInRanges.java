package dsa.leetcode.fermi_s_paradox;

import java.util.*;

public class Solution {
  /**
   * https://leetcode.com/problems/count-vowel-strings-in-ranges/?envType=daily-question&envId=2025-01-02
   *
   * Precompute the prefix sum of strings that start and end with vowels. Use unordered set to
   * store vowels. Check if the first and last characters of the string are present in the vowels set.
   * Subtract prefix sum for range [l-1, r] to find the number of strings starting and ending with vowels.
   *
   * TC: O(n + q) SC: O(n)
   * #array #string #prefix-sum #medium
   */
  
  private Set<Character> set;
  
  public int[] vowelStrings(String[] words, int[][] queries) {
    if(set == null) { 
      set = new HashSet<>();
      set.add('a'); set.add('e'); set.add('i'); set.add('o'); set.add('u');
    }
    int []pre = new int[words.length];
    for(int i = 0; i < words.length; i++) {
      if (i > 0) pre[i] = pre[i-1];
      if (
        set.contains(words[i].charAt(0)) &&
        set.contains(words[i].charAt(words[i].length()-1))
      ) pre[i]++;
    }
    
    int[] results = new int[queries.length];
    for (int j = 0; j < queries.length; j++) {
      int l = queries[j][0];
      int r = queries[j][1];
      results[j] = l > 0 ? pre[r] - pre[l-1] : pre[r];
    }
    return results;
  }
}