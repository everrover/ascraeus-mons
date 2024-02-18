package dsa.leetcode.kuiperBelt;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/count-prefix-and-suffix-pairs-ii/
 * This code involves the use of a augmented Trie structure to solve 💡.
 * The goal is to efficiently count prefix and suffix pairs in a set of words. And using pairs seemed fine,
 * since, sum(words[i].length) <= 5*10^5, we can use pairs of [j,len-j-1] to represent prefix and suffix.
 * Max number of these nodes of trie = 5*10^5+1, acceptable.
 * A unique approach to handle prefix and suffix by combining them into a single entity in Trie is used 🚀.
 * 
 * TC: O(n * l^2) SC: O(n * l)
 * #trie #string #hard  #augmented-data-structure
 */
public class CountPrefixAndSuffixPairsII {
  public long countPrefixSuffixPairs(String[] words) {
    long res = 0;
    Trie trie = new Trie();
    for(String word: words){
      res += Trie.findAndAddPair(trie, word);
    }
    return res;
  }

  public static class Trie{
    public char ch1, ch2;
    public Map<Integer, Trie> next;
    public long cnt;

    public Trie(){
      ch1 = ch2 = '-';
      cnt = 0;
      next = new HashMap<>();
    }

    public Trie(char c1, char c2){
      ch1 = c1;
      ch2 = c2;
      cnt = 0;
      next = new HashMap<>();
    }

    public static long findAndAddPair(Trie node, String str){
      long res = 0;
      Trie curr = node;
      char []chs = str.toCharArray();
      for(int i=0, j=chs.length-1; i<chs.length; i++, j--){ // made mistake here by using i<=j, but we have to represent both prefix and suffix
        int idx=(chs[i]-'a')*26+(chs[j]-'a');
        curr.next.putIfAbsent(idx, new Trie(chs[i], chs[j]));
        res += curr.next.get(idx).cnt;
        curr = curr.next.get(idx);
      }
      curr.cnt++;
      return res;
    }
  }
}