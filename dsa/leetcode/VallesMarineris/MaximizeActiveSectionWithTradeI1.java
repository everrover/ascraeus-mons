package dsa.leetcode.VallesMarineris;

public class MaximizeActiveSectionWithTradeI1 {

    /**
     * https://leetcode.com/problems/maximize-active-section-with-trade-i/description/
     * 
     * To maximize the number of active sections, traverse through the string to count all '1's.
     * Keep track of consecutive zero segments delimited by '1's. Convert the largest zero segment,
     * when surrounded by '1's, to '1's.
     * 
     * TC: O(n) SC: O(1)
     * #string #enumeration #medium
     */

    public int maxActiveSectionsAfterTrade(String s) {
        int oc = 0, co = 0, cz = 0, lz = 0;
        for(char ch: s.toCharArray()){
            if(ch == '0') cz++;
            else {
                if(cz != 0) lz = cz;
                cz = 0; oc++;
            }
            co = Math.max(co, lz + cz);
        }
        // last set of zeros remaining / first set of zeros only
        if(co == cz || co == lz) return oc;
        return oc + co;
    }
}
// Example: 1 01110010011000000001 1
//           xxx  x  xx        x