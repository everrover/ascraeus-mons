package dsa.leetcode.Ipestus;

class Solution {

    /**
     * https://leetcode.com/problems/minimum-deletions-to-make-string-k-special/description/?envType=daily-question&envId=2025-06-21
     *
     * To make a string k-special, compete by minimizing deletions such
     * that the frequency difference between any two characters does not
     * exceed k. Count each character's frequency, assume a certain frequency
     * to be the minimal allowed, and then adjust accordingly.
     *
     * TC: O(n) SC: O(26)
     * #hash-table #string #greedy #medium
     */

    public int minimumDeletions(String word, int k) {
        int[] freq = new int[26];
        char[] chs = word.toCharArray();
        int res = chs.length;
        for (char ch : chs) freq[ch - 'a']++;
        for (int i = 0; i < 26; i++) {
            if (freq[i] > freq[j]) {
                del += freq[j];
            }
        }
        return res;
    }
}