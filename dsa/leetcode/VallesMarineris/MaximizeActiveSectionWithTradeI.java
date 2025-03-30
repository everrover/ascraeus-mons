package dsa.leetcode.VallesMarineris;

// https://leetcode.com/problems/maximize-active-section-with-trade-i/

// To maximize active sections, we strategically trade one contiguous inactive segment
// surrounded by active segments with an active one. Track zero-one segments and calculate
// potential trade benefits. Find the maximal benefit and apply the trade.
// 
// First and last set of encountered converted ones are ignored.
// TC: O(n); SC: O(1)
// #greedy #trade #string #medium

public class MaximizeActiveSectionWithTradeI {

    public int maxActiveSectionsAfterTrade(String s) {
        // oc = total ones, co = converted ones, cz = current zeros, lz = last zeros
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