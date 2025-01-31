package dsa.leetcode.mithrim_montez;

public class NeighboringBitwiseXOR {
  /**
   * https://leetcode.com/problems/neighboring-bitwise-xor/description/?envType=daily-question&envId=2025-01-17
   *
   * Start with a `0` or `1` and derive the array by XORing the previous element with the current element.
   *
   * TC: O(n) SC: O(1)
   * #array #bit-manipulation #medium
   */

  

  public boolean doesValidArrayExist(int[] derived) {
    int last = 1;
    for(int i=1; i<derived.length; i++) last = last^derived[i-1];
    if(derived[derived.length-1] == 1){
      return 1 != last;
    }else{
      return 1 == last;
    }
  }

  // actual simulation
  public boolean doesValidArrayExistActual(int[] derived) {
    // start 0
    boolean res1 = false, res2 = false;
    int []pi = new int[derived.length]; pi[0] = 0;
    for(int i=1; i<derived.length; i++){
      if(derived[i-1] == 0){
        pi[i] = pi[i-1];
      }else{
        pi[i] = pi[i-1]^1;
      }
    }
    if(derived[derived.length-1] == 1){
      res1 = pi[pi.length-1] != pi[0];
    }else{
      res1 = pi[pi.length-1] == pi[0];
    }
    pi[0] = 1;
    for(int i=1; i<derived.length; i++){
      if(derived[i-1] == 0){
        pi[i] = pi[i-1];
      }else{
        pi[i] = pi[i-1]^1;
      }
    }
    if(derived[derived.length-1] == 1){
      res2 = pi[pi.length-1] != pi[0];
    }else{
      res2 = pi[pi.length-1] == pi[0];
    }
    return res1||res2;
  }
}