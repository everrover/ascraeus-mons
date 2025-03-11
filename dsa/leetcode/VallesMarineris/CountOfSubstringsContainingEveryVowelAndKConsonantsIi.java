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

  public long countSubstringsWithAllVowels(String word, int k) {
    long count = 0, res = 0;
    Map<Character, Integer> vowmap = new HashMap<>();
    Set<Character> vowels = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u'));
    int l = 0, r = 0, consonants = 0;
    char[] chs = word.toCharArray();
    while (r < chs.length) {
      consonants = add(chs[r], vowmap, consonants);
      while (l <= r && consonants > k) {
        consonants = rem(chs[l], vowmap, consonants);
        l++;
      }
      if (vowmap.size() == 5) {
        // Every vowel is present
        res += count + 1;
      }
      r++;
    }
    return res;
  }

  private int add(char c, Map<Character, Integer> vowmap, int consonants) {
    if (vowmap.containsKey(c)) {
      vowmap.put(c, vowmap.get(c) + 1);
    } else {
      if ('a' <= c && c <= 'z' && !vowmap.containsKey(c)) {
        cps
      }
    }
    return consonants;
  }

  private int rem(char c, Map<Character, Integer> vowmap, int consonants) {
    if (vowmap.containsKey(c)) {
      int count = vowmap.get(c);
      if (count == 1) {
        vowmap.remove(c);
      } else {
        vowmap.put(c, count - 1);
      }
    } else {
      consonants--;
    }
    return consonants;
  }
}