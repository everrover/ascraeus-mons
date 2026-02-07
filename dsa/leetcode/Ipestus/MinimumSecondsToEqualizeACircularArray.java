package dsa.leetcode.Ipestus;

import java.util.*;

public class MinimumSecondsToEqualizeACircularArray {
    /**
     * https://leetcode.com/problems/minimum-seconds-to-equalize-a-circular-array/description/
     * 
     * The solution involves calculating for each number in the array, the time it would take to make all other numbers equal to it.
     * This is done by calculating the maximum distance between indices of the same number and halving it, due to simultaneous replacements.
     * The minimum of these calculated maximum distances across numbers gives the required number of seconds.
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