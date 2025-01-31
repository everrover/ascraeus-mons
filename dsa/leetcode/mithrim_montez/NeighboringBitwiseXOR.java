package dsa.leetcode.mithrim_montez;

public class NeighboringBitwiseXOR {
  /**
   * https://leetcode.com/problems/neighboring-bitwise-xor/description/?envType=daily-question&envId=2025-01-17
   *
   * The problem checks whether there exists a valid original array from the derived array by maintaining an XOR relationship.
   * For a valid original array, the XOR-sum of the derived array should be 0, ensuring parity of transitions if no contradiction arises 
   * between calculated and required transitions.
   *
   * TC: O(n) SC: O(1)
   * #array #bit-manipulation #medium
   */

  public boolean doesValidArrayExist(int[] derived) {
    int last = 0;
    for(int i = 1; i < derived.length; i++) {
      if(derived[i-1] != 0) last = last ^ 1;
    }
    return (derived[derived.length-1] == 0) == (last == 0);
  }
}