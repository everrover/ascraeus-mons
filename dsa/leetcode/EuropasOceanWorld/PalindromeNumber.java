package dsa.leetcode.EuropasOceanWorld;

public class PalindromeNumber {

    /**
     * https://leetcode.com/problems/palindrome-number/description/
     *
     * 
     * The solution checks if the number is negative or not. Then converts the number to a string and uses a two-pointer technique to compare characters from the beginning and end of the string.
     * If any mismatch is found, it returns false.
     *
     * TC: O(n) SC: O(1)
     * #math #easy
     */

    public boolean isPalindrome(int x) {
        if (x < 0) return false;
        String y = "" + x;
        int l = 0, r = y.length() - 1;
        while (l < r) {
            if (y.charAt(l) != y.charAt(r)) return false;
            l++;
            r--;
        }
        return true;
    }
}