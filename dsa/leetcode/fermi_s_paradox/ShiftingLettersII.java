package dsa.leetcode.fermi_s_paradox;

public class ShiftingLettersII {

  /**
   * https://leetcode.com/problems/shifting-letters-ii/description/
   *
   * Instead of shifting every character in each shift, could keep track of which characters are shifted and by how much across all shifts.
   * Try marking the start and ends of each shift, then perform a prefix sum of the shifts.
   * 
   * Remember for -ve shifts, we add 26 to the result to get the correct character.
   *
   * TC: O(n + m) SC: O(n)
   * #array #string #prefix-sum #medium
   */

  class Solution {
    public String shiftingLetters(String s, int[][] shifts) {
        int arr[] = new int[s.length() + 1];
        
        // Record the effect of each shift
        for (int[] shift : shifts) {
            if (shift[2] == 1) {
                arr[shift[0]]++;
                arr[shift[1] + 1]--;
            } else {
                arr[shift[0]]--;
                arr[shift[1] + 1]++;
            }
        }

        // Apply prefix sum to accumulate shifts
        int netShift = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            netShift = (netShift + arr[i]) % 26;
            int newChar = (s.charAt(i) - 'a' + netShift + 26) % 26 + 'a';
            sb.append((char) newChar);
        }

        return sb.toString();
    }
  }
}