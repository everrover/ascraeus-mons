package dsa.leetcode.VallesMarineris;

/**
 * https://leetcode.com/problems/count-the-number-of-powerful-integers/description/?envType=daily-question&envId=2025-04-10
 *
 * Count integers with digits at most the limit and suffix specific to the integer. 
 * Use digit DP to find the answer from count[finish] - count[start - 1].
 *
 * TC: O(n * m) SC: O(n * m), n = number of digits, m = possible states
 * #dynamic-programming #math #string #hard
 */

class Solution {
    public long numberOfPowerfulInt(long start, long finish, int limit, String s) {
        return calc(Long.toString(finish), s, limit) - calc(Long.toString(start - 1), s, limit);
    }

    private long calc(String num, String suf, int limit) {
        if (num.length() < suf.length()) return 0L;
        else if (num.length() == suf.length()) return num.compareTo(suf) >= 0 ? 1L : 0L;
        int preLen = num.length() - suf.length();
        String sufInNum = num.substring(preLen);
        long count = 0;
        for (int i = 0; i < preLen; i++) {
            int digit = num.charAt(i) - '0';
            // Iteratively check each digit ensuring it remains within the limit
            if (digit > limit) break;
            if (i == preLen - 1 && sufInNum.equals(suf)) {
                count = digit;
            }
        }
        return count;
    }
}