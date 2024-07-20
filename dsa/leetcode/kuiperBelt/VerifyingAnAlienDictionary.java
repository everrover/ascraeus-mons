package dsa.leetcode.kuiperBelt;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/verifying-an-alien-dictionary/
 * In an alien language, surprisingly, they use English lowercase letters in a different order.
 * This method checks if the given words are sorted lexicographically in this alien language.
 *
 * TC: O(C), where C is the total content of words.
 * SC: O(1)
 * #array #hash-table #string #easy
 */
public class VerifyingAnAlienDictionary {

  public boolean isAlienSorted(String[] words, String order) {
    int[][] orderSorted = new int[order.length()][2];
    for(int i=0; i<order.length(); i++) {
      orderSorted[i][0] = order.charAt(i)-'a';
      orderSorted[i][1] = i;
    }
    Arrays.sort(orderSorted, (a,b)->a[0]-b[0]);
    char[] c1, c2;
    c2 = words[0].toCharArray();
    for(int i=1; i<words.length; i++){
      c1 = c2;
      c2 = words[i].toCharArray();
      if(!sorted(c1, c2, orderSorted)) return false;
    }
    return true;
  }

  private boolean sorted(char[] w1, char[] w2, int[][] orderSorted){
    for(int i=0; i<w1.length && i<w2.length; i++)
      if(orderSorted[w1[i]-'a'][1] < orderSorted[w2[i]-'a'][1]) return true;
      else if(orderSorted[w1[i]-'a'][1] > orderSorted[w2[i]-'a'][1]) return false;
    return w1.length <= w2.length;
  }
}