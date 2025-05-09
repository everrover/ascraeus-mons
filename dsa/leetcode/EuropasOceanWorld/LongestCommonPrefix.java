package dsa.leetcode.EuropasOceanWorld;

public class LongestCommonPrefix {

    /**
     * https://leetcode.com/problems/longest-common-prefix/
     *
     * Finds the longest common prefix string among an array of strings.
     * If no common prefix is found, returns an empty string.
     * 
     * TC: O(S) where S is the sum of all characters in all strings. SC: O(1)
     * #string #trie #easy
     */
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        String lcp = strs[0];
        int length = lcp.length();
        for (int j = 1; j < strs.length; j++) {
            length = Math.min(length, strs[j].length());  // Adjust the length to the smallest string's length
            int k = 0;
            for (k = 0; k < length; k++) {
                if (lcp.charAt(k) != strs[j].charAt(k)) {
                    break;
                }
            }
            lcp = lcp.substring(0, k);
        }
        return lcp;
    }
}