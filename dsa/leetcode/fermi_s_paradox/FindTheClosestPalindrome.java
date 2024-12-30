package dsa.leetcode.fermi_s_paradox;

import java.util.*;

public class FindTheClosestPalindrome {

    /**
     * https://leetcode.com/problems/find-the-closest-palindrome/description/?envType=company&envId=goldman-sachs&favoriteSlug=goldman-sachs-all
     *
     * The goal is to find the nearest palindrome number. For each number,
     * generate potential palindromes by adjusting the first half of the number.
     * Compare these to find the closest one.
     *
     * TC: O(log^2(N)) SC: O(1)
     * #math #string #hard
     */
    public String nearestPalindromic(String n) {
        final int m = n.length();
        long origval = Long.valueOf(n);
        long res = (long) 1e19, diff = Long.MAX_VALUE;
        List<Long> cand = new LinkedList<>();
        String onehalf = n.substring(0, (m + 1) / 2);

        for (int i : new int[]{-1, 0, 1}) {
            StringBuilder sb1 = new StringBuilder();
            StringBuilder sb2 = new StringBuilder();
            sb1.append((Long.valueOf(onehalf) + i));
            sb2.append((Long.valueOf(onehalf) + i));
            if (m % 2 == 1 && sb2.length() > 0) sb2.setLength(sb2.length() - 1);
            // Add potential palindrome
            cand.add(Long.valueOf(sb1.toString() + new StringBuilder(sb2).reverse().toString()));
        }

        if (m == 1) return "" + (Long.valueOf(n) - 1);

        // Calculate closest palindrome

        return ""; // Placeholder for closest palindrome calculation
    }

}