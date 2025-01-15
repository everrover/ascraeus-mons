package dsa.leetcode.mithrim_montez;

/**
 * https://leetcode.com/problems/minimize-xor/description/?envType=daily-question&envId=2025-01-15
 *
 * Calculate the number of set bits in num2 and use that to determine the number of set bits needed in the result.
 * Try to use bits from num1 and, if necessary, set additional bits from least significant to minimize XOR result.
 *
 * TC: O(1) SC: O(1)
 * #bit-manipulation #greedy #medium
 */

public class MinimizeXOR {

    public int minimizeXor(int num1, int num2) {
        int cnt = 0;
        // Count the number of set bits in num2
        for (int i = 0; i < 32; i++) {
            if ((num2 & (1 << i)) > 0) cnt++;
        }
        int res = 0;
        // Try to set bits from num1 where possible and count down the necessary set bits
        for (int i = 31; i >= 0; i--) {
            if ((num1 & (1 << i)) > 0 && cnt > 0) {
                res ^= (1 << i);
                cnt--;
            }
        }
        // Set remaining bits from least significant bits
        for (int i = 0; i < 32 && cnt > 0; i++) {
            if ((res & (1 << i)) == 0) {
                res ^= (1 << i);
                cnt--;
            }
        }
        return res;
    }
}