package dsa.leetcode.JupitersGreatStorm;

import java.util.*;

/**
 * https://leetcode.com/problems/sum-of-decoded-numbers/
 *
 * Decoding is pure string surgery: width is the last digit of nums[i], d drops that digit, and splitting the decimal string of d at position width gives x (the leading widthi digits) and y (everything after). No arithmetic on digit counts is needed since Long.toString/substring does it directly.
 *
 * Once x and y are known, x^y mod (1e9+7) is computed with standard binary exponentiation (square-and-multiply) in O(log y), since y can be as large as ~1e9 and a naive multiplication loop would be too slow. Each element's decoded value is accumulated into a running modular sum, giving overall O(n log MAXY) time.
 *
 * TC: O(n log MAXY) SC: O(1)
 * #array #math #string #binary-exponentiation #medium
 */

class SumOfDecodedNumbers {
    private static final int M = (int)1e9+7;
    public int sumDecoded(long[] nums) {
        long sum = 0;
        for (long num : nums) {
            int width = (int) (num % 10);
            long d = num / 10;
            String s = Long.toString(d);
            long x = Long.parseLong(s.substring(0, width));
            long y = Long.parseLong(s.substring(width));
            sum = (sum + power(x, y)) % M;
        }
        return (int) sum;
    }

    private long power(long base, long exp) {
        base %= M;
        long res = 1;
        while (exp > 0) {
            if ((exp & 1) == 1) res = (res * base) % M;
            base = (base * base) % M;
            exp >>= 1;
        }
        return res;
    }
}
