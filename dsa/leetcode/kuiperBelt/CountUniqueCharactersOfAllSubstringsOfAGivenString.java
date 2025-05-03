package dsa.leetcode.KuiperBelt;

class CountUniqueCharactersOfAllSubstringsOfAGivenString {
  /**
   * https://leetcode.com/problems/count-unique-characters-of-all-substrings-of-a-given-string/
   * 
   * It iterates through each character from 'A' to 'Z' and searches for its occurrences in the string. And uses the occurance pos
   * to compute result. Same substrings with diff pos are still allowed and hence position is enough.
   * 
   * For each char, we build ranges of substrings with the char as the only character in substr. 
   * For example, first A can be a part of ranges [0,0] and [0,3].
   * Hence first A can be a part of 1*4 = 4 substrings.
   * Second A is ingnored above, since if included would `
   * For 2nd A, it can be a part of ranges [1,4] and [4,6], i.e. 4*3 = 12 substrings.
   * A * * * A * * A *
   * 
   * DP is applied in other sol's i saw, but how, I don't understand.
   * 
   * TC: O(n*26=n) SC: O(26=1)
   * #hard #string #array #dynamic-programming #ignoring-unnecessary-subproblems #inverted-thought-process
   */
  public int uniqueLetterString(String s) {
    char []chs = s.toCharArray();
    int res = 0;
    // Loop through A to Z to find occurrences
    for(char c='A'; c<='Z'; c++){
      int i = 0;
      int prev = 0, pprev = -1;
      // Searching for character c in the string
      while(i<chs.length){
        while(i<chs.length){
          if(chs[i] == c) break;
          i++;
        }
        // Calculating unique character count
        if(i<chs.length && c == chs[i]){
          if(pprev != -1) res += (prev - pprev + 1)*(i-prev);
          pprev = prev;
          prev = i++;
        }
      }
      // Edge case handling for last character
      if(i == chs.length && pprev != -1){
        res += (prev - pprev + 1)*(i-prev-1);
      }
    }
    // Return total count of unique characters across all substrings
    return res;
  }
}