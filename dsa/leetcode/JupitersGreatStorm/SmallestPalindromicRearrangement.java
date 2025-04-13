package dsa.leetcode.JupitersGreatStorm;

class Solution {
  /**
   * https://leetcode.com/problems/smallest-palindromic-rearrangement-i/description/
   *
   * Construct the smallest palindrome by arranging letters in sorted order to form half and using the reverse to form the other half.
   * Ensure single odd counted character is placed in the middle if needed.
   *
   * TC: O(n) SC: O(1)
   * #palindrome #string #sorting #medium
   */
  public String smallestPalindrome(String s) {
    char []chs = s.toCharArray();
    int []cnt = new int[26];
    for(char ch: chs) cnt[ch-'a']++;
    int odd = -1;
    for(int i=0; i<cnt.length; i++){
      if(cnt[i]%2 == 1) if(odd != -1) return ""; else odd = i;
    }
    for(int i=0,j=0; i<26; i++){
      int c = 0;
      while(c<cnt[i]/2) {chs[j++] = (char)('a'+i); c++;}
    }
    if(odd != -1) chs[chs.length/2] = (char)('a'+odd);
    return new String(chs);
  }
}