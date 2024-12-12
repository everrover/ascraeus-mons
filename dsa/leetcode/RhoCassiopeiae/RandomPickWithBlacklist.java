package dsa.leetcode.RhoCassiopeiae;

import java.util.*;

public class Solution {
    /**
     * https://leetcode.com/problems/random-pick-with-blacklist/
     * 
     * Map elements in blacklist to available numbers in a smaller range.
     * Use an internal map to replace occurrences of blacklisted indices.
     * 
     * TC: O(B) for setup with blacklist size B; O(1) per pick.
     * SC: O(B) for blacklist mapping.
     * #hash-map #randomization #hard
     */

    private int MOD = 0;
    private int idx = 0;
    private Map<Integer, Integer> map = new HashMap<>();

    public Solution(int n, int[] blacklist) {
        MOD = n;
        int len = blacklist.length, idx = 0;
        Set<Integer> set = new HashSet<>();
        for(int b: blacklist) if(b < n) set.add(b);
        List<Integer> arr = new ArrayList<>();
        for (int i=0; i<n && i<2*set.size(); i++){
            if (!set.contains(i)) arr.add(i);
        }
        for (int i = 0; i < len ; i++) {
            map.put(blacklist[i], arr.get(idx));
            idx = (idx+1)%arr.size();
        }
    }

    public int pick() {
        // this mimics the needed random function
        idx = (idx+1)%MOD;
        return map.containsKey(idx) ? map.get(idx) : idx;
    }
}