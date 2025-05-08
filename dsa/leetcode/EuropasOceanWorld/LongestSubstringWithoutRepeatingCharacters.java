package dsa.leetcode.EuropasOceanWorld;

public class LongestSubstringWithoutRepeatingCharacters {
    /**
     * https://leetcode.com/problems/longest-substring-without-repeating-characters
     *
     * Use a sliding window with two pointers and a boolean array to track characters.
     * Move the "end" pointer, and if a duplicate character is found, move the "start" pointer.
     * Update the maximum length of substring without repeating characters.
     *
     * TC: O(n) SC: O(128), which simplifies to O(1)
     * #hash-table #string #sliding-window #medium
     */

    public int lengthOfLongestSubstring(String s) {
        int start = 0, end = -1, maxLen = 0;
        final int LEN = s.length();
        boolean[] charSet = new boolean[128];  // ASCII character set
        while (end < LEN - 1) {
            int l = (int) s.charAt(start);
            int r = (int) s.charAt(end + 1);
            if (charSet[r]) {
                // If character is repeating, move start
                charSet[l] = false;
                start++;
            } else {
                // If unique, include in window and update maxLen
                charSet[r] = true;
                ++end;
                maxLen = Integer.max(end - start + 1, maxLen);
            }
        }
        return maxLen;
    }
}