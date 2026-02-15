package dsa.leetcode.Ipestus;

class HexadecimalAndHexatrigesimalConversion { 
  /**
   * https://leetcode.com/problems/hexadecimal-and-hexatrigesimal-conversion/description/
   * 
   * Convert a given integer's square to hexadecimal and its cube to base-36.
   * Concatenate both results to form the final output.
   * 
   * TC: O(log n) SC: O(1)
   * #base-conversion #math #easy
   */

  public String concatHex36(int n) {
    int m = n * n;
    String res = "";
    while(m > 0) {
      int dig = m % 16;
      res = numshexa[dig] + res;
      m /= 16;
    }
    String restwo = "";
    m = n * n * n;
    while(m > 0) {
      int dig = m % 36;
      restwo = tetrahexa[dig] + restwo;
      m /= 36;
    }
    return res + restwo;
  }

  private char[] numshexa = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
  private char[] tetrahexa = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
}