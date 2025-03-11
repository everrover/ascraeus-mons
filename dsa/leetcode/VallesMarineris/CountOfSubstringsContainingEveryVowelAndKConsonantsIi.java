package dsa.leetcode.VallesMarineris;

import java.util.*;

public class CountOfSubstringsContainingEveryVowelAndKConsonantsIi {

  /**
   * https://leetcode.com/problems/count-of-substrings-containing-every-vowel-and-k-consonants-ii/description/?envType=daily-question&envId=2025-03-10
   *
   * Uses a sliding window approach where we maintain counts of vowels and consonants using maps and sets.
   * If the substring meets the condition, increment the result counter.
   * 
   * TC: O(n) SC: O(1)
   * #sliding-window #string #hash-table #medium
   */

  private static Set<Character> vowels = new HashSet<>();
  public long countOfSubstrings(String word, int k) {
    if(vowels.isEmpty()) {
      vowels.add('a');
      vowels.add('e');
      vowels.add('i');
      vowels.add('o');
      vowels.add('u');
    }
    char []chs = word.toCharArray();
    int l = 0, r = 0, consonants = 0;
    Map<Character, Integer> vowmap = new HashMap<>();
    long count = 0, res = 0;
    while(r<chs.length){
      consonants = add(chs[r], vowmap, consonants);
      while(l<=r && consonants > k) {
        count = 0; // the count of leading vowels is reset here since only when consonants are removed
        // we start a new range of substrings
        consonants = rem(chs[l], vowmap, consonants);
        l++;
      }
      if(consonants == k && vowmap.size()==5) {
        while(consonants == k && l<=r) {
          if(vowels.contains(chs[l]) && vowmap.get(chs[l]) > 1) vowmap.put(chs[l], vowmap.get(chs[l])-1);
          else break;
          l++; count++;
        }
        res += (count+1);
      }
      r++;
    }
    return res;
  }

  private int add(char ch, Map<Character, Integer> vowmap, int consonants){
    if(vowels.contains(ch)) {
      vowmap.put(ch, vowmap.getOrDefault(ch, 0)+1);
    }else{
      consonants++;
    }
    return consonants;
  }

  private int rem(char ch, Map<Character, Integer> vowmap, int consonants){
    if(vowels.contains(ch)) {
      vowmap.put(ch, vowmap.get(ch)-1);
      if(vowmap.get(ch) == 0) vowmap.remove(ch);
    }else{
      consonants--;
    }
    return consonants;
  }
}