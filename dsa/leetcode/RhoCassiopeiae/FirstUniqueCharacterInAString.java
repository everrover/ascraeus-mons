package dsa.leetcode.RhoCassiopeiae;

public class FirstUniqueCharacterInAString {
  /**
   * https://leetcode.com/problems/first-unique-character-in-a-string/
   *
   * The solution involves using a frequency array to keep count of character occurrences 
   * and storing the first index where each character appears. The result is computed by 
   * finding the minimum index of the characters that appeared only once.
   *
   * TC: O(n) SC: O(1)
   * #hash-table #string #queue #counting #easy
   */
  
  public int firstUniqChar(String s) {
    int [][]freq = new int[26][2]; // Frequency array storing index and count
    for(int []f: freq){
      f[0] = -1; f[1] = 0; // Initialize with -1 (no index) and 0 (count 0)
    }
    int idx = 0;
    for(char c: s.toCharArray()){
      freq[c-'a'][0] = idx; // Store first occurrence index
      freq[c-'a'][1]++; // Count occurrences
      idx++;
    }
    int res = (int)1e5+1;
    for(int []f: freq){
      if(f[1] == 1) res = Math.min(res, f[0]); // Find smallest index with count 1
    }
    return res>1e5?-1:res; // Return -1 if no such character
  }
}