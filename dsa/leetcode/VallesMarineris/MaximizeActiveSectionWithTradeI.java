package dsa.leetcode.VallesMarineris;

// https://leetcode.com/problems/maximize-active-section-with-trade-i/
// 
// For the given set of ops, we select two different but adjacent regions of zeros[max number of zeros], convert
// the middle ones to zeros and convert the three regions to all ones.
// 000011100 => 000000000 =>111111111
// 
// Or if only one zero region is present, lz == co[only one zero region with atleast one ending one] or cz == co[only one zero region with no ending ones], there's no point in executing the trade, so we return ones count.
//
// First and last set of encountered converted ones are ignored.
// TC: O(n); SC: O(1)
// #greedy #trade #string #medium

public class MaximizeActiveSectionWithTradeI {

    public int maxActiveSectionsAfterTrade(String s) {
        // oc = ones count, co = converted ones[from zeros], cz = current zeros, lz = last zeros
        int oc = 0, co = 0, cz = 0, lz = 0;
        for(char ch: s.toCharArray()){
          if(ch == '0') cz++;
          else{
            if(cz != 0) lz = cz;
            cz=0; oc++;
          }
          co = Math.max(co, lz+cz);
        }
        // last set of zeros remaining / first set of zeros only
        if(co == cz || co == lz) return oc;
        return oc+co;
      }
}