package dsa.leetcode.RhoCassiopeiae;

import java.util.*;

public class SortCharactersByFrequency {
  
  /**
   * https://leetcode.com/problems/sort-characters-by-frequency/
   * 
   * Given a string s, sort it in decreasing order based on the frequency of the characters.
   * The frequency of a character is the number of times it appears in the string.
   * Return the sorted string.
   * 
   * TC: O(n log n) for sorting SC: O(n) for frequency count storage
   * #hash-table #string #sorting #heap #medium
   */
  
  public String frequencySort(String s) {
    int [][]chcnt = new int[128][2];
    for(int i = 0; i < 128; i++){
      chcnt[i][0] = i; // Initialize character index
    }
    for(char ch : s.toCharArray()){
      chcnt[ch - '0'][1]++; // Count character frequency
    }
    Arrays.sort(chcnt, (a, b) -> b[1] - a[1]); // Sort based on frequency
    StringBuilder res = new StringBuilder();
    for(int []freq : chcnt){
      while(freq[1] > 0) {
        --freq[1];
        res.append((char)('0' + freq[0])); // Append sorted characters
      }
    }
    return res.toString();
  }
}