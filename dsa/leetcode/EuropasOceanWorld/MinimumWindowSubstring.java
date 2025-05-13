package dsa.leetcode.EuropasOceanWorld;

public class MinimumWindowSubstring {

    /**
     * https://leetcode.com/problems/minimum-window-substring/description/
     *
     * Utilizes a sliding window approach to find the minimum substring window
     * in s that contains all characters from t. Expand the right pointer until all
     * characters from t are found, then contract the left pointer while maintaining
     * the condition, updating the minimum window size as necessary.
     * 
     * TC: O(m + n), SC: O(1)
     * #sliding-window #hash-table #string #hard
     */

    public String minWindow(String s, String t) {
        int[] map = new int[128];
        int[] extras = new int[128];
        char[] str = s.toCharArray();
        char[] ttr = t.toCharArray();
        final int m = s.length(), n = t.length();
        String ans = "";
        if (m == 0 || n == 0 || m < n) { // empty strings
            return ans;
        }
        int left = 0, right = 0, ansSize = Integer.MAX_VALUE, size = 0;
        while (right < m) { // expand right pointer
            // Add comments as needed for clarity

            // Logic for sliding window
            right++;
        }
        return ans;
    }
}