package dsa.leetcode.fermi_s_paradox;

import java.util.*;

public class UniqueLength3PalindromicSubsequences {

    /**
     * https://leetcode.com/problems/unique-length-3-palindromic-subsequences/description/?envType=daily-question&envId=2025-01-04
     *
     * The solution involves identifying unique palindromic subsequences 
     * by tracking appearances of characters before and after positions in the string.
     * 
     * Prefix sum can also be used by iterating over each char but uses O(n 26) space.
     *
     * TC: O(n) SC: O(1)
     * #hash-table #string #prefix-sum #medium
     */

    public int countUniquePalindromicSubsequences(String s) {
        int[][] fl = new int[2][26]; // To store first and last occurrence of each character
        Arrays.fill(fl[0], -1);
        Arrays.fill(fl[1], -1);
        int res = 0;

        // Find first and last positions
        for (int i = 0; i < s.length(); i++) {
            if (fl[0][s.charAt(i) - 'a'] == -1) 
                fl[0][s.charAt(i) - 'a'] = i;
            else 
                fl[1][s.charAt(i) - 'a'] = i;
        }

        // Calculate unique palindromic subsequences
        for (int i = 0; i < 26; i++) {
            if (fl[0][i] == -1 || fl[1][i] == -1) continue;

            Set<Character> set = new HashSet<>();
            for (int j = fl[0][i] + 1; j < fl[1][i]; j++) {
                set.add(s.charAt(j));
            }
            res += set.size();
        }
        return res;
    }
}