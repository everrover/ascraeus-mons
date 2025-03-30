package dsa.leetcode.VallesMarineris;

// https://leetcode.com/problems/maximize-active-section-with-trade-i/
// To maximize active sections, we strategically trade one contiguous inactive segment
// surrounded by active segments with an active one. Track zero-one segments and calculate
// potential trade benefits. Find the maximal benefit and apply the trade.
// TC: O(n); SC: O(1)
// #greedy #trade #string #medium

public class MaximizeActiveSectionWithTradeI {

    public int maximizeActive(String s) {
        s = "1" + s + "1"; // Augment the string
        int oc = 0, co = 0, cz = 0, lz = 0; // Initialize counter variables

        for (char c : s.toCharArray()) {
            if (c == '1') { // Count ones
                co = Math.max(co, lz + cz); // Max potential benefit
                cz = 0;
                oc++;
            } else { // Count a zero
                cz++;
                if (cz != 0) lz = cz; // Keep track of last zero segment
            }
        }
        return oc + co; // Return max active sections
    }
}