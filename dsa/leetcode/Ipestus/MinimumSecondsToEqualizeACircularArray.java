package dsa.leetcode.Ipestus;

import java.util.*;

public class MinimumSecondsToEqualizeACircularArray {
    /**
     * https://leetcode.com/problems/minimum-seconds-to-equalize-a-circular-array/description/
     * 
     * For each unique number in the array, calculate the maximum distance between consecutive occurrences of that number (considering the circular nature of the array). 
     * The minimum seconds required to equalize the array is the minimum of these maximum distances divided by 2 (rounded up).
     * 
     * Because the time needed to equalize a given range (i,j) = ceil((j - i - 1)/2).
     * 
     * TC: O(n) SC: O(n)
     * #array #hash-table #medium
     */
    
    public int minSeconds(int[] nums) {
        Map<Integer, List<Integer>> s = new HashMap<>();
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            s.putIfAbsent(nums[i], new ArrayList<>());
            s.get(nums[i]).add(i);
        }
        
        for (Map.Entry<Integer, List<Integer>> nset: s.entrySet()) {
            List<Integer> lst = nset.getValue();
            int tmp = -1;
            for (int i = 1; i < lst.size(); i++) {
                tmp = Math.max(tmp, (int)Math.ceil((double)(lst.get(i) - lst.get(i - 1) - 1) / 2));
            }
            tmp = Math.max(tmp, (int)Math.ceil((double)(nums.length - lst.get(lst.size() - 1) - 1 + lst.get(0)) / 2));
            res = Math.min(tmp, res);
        }
        return res;
    }
}